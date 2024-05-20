<script setup>
import { ref, onMounted } from 'vue';
import ShareRouteCard from './ShareRouteCard.vue';

const places = ref([]);
const page = ref(0);
const pageSize = 16;
const observer = ref(null);

const loadPlaces = () => {
    const newPlaces = [];
    for (let i = 0; i < pageSize; i++) {
        newPlaces.push({
            img: 'https://via.placeholder.com/150',
            title: `Place Title ${(page.value * pageSize) + i + 1}`,
            name: `Author ${(page.value * pageSize) + i + 1}`,
            country: `country ${(page.value * pageSize) + i + 1}`,
        });
    }
    places.value = [...places.value, ...newPlaces];
    page.value++;
};

const loadMore = (entries) => {
    const entry = entries[0];
    if (entry.isIntersecting) {
        loadPlaces();
    }
};

onMounted(() => {
    loadPlaces(); // 초기 로드

    observer.value = new IntersectionObserver(loadMore);
    observer.value.observe(document.querySelector('#scroll-target'));
});
</script>

<template>
    <div class="container">
        <div class="row">
            <div class="col-xl-3 col-lg-4 col-md-6 col-sm-10 my-5 gx-4 gy-5" v-for="place in places" :key="place.id">
                <div class="card shadow-sm border-0">
                    <img :src="place.img" class="card-img-top" alt="Place Image" />
                    <div class="card-body">
                        <p class="text-muted mb-1">{{ place.country }}</p>
                        <h5 class="card-title">{{ place.title }}</h5>
                        <p class="card-text text-muted">{{ place.name }}</p>
                    </div>
                </div>
            </div>

            <div id="scroll-target" style="height: 1px;"></div>
        </div>
    </div>
</template>

<style scoped>
.card {
    position: relative;
    overflow: hidden;
    border-radius: 15px;
}
.card-img-top {
    height: 200px;
    object-fit: cover;
}
.card-body {
    text-align: center;
}
.badge {
    font-size: 0.8rem;
    padding: 0.5em 1em;
    border-radius: 0.25rem;
}
</style>