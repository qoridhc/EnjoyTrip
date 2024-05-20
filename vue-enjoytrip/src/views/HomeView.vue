<script setup>
import HomeCarousel from '@/components/home/HomeCarousel.vue';
import HomeMainText from '@/components/home/HomeMainText.vue';
import HomeInfo1 from '@/components/home/HomeInfo1.vue';
import HomeInfo2 from '@/components/home/HomeInfo2.vue';
import HomeInfo3 from '@/components/home/HomeInfo3.vue';
import HomeInfo4 from '@/components/home/HomeInfo4.vue';
import { useFooterStore } from '@/stores/footer';
useFooterStore().isFixed = false

</script>

<template>
    <main class="flex">

        <!-- Main -->
        <div class="container">
            <div class="row align-items-center justify-content-sm-between vh-100">
                <HomeMainText class="col-sm-12 col-md-5"/>
                <HomeCarousel class="col-sm-12 col-md-6 text-center" />
            </div>
        </div>

        <!-- 설명1. 지역별 관광지 정보 -->
        <HomeInfo1/>

        <!-- 여백 -->
        <div style="height: 250px;"></div>

        <!-- 설명2. 나의 여행 계획 -->
        <HomeInfo2 />

        <!-- 여백 -->
        <div style="height: 250px;"></div>

        <!-- 핫플 자랑하기 -->
        <HomeInfo3 />

        <!-- 리뷰 공유 -->
        <HomeInfo4 />
    </main>
</template>

<style scoped>
.gray{
    background-color: rgba(250, 250, 250, 0.9);
}
</style>