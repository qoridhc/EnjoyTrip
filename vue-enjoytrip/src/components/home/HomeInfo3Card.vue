<script setup>
const props = defineProps({
    placeInfo: Object,
})
</script>

<template>
    <div class="col-sm-10 col-md-5 col-lg-5 col-xl-3 mb-5">
        <div class="card shadow border-0" style="height: 80%">
            <img class="card-img-top" style="height: 45%" :src="props.placeInfo.img" alt="..." v-if="props.placeInfo.img" />
            <div class="card-body p-4">
                <div class="badge bg-primary bg-gradient rounded-pill mb-2">Sights</div>
                <a class="text-decoration-none link-dark stretched-link" href="#!">
                    <h5 class="card-title mb-3 fw-bold">{{ props.placeInfo.title }}</h5>
                </a>
                <p class="card-text mb-0">
                    {{ props.placeInfo.content }}
                </p>
            </div>
            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                <div class="d-flex align-items-end justify-content-between">
                    <div class="d-flex align-items-center">
                        <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                        <div class="small">
                            <div class="fw-bold">{{ props.placeInfo.name }}</div>
                            <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>