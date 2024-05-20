<script setup>

const props = defineProps({
    comment: Object,
})

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
        <div>{{ comment.comment }}</div>
    </div>
</template>

<style scoped>
</style>