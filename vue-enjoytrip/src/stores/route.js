import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useRouteStore = defineStore('route', () => {

    const sido_code = ref()

    return { sido_code }
    
})
