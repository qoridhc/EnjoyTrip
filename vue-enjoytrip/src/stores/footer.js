import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useFooterStore = defineStore('footer', () => {
  const isFixed = ref(false)
  const isTopFixed = ref(true)

  return { isFixed, isTopFixed }
})
