<script setup>
import HomeCarousel from '@/components/home/HomeCarousel.vue';
import HomeMainText from '@/components/home/HomeMainText.vue';
import HomeInfo1 from '@/components/home/HomeInfo1.vue';
import HomeInfo2 from '@/components/home/HomeInfo2.vue';
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
        <!-- <div class="gray py-5" style="height:auto">
            <div class="container p-5 my-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-4 col-xl-4">
                        <div class="py-5">
                            <h2 class="fw-bolder pt-5 mt-5">나의 여행 계획</h2>
                            <h4 class="mt-5">여행 루트를 만들고</h4>
                            <h4 class="mt-5">최적의 경로로 이동해요</h4>
                        </div>
                    </div>

                    <div class="col-lg-8 col-xl-6 py-5">
                        <img class="" style="width: 110%" src="@/assets/img-main/mytrip.png" alt="" />
                    </div>
                </div>
            </div>
        </div> -->



        <!-- 핫플 자랑하기 -->
        <section class="pt-5 pb-3">
            <div class="container px-5 my-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <div class="text-center">
                            <h2 class="fw-bolder">핫플 자랑하기</h2>
                            <p class="lead fw-normal text-muted mb-5 mt-4">
                                요즘 뜨는 🔥핫🔥플레이스를 알아가요
                            </p>
                        </div>
                    </div>
                </div>
                <div class="row gx-5">
                    <div class="col-lg-4 mb-5">
                        <div class="card shadow border-0" style="height: 80%">
                            <img class="card-img-top" style="height: 45%" src="@/assets/img-main/blog1.png" alt="..." />
                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">Sights</div>
                                <a class="text-decoration-none link-dark stretched-link" href="#!">
                                    <h5 class="card-title mb-3 fw-bold">연인과 데이트 코스 추천</h5>
                                </a>
                                <p class="card-text mb-0">
                                    연인과 로맨틱한 시간을 보내시고 싶으신가요? 남산타워의 야경을 즐겨보세요.

                                </p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">
                                        <img class="rounded-circle me-3"
                                            src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                        <div class="small">
                                            <div class="fw-bold">김싸피</div>
                                            <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 mb-5">
                        <div class="card shadow border-0" style="height: 80%">
                            <img class="card-img-top" style="height: 45%" src="@/assets/img-main/blog2.png" alt="..." />
                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">Restaurant</div>
                                <a class="text-decoration-none link-dark stretched-link" href="#!">
                                    <h5 class="card-title mb-3 fw-bold">수플레 맛집</h5>
                                </a>
                                <p class="card-text mb-0">
                                    프랑스의 전통 간식 수플레 맛집을 알려드립니다.
                                    프랑스보다 더 맛있어요!
                                </p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">
                                        <img class="rounded-circle me-3"
                                            src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                        <div class="small">
                                            <div class="fw-bold">노나현</div>
                                            <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 mb-5">
                        <div class="card shadow border-0" style="height: 80%">
                            <img class="card-img-top" style="height: 45%" src="@/assets/img-main/blog3.jpg" alt="..." />
                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">Sights</div>
                                <a class="text-decoration-none link-dark stretched-link" href="#!">
                                    <h5 class="card-title mb-3 fw-bold">가족과 주말 나들이</h5>
                                </a>
                                <p class="card-text mb-0">
                                    이번주 주말 가족과 강정보에 다녀왔어요~
                                </p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">
                                        <img class="rounded-circle me-3"
                                            src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                        <div class="small">
                                            <div class="fw-bold">허동원</div>
                                            <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


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