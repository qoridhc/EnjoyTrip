<!-- BoardListTableBody.vue -->

<script setup>
const props = defineProps({
    article: Object,
});
</script>

<template>
    <tbody>
        <tr class="text-center">
            <th scope="row" class="col-2">{{ props.article.articleNo }}</th>
            <td class="col-6">
                <router-link 
                    :to="{ name: 'board-detail', params: { articleNo: props.article.articleNo } }"
                    class="article-title link-dark"
                    style="text-decoration: none"
                > 
                    {{ props.article.subject }}
                </router-link>
            </td>
            <td class="col-2">{{ props.article.userId }}</td>
            <td class="col-2">{{ props.article.registerTime }}</td>
        </tr>
    </tbody>
</template>

<style scoped>

/* 기본 스타일 */
.article-title {
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

/* 작은 화면에 대한 스타일 */
@media (max-width: 768px) {
    .article-title {
        font-size: 14px;
    }
}
</style>
