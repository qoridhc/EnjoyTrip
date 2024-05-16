import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useArticleStore = defineStore('article', () => {
  const articles = ref([])
  const article = ref({})
  const articlesCount = computed(()=>{ return articles.value.length })

  const fetchArticles = function (response)  {
    articles.value = response.data
  }

  const addArticle = function(article) {
    articles.value.push(article)
  }

  const getArticle = function(articleNo) {
    return articles.value.filter(article => article.articleNo == articleNo)
  }

  const setNowArticle = function(articleNo) {
    article.value = getArticle(articleNo)[0]
    return article.value
  }

  return { article, articles, articlesCount, fetchArticles, addArticle, getArticle, setNowArticle }
})
