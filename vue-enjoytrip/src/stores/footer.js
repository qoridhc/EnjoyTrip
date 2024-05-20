import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useFooterStore = defineStore('footer', () => {
  const isFixed = ref(false)

  return { isFixed }
})
