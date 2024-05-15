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
                <a href="${root}/article/view/${article.articleNo}" 
                   class="article-title link-dark"
                   :data-no="props.article.articleNo" 
                   style="text-decoration: none"
                > 
                    {{props.article.subject}}
                </a>
            </td>
            <td>{{props.article.userId}}</td>
            <td>{{props.article.registerTime}}</td>
        </tr>
    </tbody>
</template>