<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { writeArticle } from '@/api/board';
import { useMemberStore } from '@/stores/member';

const memberStore = useMemberStore()
const router = useRouter()
const article = ref({})

function submit() {
    if(article.value.subject.length >= 80){
        console.log("submit(BoardWrite.vue): 제목 글자수 제한\n", article.value.subject.length)
        alert("제목 글자수 제한: " + article.value.subject.length + "/80")
        return
    }

    writeArticle(
        {
            id: memberStore.userInfo.id,
            subject: article.value.subject,
            content: article.value.content,
        },
        function(response){
            router.push({name: 'board-detail', params: {articleNo: response.data.articleNo}})  
        },
        function(error){
            console.log("writeArticle(BoardWrite.vue): 게시글 작성 실패\n", error)
        }
    )
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