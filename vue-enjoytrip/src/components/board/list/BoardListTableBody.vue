<script setup>
const props = defineProps({
    article: Object,
})
</script>

<template>
    <tbody>
        <tr class="text-center">
            <th scope="row">{{props.article.articleNo}}</th>
            <td>
                <router-link 
                :to="{ name: 'board-detail', params: { articleNo: props.article.articleNo } }"
                   class="article-title link-dark"
                   style="text-decoration: none"
                > 
                    {{props.article.subject}}
                </router-link>
            </td>
            <td>{{props.article.userId}}</td>
            <td>{{props.article.registerTime}}</td>
        </tr>
    </tbody>
</template>