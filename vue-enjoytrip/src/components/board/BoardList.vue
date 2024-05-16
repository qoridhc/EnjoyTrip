<script setup>
import BoardListHeader from '@/components/board/list/BoardListHeader.vue';
import BoardListTableHead from '@/components/board/list/BoardListTableHead.vue';
import BoardListTableBody from '@/components/board/list/BoardListTableBody.vue';
import BoardListPagination from '@/components/board/list/BoardListPagination.vue';

import axios from 'axios'
import { useArticleStore } from '@/stores/article';

const articleStore = useArticleStore()

axios.get('http://localhost/article/list')
    .then(function(response){
        articleStore.fetchArticles(response)
    })
    .catch(function(error){
        console.log(error)
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
                        v-for="(article) in articleStore.articles" :key="article.articleNo" 
                        :article="article"
                    />
                </table>
            </div>
            <BoardListPagination />
        </div>
    </div>
</template>

<style scoped></style>