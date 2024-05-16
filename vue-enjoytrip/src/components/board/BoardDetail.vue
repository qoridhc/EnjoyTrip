<script setup>
import axios from 'axios';
import { useArticleStore } from '@/stores/article';
import { useRoute, useRouter } from 'vue-router';
import { onMounted, ref } from 'vue';

const articleStore = useArticleStore()
const router = useRouter()
const route = useRoute()
const article = ref({})

onMounted(() => {
    article.value = articleStore.setNowArticle(route.params.articleNo)
    if (!article.value) {
        axios.get(`http://localhost/article/view/${route.params.articleNo}`)
            .then(function (response) {
                article.value = response.data
                useArticleStore.article = article
            })
            .catch(function (error) {
                console.log(error)
            })
    }
})

//삭제
function remove() {
    const url = `${import.meta.env.VITE_VUE_API_URL}:${import.meta.env.VITE_BE_API_PORT}/article/delete/${article.value.articleNo}`
    axios.get(url)
        .then((response) => {
            router.push({ name: 'board-list' })
        })
        .catch((error) => {
            console.log(error)
        })
}
</script>

<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0">
            <div class="py-5">
                <div class="row justify-content-center">
                    <hr class="mt-2" />
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <div class="row my-2">
                            <h2 class="text-dark fw-bolder">{{ article.articleNo }}. {{ article.subject }}</h2>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="clearfix align-content-center">
                                    <img class="avatar me-2 float-md-start bg-light p-2"
                                        src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
                                    <p> <span class="fw-bold">{{ article.userId }}</span> <br /> </p>
                                </div>
                            </div>
                            <div class="col-md-4 align-self-center text-end">댓글 : 0</div>

                            <div class="divider mb-3" />
                            <div class="text-secondary"> {{ article.content }} </div>
                            <div class="divider mt-3 mb-3"></div>

                            <div class="d-flex justify-content-end">
                                <router-link :to="{ name: 'board-list' }" type="button" id="btn-list"
                                    class="btn btn-outline-primary mb-3">
                                    글목록
                                </router-link>
                                <router-link :to="{name: 'board-modify', params: article.articleNo } " class="btn btn-outline-success mb-3 ms-1">
                                    글수정
                                </router-link>
                                <button type="button" @click="remove" class="btn btn-outline-danger mb-3 ms-1">
                                    글삭제
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>