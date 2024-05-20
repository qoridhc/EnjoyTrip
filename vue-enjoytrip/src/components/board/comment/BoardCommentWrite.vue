<!-- BoardCommentWrite.vue -->
<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { writeComment } from '@/api/board';

const route = useRoute();
const newComment = ref('');
const emit = defineEmits(['writeComment'])

function submitComment() {
    if(useMemberStore().userInfo == null){
        alert("회원가입/로그인해 주십시오");
        return; 
    }

    if(newComment.value === ""){
        alert("댓글을 작성해 주십시오.")
        return;
    }

    const commentData = {
        articleNo: route.params.articleNo,
        userId: useMemberStore().userInfo.id,
        comment: newComment.value,
    };
    writeComment(
        commentData,
        function(response) {
            console.log("submitComment(BoardCommentWrite.vue): 댓글 추가 성공\nresponse: ", response);
            newComment.value = '';
            emit('writeComment')
        },
        function(error) {
            console.log("submitComment(BoardCommentWrite.vue): 댓글 추가 실패\nerror: ", error);
        }
    );
}
</script>

<template>
    <h5 class="mt-3">댓글 작성</h5>
    <div class="comment-write text-end">
        <textarea v-model="newComment" class="form-control mb-2" id="comment-input" placeholder="댓글 내용을 입력하세요"></textarea>
        <button @click="submitComment" class="btn btn-light">작성</button>
    </div>
</template>

<style scoped>
.comment-write {
    border-radius: 5px;
}

#comment-input {
    background-color: #f0f0f0;
}
</style>
