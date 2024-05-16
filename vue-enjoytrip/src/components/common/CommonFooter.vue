<script setup>
import { ref } from 'vue';
import { useFooterStore } from '@/stores/footer';
const footerStore = useFooterStore()
</script>

<template>
    <footer class="py-4 mt-auto" :class="{ 'mt-auto': !footerStore.isFixed, 'fixed-bottom': footerStore.isFixed }">
        <div class="container px-5">
            <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                <div class="col-auto">
                    <div class="small m-0 ">Copyright &copy; SSAFY Heodongwon LeeSeongHyeon</div>
                </div>
                <div class="col-auto">
                    <a class="link-secondary small" href="#!">Privacy</a>
                    <span class=" mx-1">&middot;</span>
                    <a class="link-secondary small" href="#!">Terms</a>
                    <span class=" mx-1">&middot;</span>
                    <a class="link-secondary small" href="#!">Contact</a>
                </div>
            </div>
        </div>
    </footer>
</template>

<style scoped></style>