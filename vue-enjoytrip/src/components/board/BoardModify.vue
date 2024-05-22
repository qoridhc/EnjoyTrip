<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { getArticle, modifyArticle } from '@/api/board';

const memberStore = useMemberStore()
const router = useRouter()
const route = useRoute()
const article = ref({})

onMounted(() => {
    getArticle(
        route.params.articleNo,
        function (data) {
            article.value = data
        },
        function (error) {
            console.log("getArticle(BoardModify.vue): 게시글 읽어오기 실패\n", error)
        }
    )
})

function submit() {
    modifyArticle(
        {
            id: memberStore.userInfo.id,
            articleNo: article.value.articleNo,
            subject: article.value.subject,
            content: article.value.content,
            hit: article.value.hit,
        },
        function(response){
            router.push({name: 'board-detail', params: {articleNo: response.data.articleNo}})  
        },
        function(error){
            console.log("modifyArticle(BoardModify.vue): 게시글 수정 실패\n", error)
        }
    )
}
</script>

<template>
    <div class="vh-100">
        <div class="row py-5" style="margin-top: 10vh">
            <div class="col-lg-8 col-md-10 col-sm-12" style="margin: auto">
                <form id="form-modify" method="POST" action="">
                    <input type="hidden" name="articleNo" v-model="article.articleNo">

                    <div class="mb-3">
                        <label for="subject" class="form-label">제목 : </label>
                        <input type="text" class="form-control" v-model="article.subject" />
                    </div>

                    <div class="mb-3">
                        <label for="content" class="form-label">내용 : </label>
                        <textarea class="form-control" rows="7" v-model="article.content"></textarea>
                    </div>

                    <div class="col-auto text-end">
                        <button @click.prevent="submit" class="btn btn-outline-primary mb-3 me-2">
                            글수정
                        </button>
                        <router-link :to="{name: 'board-list'}" class="btn btn-outline-danger mb-3">
                            목록으로이동...
                        </router-link>

                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped></style>