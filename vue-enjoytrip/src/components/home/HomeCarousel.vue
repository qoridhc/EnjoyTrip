<script setup>

</script>

<template>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel w-100 h-100">
        <div class="carousel-inner d-block">
            <div class="carousel-item active">
                <img src="@/assets/img-main/carousel1.jpg" alt="First slide" />
            </div>
            <div class="carousel-item">
                <img src="@/assets/img-main/carousel2.jpg" alt="First slide" />
            </div>
            <div class="carousel-item">
                <img src="@/assets/img-main/carousel3.jpg" alt="First slide" />
            </div>
            <div class="carousel-item">
                <img src="@/assets/img-main/carousel4.jpg" alt="First slide" />
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <!-- <span class="visually-hidden"></span> -->
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <!-- <span class="visually-hidden"></span> -->
        </button>
    </div>
</template>

<style scoped></style>