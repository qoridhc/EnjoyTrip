import { faI } from '@fortawesome/free-solid-svg-icons'
import axios from 'axios'

const api_url = import.meta.env.VITE_VUE_API_URL

// board-list 접속 시 상위 12개의 게시글 read
function getArticleList(page, pageSize, success, fail) {
    axios.get(`${api_url}/article/list`)
        .then(function (response) {
            success(response.data)
        })
        .catch(function (error) {
            fail(error)
        })
}

function searchArticleList(search, success, fail) {
    axios.get(`${api_url}/article/list?category=${search.category}&keyword=${search.keyword}`)
        .then(function (response) {
            success(response.data)
        })
        .catch(function (error) {
            fail(error)
        })
}

// board-detail 접속 시 articleNo 게시글 read
function getArticle(articleNo, success, fail) {
    axios.get(`${api_url}/article/view/${articleNo}`)
        .then(function (response) {
            success(response.data)
        })
        .catch(function (error) {
            fail(error)
        })
}

// board-write 글 작성
function writeArticle(article, success, fail) {
    axios({
        method: 'post',
        url: `${api_url}/article/write`,
        data: article,
    })
        .then((response) => {
            success(response)
        })
        .catch((error) => {
            fail(error)
        })
}

// board-detail 글 삭제
function removeArticle(articleNo, success, fail) {
    axios.get(`${api_url}/article/delete/${articleNo}`)
        .then((response) => {
            success(response)
        })
        .catch((error) => {
            fail(error)
        })
}

// board-detail 글 수정
function modifyArticle(article, success, fail) {
    axios({
        method: 'post',
        url: `${api_url}/article/modify`,
        data: article,
    })
    .then((response) => {
        success(response)
    })
    .catch((error) => {
        fail(error)
    })
}

// board-detail 댓글 조회
function getCommentList(articleNo, success, fail) {
    axios.get(`${api_url}/article/comment/${articleNo}`)
    .then((response) => {
        success(response.data)
    })
    .catch((error) => {
        fail(error)
    })
}

// board-comment-write 댓글 작성
function writeComment(commentData, success, fail){
    axios({
        url: `${api_url}/article/comment/write`,
        method: 'post',
        data: commentData,
    })
    .then((response)=>{
        success(response)
    })
    .catch((error)=>{
        fail(error)
    })
}

// board-comment 댓글 삭제
function deleteComment(memoNo, success, fail){
    axios({
        url: `${api_url}/article/comment/delete/${memoNo}`,
        method: 'get'
    })
    .then((response)=>{
        success(response)
    })
    .catch((error)=>{
        fail(error)
    })
}

function modifyComment(memo, success, fail){
    axios({
        url: `${api_url}/article/comment/modify`,
        method: 'post',
        data: memo
    })
    .then((response)=>{
        success(response)
    })
    .catch((error)=>{
        fail(error)
    })
}


export { getArticleList, searchArticleList, getArticle, writeArticle, removeArticle, modifyArticle
    , getCommentList, writeComment, deleteComment, modifyComment }