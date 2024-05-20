<script setup>
const props = defineProps({
    placeInfo: Object,
});
</script>

<template>
    <div class="col-sm-8 col-md-5 col-lg-5 col-xl-3 mb-3">
        <div class="card shadow border-0">

            <!-- 이미지 -->
            <img class="card-img-top" style="height: 60%" :src="props.placeInfo.img" alt="..." v-if="props.placeInfo.img" />

            <!-- 타이틀 -->
            <div class="card-body">
                <a class="text-decoration-none link-dark stretched-link" href="#!">
                    <h5 class="card-title fw-bold">{{ props.placeInfo.title }}</h5>
                </a>
            </div>

            <!-- 작성자 및 기타 내용 -->
            <div class="card-footer bg-transparent border-top-0 d-flex-column justify-content-start">
                <div class="fw-bold">{{ props.placeInfo.name }}</div>
                <div>March 23, 2023</div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card-title {
    line-height: 0.1;
    margin-bottom: 0.5rem;
}
</style>
