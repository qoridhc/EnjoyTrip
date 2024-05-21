<script setup>
import ShareRouteCard from './ShareRouteCard.vue';
import { ref, onMounted } from 'vue';
import { getSharedRoute } from '@/api/map';


const places = ref([]);
const page = ref(0);
const pageSize = 12;
const observer = ref(null);

const loadPlaces = () => {
    let newPlaces = [];
    getSharedRoute(
        page.value++, pageSize,
        function (data) {
            newPlaces = data
            places.value = [...places.value, ...newPlaces];
        },
        function (error) {
            console.log(error)
        }
    )
};

const loadMore = (entries) => {
    const entry = entries[0];
    if (entry.isIntersecting) {
        loadPlaces();
    }
};

onMounted(() => {
    observer.value = new IntersectionObserver(loadMore);
    observer.value.observe(document.querySelector('#scroll-target'));
});
</script>

<template>
    <div class="container">
        <div class="row">
            <ShareRouteCard  v-for="place in places" :key="place.id" :place="place" />
            <div id="scroll-target" style="height: 1px;"></div>
        </div>
    </div>
</template>

<style scoped>
.container {
    min-height: 85vh;
}

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