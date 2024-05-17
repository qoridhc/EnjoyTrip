<script setup>
import { ref } from 'vue';
const user = ref({})
const isFinish = ref(true)

function submit(){
    console.log(user.value)
    if(validationCheck()) return
}

function validationCheck(){
    if(!user.value.name || !user.value.id || !user.value.pw || !user.value.pwCheck || !user.value.email){
        console.log("validationCheck(UserSignup.vue): 항목 부재\nuser:", user.value)
        return false
    }

    if(user.value.pw !== user.value.pwCheck){
        console.log("validationCheck(UserSignup.vue): 비밀번호 불일치\nuser:", user.value)
        return false
    }
    
    return true
}

</script>

<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0 container px-5 py-5">
            <div class="py-5 px-4 px-md-5 mb-5">
                
                <!-- title -->
                <h1 class="fw-bolder text-center mb-5">회원가입</h1>
                
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <form>

                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input  class="form-control" id="name" type="text" v-model="user.name"/>
                                <label for="name">이름</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    A name is required.
                                </div>
                            </div>

                            <!-- ID input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="id" type="text" v-model="user.id"/>
                                <label for="name">아이디</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    ID is required.
                                </div>
                            </div>

                            <!-- PWD input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pw" type="password" v-model="user.pw" />
                                <label for="name">비밀번호</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    A password is required.
                                </div>
                            </div>

                            <!-- PWD recheck input -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwCheck" type="password" v-model="user.pwCheck"/>
                                <label for="name">비밀번호 확인</label>

                                <!-- 미입력시 -->
                                <div class="invalid-feedback">
                                    Password is not valid.
                                </div>
                            </div>

                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" type="email" v-model="user.email"/>
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
                            <div class="d-grid">
                                <input class="btn btn-lg btn-dark" @click.prevent="submit" type="submit">
                            </div>
                        </form>
                        <div v-if="!isFinish">
                            모든 항목을 입력해 주십시오.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
@import "@/assets/css/styles.css";
</style>