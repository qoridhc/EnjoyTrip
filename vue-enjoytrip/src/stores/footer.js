import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useFooterStore = defineStore('footer', () => {
  const isFixed = ref(true)

  return { isFixed }
})
