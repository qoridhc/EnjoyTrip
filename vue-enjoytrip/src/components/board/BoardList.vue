<script setup>
import BoardHeader from '@/components/board/list/BoardListHeader.vue';
import BoardTableHead from '@/components/board/list/BoardListTableHead.vue';
import BoardTableBody from '@/components/board/list/BoardListTableBody.vue';
import BoardPagination from '@/components/board/list/BoardListPagination.vue';
</script>

<template>
    <div class="d-flex flex-column">
        <div class="flex-shrink-0 row justify-content-center py-5">
            <hr class="mt-2" />
            <div class="col-lg-8 col-md-10 col-sm-12">
                <BoardHeader />

                <table class="table table-hover">
                    <BoardTableHead />
                    <BoardTableBody />
                </table>
            </div>
            <BoardPagination />
        </div>
    </div>
</template>

<style scoped></style>