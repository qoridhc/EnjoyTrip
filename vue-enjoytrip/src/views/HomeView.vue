<script setup>
import HomeCarousel from '@/components/home/HomeCarousel.vue';
import HomeMainText from '@/components/home/HomeMainText.vue';
import HomeInfo1 from '@/components/home/HomeInfo1.vue';
import HomeInfo2 from '@/components/home/HomeInfo2.vue';
import HomeInfo3 from '@/components/home/HomeInfo3.vue';

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
        <div class="py-5 gray">
            <div class="container p-5 my-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-4 col-xl-4">
                        <div>
                            <h2 class="fw-bolder pt-5 mt-5">여행기와 리뷰를</h2>
                            <h2 class="fw-bolder">공유하세요</h2>
                            <h4 class="mt-5">먼저 다녀온 여행자의</h4>
                            <h4>생생한 리뷰와 꿀팁을 확인하세요.</h4>
                        </div>
                    </div>

                    <div class="col-lg-8 col-xl-6">
                        <img class="" style="width: 110%" src="@/assets/img-main/info1.png" alt="" />
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<style scoped>
.gray{
    background-color: rgba(250, 250, 250, 0.9);
}
</style>