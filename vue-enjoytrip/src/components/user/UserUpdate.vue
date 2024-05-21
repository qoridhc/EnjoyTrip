<script setup>
import { onMounted, ref } from 'vue';
import { useMemberStore } from '@/stores/member';
import { useMenuStore } from '@/stores/menu';
import { modifyUser, deleteUser } from '@/api/user';
import { useRouter } from 'vue-router';

const user = ref({})
const router = useRouter()
const memberStore = useMemberStore()

onMounted(() => {
    user.value = memberStore.userInfo
    user.value.email = `${user.value.email_1}@${user.value.email_2}`
    user.value.pw = ""
    user.value.pwCheck = ""
})

function submit(){
    if(!validationCheck()){
        alert("비밀번호 불일치")
        return;
    }
    let params = {}
    params.id = user.value.id
    if(user.value.name) params.name = user.value.name
    if(user.value.pw) params.pw = user.value.pw
    if(user.value.email) params.email = user.value.email

    modifyUser(
        params,
        function(data){
            useMemberStore().userInfo = data
            router.push({name: 'user-mypage'})
        },
        function(error){
            console.log("submit(UserUpdate.vue): 회원 정보 수정 실패\nerror: ", error)
        }
    )
}

function validationCheck() {
    if (user.value.pw !== user.value.pwCheck) {
        console.log("validationCheck(UserSignup.vue): 비밀번호 불일치\nuser:", user.value)
        return false
    }
    return true
}

function remove(){
    if(confirm("정말 삭제하시겠습니까?")){
        memberStore.userLogout()
        useMenuStore().logoutMenuState()

        deleteUser(
            user.value.id,
            function(response){
                memberStore.userInfo = null
                router.push({name: 'Home'})
            },
            function(error){
                console.log("remove(UserUpdate.vue): 유저 삭제 실패\nresponse: ", error)
            }
        )
    }
}

</script>
<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0 container px-5 py-5">
            <div class="py-5 px-4 px-md-5 mb-5">

                <!-- title -->
                <h1 class="fw-bolder text-center mb-5">프로필 수정</h1>

                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <form>

                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" type="text" v-model="user.name" />
                                <label for="name">이름</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    A name is required.
                                </div>
                            </div>

                            <!-- ID input -->
                            <div class="form-floating mb-3">
                                <div class="form-control text-start" id="id" type="text"> {{ user.id }} </div>
                                <label for="name">아이디</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    ID is required.
                                </div>
                            </div>

                            <!-- PWD input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwd" type="password" v-model="user.pw" />
                                <label for="name">비밀번호</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    A password is required.
                                </div>
                            </div>

                            <!-- PWD recheck input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwCheck" type="password" v-model="user.pwCheck" />
                                <label for="name">비밀번호 확인</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    Password is not valid.
                                </div>
                            </div>

                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" type="email" v-model="user.email" />
                                <label for="email">이메일</label>

                                <!-- 미입력, 양식 오류시 -->
                                <div class="invalid-feedback">
                                    An email is required.
                                </div>
                                <div class="invalid-feedback">
                                    Email is not valid.
                                </div>
                            </div>

                            <!-- Submit Button-->
                            <div class="d-flex">
                                <input class="btn btn-lg btn-dark me-3" @click.prevent="submit" type="submit">
                                <input class="btn btn-lg btn-danger" @click.prevent="remove" type="submit" value="삭제">
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.btn.btn-lg.btn-dark{
    width: 78%;
}
.btn.btn-lg.btn-danger{
    width: 19%;
}
</style>