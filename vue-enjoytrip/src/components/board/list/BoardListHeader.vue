<script setup>
import { ref, defineEmits } from 'vue';
import { searchArticleList } from '@/api/board';


const category = ref('subject')
const keyword = ref('')

const emit = defineEmits(['search'])
const articleList = ref([])

function search(){
    searchArticleList(
        { 
            keyword: keyword.value, 
            category: category.value 
        },
        function(data){
            articleList.value = data
            emit('search', articleList.value )
        },
        function(error){
            console.log("search(BoardListHeader.vue): search 실패\n", error)
        }
    )
}
</script>

<template>
    <div class="row align-self-center mb-2">
        <div class="col-md-7 offset-3">
            <form class="d-flex" @submit.prevent="search">
                <select id="key" class="form-select form-select-sm ms-5 me-1 w-50" aria-label="검색조건" v-model="category">
                    <option value="subject" selected>제목</option>
                    <option value="article_no">글번호</option>
                    <option value="user_id">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" placeholder="검색어..." v-model="keyword"/>
                    <button @click="search" class="btn btn-dark" type="button">검색</button>
                </div>
            </form>
        </div>
        <div class="col-md-2 text-start">
            <router-link :to="{name: 'board-write'}" type="button" class="btn btn-dark btn-sm">
                글쓰기
            </router-link>
        </div>
    </div>
</template>