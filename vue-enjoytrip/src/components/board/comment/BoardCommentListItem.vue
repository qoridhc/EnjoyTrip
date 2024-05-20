<script setup>
import { onMounted, ref } from 'vue';
import { deleteComment, modifyComment } from '@/api/board';
import { useCommentStore } from '@/stores/comment';
import { useMemberStore } from '@/stores/member';


const commentStore = useCommentStore()
const modifing = ref(false)
const isShow = ref(false)

const props = defineProps({
    comment: Object,
})

onMounted(()=>{
    const memberStore = useMemberStore()
    if(memberStore.userInfo == null) return;
    if(memberStore.userInfo.id === 'admin') {
        isShow.value = true
        return;
    }
    if(memberStore.userInfo.id === props.comment.userId) {
        isShow.value = true
        return;
    }
})

function remove(){
    deleteComment(
        props.comment.memoNo,
        function(response){
            console.log("remove(BoardCommentListItem): 댓글 삭제 성공\nresponse: ", response)
            commentStore.increment()
        },   
        function(error){
            console.log("remove(BoardCommentListItem): 댓글 삭제 실패\nerror: ", error)
        }
    )
}

function modify(){
    if(modifing.value === false){
        modifing.value = true;
    }
    else{
        const memo = {
            memoNo: props.comment.memoNo,
            comment: props.comment.comment,
        }
        modifyComment(
            memo,
            function(response){
                console.log("modify(BoardCommentListItem) 댓글 수정 성공\nresponse: ", response)
                modifing.value = false;
                commentStore.increment()
            },
            function(error){
                console.log("modify(BoardCommentListItem) 댓글 수정 실패\nerror: ", error)
            }
        )
    }
}

</script>

<template>
    <div class="comment my-2 p-2">
        <div class="d-flex justify-content-between">
            <div>
                <img class="avatar me-2 float-md-start bg-light p-2"
                    src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
                <span class="fw-bold">{{ comment.userId }}</span>
            </div>

            <span class="text-muted">{{ comment.memoTime }}</span>
        </div>
        <div class="d-flex justify-content-between">
            <div v-if="!modifing">{{ comment.comment }}</div>
            <input v-if="modifing" type="text" v-model="comment.comment" class="form-control">
            <div v-if="isShow">   
                <button class="btn" @click.prevent="modify">수정</button>
                <button class="btn" @click.prevent="remove">삭제</button>
            </div>
        </div>

    </div>
</template>

<style scoped>

</style>