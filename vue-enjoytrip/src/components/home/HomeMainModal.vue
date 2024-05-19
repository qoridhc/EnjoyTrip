<script setup>
import { ref, onMounted, defineEmits } from "vue";
import { getSidoInfo } from "@/api/route";
import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const { sido_code, sido_name_eng, sido_name_kor, sido_description } = storeToRefs(useRouteStore());
const { setSidoInfo } = useRouteStore()

onMounted(() => {
  console.log("onMounted(HomeMainModal.vue): Modal창 onMounted\nsido_code: ", sido_code.value);

  getSidoInfo(
    sido_code.value,
    function(data){
      console.log("onMounted(HomeMainModal.vue): 여행지 정보 확인\nresponse: ", data)
      setSidoInfo(data)
    },
    function(error){
      console.log("onMounted(HomeMainModal.vue): 여행지 정보 확인 중 에러\nerror: ", data)
    }
  )

});

const emit = defineEmits(["close"]);
const closeModal = () => {
  emit('close');
  console.log("closeModal(HomeMainModal.vue): 모달 닫기");
};

</script>

<template>
  <div 
    class="modal" 
    id="centerModal" 
    tabindex="-1" 
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    style="display: block;" 
    @click.self="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered modal-xl">
      <div class="modal-content rounded-modal">
        <button type="button" class="btn-close close-button" aria-label="Close" @click="closeModal"></button>
        <div class="modal-body d-flex justify-content-between">
          <div class="d-flex flex-column left-content">
            <h5 class="jeju-title">{{ sido_name_eng }}</h5>
            <h4 class="modal-subtitle">{{ sido_name_kor }}</h4>
            <div class="modal-description">
              {{ sido_description }}
            </div>
            <div class="modal-info row">
              <div class="info-item col">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="d-flex align-items-center">
                    <font-awesome-icon :icon="['fas', 'plane']" />
                    <div class="info-text">&nbsp;항공</div>
                  </div>
                </div>
                <div class="info-subtext">없음</div>
              </div>
              <div class="info-item col">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="d-flex align-items-center">
                    <font-awesome-icon :icon="['fas', 'passport']" />
                    <div class="info-text">&nbsp;비자</div>
                  </div>
                </div>
                <div class="info-subtext">없음</div>
              </div>
              <div class="info-item col">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="d-flex align-items-center">
                    <font-awesome-icon :icon="['fas', 'bolt']" />
                    <div class="info-text">&nbsp;전압</div>
                  </div>
                </div>
                <div class="info-subtext">콘센트</div>
                <div>220V</div>
              </div>
              <div class="info-item col">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="d-flex align-items-center">
                    <font-awesome-icon :icon="['fas', 'clock']" />
                    <div class="info-text">&nbsp;시차</div>
                  </div>
                </div>
                <div class="info-subtext">한국 대비</div>
                <div>없음</div>
              </div>
            </div>
            <router-link class="btn btn-dark" @click="closeModal" :to="{ name: 'myroute' }">일정 만들기</router-link>
          </div>
          <div class="right-content">
            <img src="/src/assets/img-main/carousel1.jpg" class="img-fluid modal-image" />
            <div class="button-group">
              <button class="btn btn-secondary">항공권 예매</button>
              <button class="btn btn-secondary">숙박 예약</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped>
/* 스타일 생략 */
.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  z-index: 1050;
  color: #000;
  opacity: 1;
}

@font-face {
  font-family: "NotoSansKR";
  src: url("/src/assets/fonts/NotoSansKR-Regular.ttf") format("truetype");
}

.modal-body {
  background-color: #f8f9fa;
  padding: 80px;
  border-radius: 20px;
  font-family: "NotoSansKR", sans-serif;
}

.modal-content {
  border-radius: 20px;
}

.left-content,
.right-content {
  flex: 1;
  padding: 20px;
}

.jeju-title {
  font-size: 1.25rem;
  color: #6c757d;
  margin-bottom: 10px;
}

.modal-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.modal-subtitle {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 15px;
}

.modal-description {
  font-size: 0.9rem;
  margin-bottom: 20px;
  line-height: 1.5;
}

.modal-info {
  font-size: 1rem;
  color: #000000;
  margin-bottom: 20px;
}

.modal-info .info-item {
  margin-bottom: 10px;
}

.modal-info .info-text {
  font-size: 1rem;
}

.modal-info .info-subtext {
  font-size: 0.75rem;
  color: #6c757d;
  margin-top: 9px;
}

.right-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal-image {
  max-width: 100%;
  height: auto;
  margin-bottom: 20px;
  border-radius: 5px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  gap: 10px;
  width: 100%;
}

.button-group .btn {
  flex: 1;
}

.info-item font-awesome-icon {
  margin-right: 5px;
}
</style>
