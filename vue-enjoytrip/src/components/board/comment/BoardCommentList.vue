<script setup>
import { onMounted, ref } from 'vue'
import BoardCommentListItem from './BoardCommentListItem.vue';
import { getCommentList } from '@/api/board';
import { useRoute } from 'vue-router';

const route = useRoute()
const comments = ref([])

onMounted(() => {
    getCommentList(
        route.params.articleNo,
        function(data){
            comments.value = data
            console.log("onMounted(BoardCommentList.vue): 댓글 읽어오기\ncomment: ",comments.value)
        },
        function(error){
            console.log("onMounted(BoardCommentList.vue): 댓글 읽어오기 오류\nerror: ",error)
        }
    )
})
</script>

<template>
    <div class="comments-section mt-1 mb-1">
        <h4 class="mb-2">댓글</h4>
        <div v-if="comments.length === 0" class="text-secondary">댓글이 없습니다.</div>
        <div v-else>
            <BoardCommentListItem v-for="(comment) in comments" :key="comment.memoNo" :comment="comment" />
        </div>
    </div>
</template>

<style scoped>
.content-container {
    min-height: 45vh;
    height: auto;
}

.comments-section {
    margin-top: 20px;
}
</style>