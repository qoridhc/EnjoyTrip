<script setup>
const attractionList = [
    { "sido_code": "1", "sido_name": "서울" }, 
    { "sido_code": "2", "sido_name": "인천" }, 
    { "sido_code": "3", "sido_name": "대전" }, 
    { "sido_code": "4", "sido_name": "대구" }, 
    { "sido_code": "5", "sido_name": "광주" }, 
    { "sido_code": "6", "sido_name": "부산" }, 
    { "sido_code": "7", "sido_name": "울산" }, 
    { "sido_code": "8", "sido_name": "세종특별자치시" }, 
    { "sido_code": "31", "sido_name": "경기도" }, 
    { "sido_code": "32", "sido_name": "강원도" }, 
    { "sido_code": "33", "sido_name": "충청북도" }, 
    { "sido_code": "34", "sido_name": "충청남도" }, 
    { "sido_code": "35", "sido_name": "경상북도" }, 
    { "sido_code": "36", "sido_name": "경상남도" }, 
    { "sido_code": "37", "sido_name": "전라북도" }, 
    { "sido_code": "38", "sido_name": "전라남도" }, 
    { "sido_code": "39", "sido_name": "제주도" }
]

</script>

<template>
    <div class="collapse w-100" id="startInputCollapse">
        <div class="input-group">
            <input class="form-control border border-dark rounded-pill me-3" type="text" placeholder="어디로 여행을 떠나시나요?"
                list="attraction-list">
            <button type="button" class="btn rounded-pill" data-bs-toggle="modal"
                data-bs-target="#centerModal">입력</button>

            <datalist id="attraction-list" >
                <option :value="sido.sido_name" v-for="(sido, index) in attractionList" :key="index">{{ sido.sido_name }}</option>
            </datalist>
        </div>
    </div>
</template>

<style scoped>
.btn {
    border: 1px solid black;
}
</style>