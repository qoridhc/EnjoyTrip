<script setup>
import { ref } from 'vue'
import HomeInfo3Card from './HomeInfo3Card.vue';
const hotplaceInfo = ref([
    {
        title: '연인과 데이트 코스 추천',
        content: '연인과 로맨틱한 시간을 보내시고 싶으신가요? 남산타워의 야경을 즐겨보세요.',
        name: '김싸피',
        img: '/src/assets/img-main/blog1.png',
    },
    {
        title: '수플레 맛집',
        content: '프랑스의 전통 간식 수플레 맛집을 알려드립니다.\n프랑스보다 더 맛있어요!',
        name: '노나현',
        img: '/src/assets/img-main/blog2.png',
    },
    {
        title: '가족과 주말 나들이',
        content: '이번주 주말 가족과 강정보에 다녀왔어요~',
        name: '허동원',
        img: '/src/assets/img-main/blog3.png',
    },
])
</script>

<template>
    <div class="container min-vw-100 px-5 my-5">

        <div class="row justify-content-center text-center">
            <h2 class="fw-bolder">핫플 자랑하기</h2>
            <p class="lead fw-normal text-muted mb-5 mt-4">
                요즘 뜨는 🔥핫🔥플레이스를 알아가요
            </p>
        </div>
        <div class="row gx-5 justify-content-center">
            <HomeInfo3Card v-for="(place, index) in hotplaceInfo" :key="index" :place-info="place"/>
        </div>
    </div>
</template>