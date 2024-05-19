// http://localhost/attraction/sido_info?sido_code=1

import axios from 'axios'

const api_url = import.meta.env.VITE_VUE_API_URL

function getSidoInfo(sido_code, success, fail){
    axios.get(`${api_url}/attraction/sido_info?sido_code=${sido_code}`)
    .then((response) => {
        success(response.data)
    })
    .catch((error) => {
        fail(error)
    })
}

export{ getSidoInfo }