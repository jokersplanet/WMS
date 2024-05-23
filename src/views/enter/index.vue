<template>
 <div class="full-background">
    <div class="centered">
        <el-button type="primary" @click="enter" class="transparent-button">
  <span class="button-text">进入登录页面</span>
</el-button>
    </div>
  </div>


</template>
<script setup >
import { RouterLink } from 'vue-router';
import { useStore } from 'vuex'
const store = useStore()

const enter = () => {
  store.dispatch('app/enter')
}


</script>
<style scoped>
.full-background {
  background-image: url('../../assets/2.jpg');
  background-size: cover;
  width: 100vw;
  height: 100vh;
}

.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.transparent-button {
  background-color: transparent;
  border: none;
  font-size: 60px;
}

.button-text {
  display: inline-block;
  background-color: rgba(255, 255, 255, 0.8);
  padding: 30px 50px;
  border-radius: 10px;
  color: rgb(106, 147, 147);
}
</style>






