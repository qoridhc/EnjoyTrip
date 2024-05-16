<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useArticleStore } from '@/stores/article';
import axios from 'axios';
const articleStore = useArticleStore()
const article = articleStore.article
const router = useRouter()

function modify(){
    const url = `${import.meta.env.VITE_VUE_API_URL}:${import.meta.env.VITE_BE_API_PORT}/article/modify`
    axios({
        method: 'post',
        url,
        data: article
    })
    .then((response)=>{
        router.push({name: 'board-detail', params: {articleNo: response.data.articleNo}})
    })
    .catch((error)=>{
        console.log(error)
    })
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
                        <button @click.prevent="modify" class="btn btn-outline-primary mb-3 me-2">
                            글수정
                        </button>
                        <button type="button" class="btn btn-outline-danger mb-3">
                            목록으로이동...
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped></style>