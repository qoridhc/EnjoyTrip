<script setup>
import { ref, computed } from 'vue';

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
];

const inputRoute = ref("");

const filteredAttractionList = computed(() => {
    const searchTerm = inputRoute.value.toLowerCase();
    return attractionList.filter(attraction => attraction.sido_name.toLowerCase().includes(searchTerm));
});

const handleEnter = () => {
    document.querySelector('#inputButton').click();
};
</script>

<template>
    <div class="collapse w-100" id="startInputCollapse">
        <div class="input-group">
            <input 
                type="text" 
                class="form-control border border-dark rounded-pill me-3 custom-input" 
                placeholder="어디로 여행을 떠나시나요?"
                v-model="inputRoute">
        </div>
        <ul v-if="inputRoute" class="list-group position-absolute">
            <li 
                class="list-group-item" 
                v-for="(sido, index) in filteredAttractionList" :key="index"
                data-bs-toggle="modal"
                data-bs-target="#centerModal"
            >
                {{ sido.sido_name }}
            </li>
        </ul>
    </div>
</template>

<style scoped>
.custom-input {
    padding-left: 20px;
    height: 50px;
    font-size: 1.2em; /* 텍스트 크기 조정 */
}
.btn {
    border: 1px solid black;
}
.list-group {
    max-height: 200px;
    overflow-y: auto;
    width: 26.6%;
    background-color: #f8f9fa;
    border: 1px solid #ddd;
    border-radius: 0.25rem;
    margin-top: 5px;
    padding: 0;
}
.list-group-item {
    border: none;
    padding: 10px 15px;
    background-color: #f8f9fa;
}
</style>
