<script setup>
import { ref } from 'vue';
import axios from 'axios'
import { useRouter } from 'vue-router';
const router = useRouter()
const url = `${import.meta.env.VITE_VUE_API_URL}:${import.meta.env.VITE_BE_API_PORT}/article/write`
const article = ref({})

function submit() {
    console.log(article.value)

    axios({
        method: 'post',
        url,
        data: article.value,
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
                <form id="form-register">
                    <div class="mb-3">
                        <label for="subject" class="form-label">제목 : </label>
                        <input type="text" class="form-control" v-model="article.subject" />
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용 : </label>
                        <textarea class="form-control" rows="15" v-model="article.content"></textarea>
                    </div>
                    <div class="col-auto text-end mb-3">
                        <button type="reset" class="btn btn-outline-danger me-2">초기화</button>
                        <button @click.prevent="submit" class="btn btn-outline-primary">글작성</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>