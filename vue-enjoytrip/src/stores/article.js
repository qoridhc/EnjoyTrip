import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useArticleStore = defineStore('article', () => {
  const articles = ref([])
  const articlesCount = computed(()=>{ return articles.value.length })

  const readArticles = function(response){
    articles.value = response.data
  }

  const addArticle = function(article){
    articles.value.push(article)
  }

  return { articles, readArticles, addArticle }
})
