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

function deleteRoute(routeId, success, fail){
    axios.get(`${api_url}/attraction/route/delete/${routeId}`)
    .then((response) => {
        success(response.data)
    })
    .catch((error) => {
        fail(error)
    })
}

function shareRoute(routeId, success, fail){
    axios.get(`${api_url}/attraction/route/share/${routeId}`)
    .then((response) => {
        success(response.data)
    })
    .catch((error) => {
        fail(error)
    })
}

function stopShareRoute(routeId, success, fail){
    axios.get(`${api_url}/attraction/route/stop/${routeId}`)
    .then((response) => {
        success(response.data)
    })
    .catch((error) => {
        fail(error)
    })
}

export{ getSidoInfo, deleteRoute, shareRoute, stopShareRoute }