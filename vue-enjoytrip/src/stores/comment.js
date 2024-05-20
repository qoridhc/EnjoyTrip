import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCommentStore = defineStore('comment', () => {
  const key = ref(0)
  function increment() {
    key.value++
  }
  return { key, increment }
})
