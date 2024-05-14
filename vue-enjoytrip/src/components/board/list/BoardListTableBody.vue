<template>
    <tbody>
        <tr class="text-center">
            <th scope="row">${article.articleNo}</th>
            <td class="text-start">
                <a href="${root}/article/view/${article.articleNo}" class="article-title link-dark"
                    data-no="${article.articleNo}" style="text-decoration: none">
                    ${article.subject}
                </a>
            </td>
            <td>${article.userId}</td>
            <td>${article.registerTime}</td>
        </tr>
    </tbody>
</template>