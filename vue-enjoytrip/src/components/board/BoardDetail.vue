<script setup>
import CommonSpinner from '../common/CommonSpinner.vue';
import BoardCommentList from './comment/BoardCommentList.vue';
import BoardCommentWrite from './comment/BoardCommentWrite.vue';

import { useRoute, useRouter } from 'vue-router';
import { onMounted, ref } from 'vue';

import { getArticle, removeArticle } from '@/api/board';
import { useMemberStore } from '@/stores/member';
import { useCommentStore } from '@/stores/comment';

const router = useRouter()
const route = useRoute()
const memberStore = useMemberStore()
const commentStore = useCommentStore()

const article = ref({})
const isEditable = ref(false)

onMounted(() => {
    getArticle(
        route.params.articleNo,
        function (data) {
            article.value = data
            setIsEditable()
        },
        function (error) {
            console.log("getArticle(BoardDetail.vue): 게시글 읽어오기 실패\n", error)
        }
    )
})

// 게시글 삭제
function remove() {
    removeArticle(
        article.value.articleNo,
        function (response) {
            router.push({ name: 'board-list' })
        },
        function (error) {
            console.log("removeArticle(BoardDetail.vue): 게시글 삭제 실패\n", error)
        },
    )
}

// admin이거나 글쓴이인 경우 수정, 삭제 버튼 출력
function setIsEditable() {
    if(memberStore.userInfo == null){
        isEditable.value = false
        return
    }
    const { id } = memberStore.userInfo
    if (id === article.value.userId || id === 'admin'){
        isEditable.value = true
    }
}

</script>

<template>
    <div class="d-flex flex-column" v-if="article">
        <div class="flex-shrink-0">
            <div class="py-5">
                <div class="row justify-content-center">
                    <hr class="mt-2" />
                    <div class="col-lg-8 col-md-10 col-sm-12">

                        <!-- 타이틀 -->
                        <div class="row my-2">
                            <h2 class="text-dark fw-bolder">{{ article.articleNo }}. {{ article.subject }}</h2>
                        </div>

                        <div class="row">

                            <!-- 작성자 정보 -->
                            <div class="col-md-8">
                                <div class="clearfix align-content-center">
                                    <img class="avatar me-2 float-md-start bg-light p-2"
                                        src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
                                    <p> <span class="fw-bold">{{ article.userId }}</span> <br /> </p>
                                </div>
                            </div>

                            <!-- 조회수 -->
                            <div class="col-md-4 align-self-center text-end">조회수 : {{ article.hit }}</div>

                            <!-- 글내용 -->
                            <div class="divider mb-3" />
                            <div class="content-container text-secondary"> {{ article.content }} </div>
                            <div class="divider mt-3 mb-3"></div>

                            <!-- 기타 버튼 -->
                            <div class="d-flex justify-content-end">
                                <router-link :to="{ name: 'board-list' }" type="button" id="btn-list"
                                    class="btn btn-outline-primary mb-3">
                                    글목록
                                </router-link>
                                <template v-if="isEditable">
                                    <router-link :to="{ name: 'board-modify', params: article.articleNo }"
                                        class="btn btn-outline-success mb-3 ms-1">
                                        글수정
                                    </router-link>
                                    <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="remove">
                                        글삭제
                                    </button>
                                </template>
                            </div>

                            <!-- 댓글 리스트 -->
                            <BoardCommentList :key="commentStore.key" />
                            <BoardCommentWrite @writeComment="commentStore.increment" />

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <CommonSpinner v-else />
</template>

<style scoped>
.content-container {
    min-height: 50vh;
    height: auto;
}
</style>

