import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useRouteStore = defineStore('route', () => {

    const sido_code = ref()
    const sido_name_kor = ref("")
    const sido_name_eng = ref("")
    const sido_description = ref("")

    function setSidoInfo(data){
        sido_name_kor.value = data.sido_name_kor
        sido_name_eng.vlaue = data.sido_name_eng
        sido_description.value = data.sido_description
    }

    return { sido_code, sido_name_kor, sido_name_eng, sido_description, setSidoInfo }

})
