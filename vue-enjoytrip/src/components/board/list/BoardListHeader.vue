<script setup>

</script>

<template>
    <div class="row align-self-center mb-2">
        <div class="col-md-7 offset-3">
            <form class="d-flex">
                <select id="key" class="form-select form-select-sm ms-5 me-1 w-50" aria-label="검색조건">
                    <option selected>검색조건</option>
                    <option value="articleno">글번호</option>
                    <option value="subject">제목</option>
                    <option value="userid">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" placeholder="검색어..." />
                    <button class="btn btn-dark" type="button">검색</button>
                </div>
            </form>
        </div>
        <div class="col-md-2 text-start">
            <button type="button" id="btn-mv-register" class="btn btn-dark btn-sm">
                글쓰기
            </button>
        </div>
    </div>
</template>