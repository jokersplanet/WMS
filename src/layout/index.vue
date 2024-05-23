<template>
  <el-container class="app-wrapper">
    <el-aside width="210px" class="sidebar-container">
      <Menu />
    </el-aside>
    <el-container class="container">
      <el-header class="header">
        <Headers></Headers>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup>
import Menu from './Menu/index.vue'
// import variables from '../styles/variables.scss'
import Headers from './headers/index.vue'
</script>
<style lang="scss" scoped>
body{
  margin: 0;
}
.app-container {
  position: relative;
  width: 100%;
  height: 100%;
}
.app-wrapper{
  height: 100%;

}
.header{
  padding: 0;
}
.sidebar-container{
  height: 100%;
  background-color: #545c64;
}
.container {
  width: calc(100% - 210px);
  height: 100%;

  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  transition: all 0.28s;
  &.hidderContainer {
    width: calc(100% - 67px);  
  }
}

</style>
