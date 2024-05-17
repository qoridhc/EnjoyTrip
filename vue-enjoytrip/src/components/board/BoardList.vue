<script setup>
import BoardListHeader from '@/components/board/list/BoardListHeader.vue';
import BoardListTableHead from '@/components/board/list/BoardListTableHead.vue';
import BoardListTableBody from '@/components/board/list/BoardListTableBody.vue';
import BoardListPagination from '@/components/board/list/BoardListPagination.vue';

import { getArticleList } from '@/api/board';
import { onMounted, ref } from 'vue';

const articleList = ref([])

onMounted(() => {
    articleList.value = getArticleList(
        function(data){
            articleList.value = data
        },
        function(error){
            console.log("getArticleList(BoardList.vue): 게시글 목록 읽어오기 실패\n", error)
        }
    )
})
</script>

<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0 row justify-content-center py-5">
            <hr class="mt-2" />
            <div class="col-lg-8 col-md-10 col-sm-12">
                <BoardListHeader />

                <table class="table table-hover mt-5">
                    <BoardListTableHead />
                    <BoardListTableBody 
                        v-for="(article) in articleList" :key="article.articleNo" 
                        :article="article"
                    />
                </table>
            </div>
            <BoardListPagination />
        </div>
    </div>
</template>

<style scoped></style>