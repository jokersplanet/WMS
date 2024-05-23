<template>
  <div class="login-container">
    <!-- <div class="img">
    <img src="../../../public/3.png" style="width: 230px;">

    </div> -->
    <!-- <RouterLink to="/enter">
      <el-button class="login-button">首页</el-button>
    </RouterLink> -->
    <el-form ref="formRef" :model="form" class="login-form" :rules="rules">
      <div class="title-container">
        <h3 class="title">用户登录</h3>
      </div>
      <el-form-item prop="uid">
        <el-icon :size="20" class="svg-container">
          <Edit />
        </el-icon>
        <el-input v-model="form.uid" />
      </el-form-item>
      <el-form-item prop="password">
        <el-icon :size="20" class="svg-container">
          <Edit />
        </el-icon>
        <el-input v-model="form.password" :type="passwordType" />
        <svg-icon :icon="passwordType === 'password' ? 'eye' : 'eye-open'" @click="changeType"></svg-icon>
        <svg viewBox="0 0 1024 1024" style="width: 20px;height: 20px;" xmlns="http://www.w3.org/2000/svg"
          data-v-ea893728="" @click="changeType">
          <path fill="currentColor"
            d="M512 160c320 0 512 352 512 352S832 864 512 864 0 512 0 512s192-352 512-352zm0 64c-225.28 0-384.128 208.064-436.8 288 52.608 79.872 211.456 288 436.8 288 225.28 0 384.128-208.064 436.8-288-52.608-79.872-211.456-288-436.8-288zm0 64a224 224 0 1 1 0 448 224 224 0 0 1 0-448zm0 64a160.192 160.192 0 0 0-160 160c0 88.192 71.744 160 160 160s160-71.808 160-160-71.744-160-160-160z">
          </path>
        </svg>

      </el-form-item>
      <el-button class="login-button" @click="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { Edit } from '@element-plus/icons-vue'
import { useStore } from 'vuex'
const store = useStore()
const form = ref({
  uid: '',
  password: ''
})


const rules = ref({
  uid: [
    {
      required: true,
      message: '请输入账号',
      trigger: 'change',
    },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'change',
    },
  ]
})
//s
const formRef = ref(null)
const handleLogin = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      store.dispatch('app/login', form.value);
      store.commit('setUid', form.value.uid);
      console.log(store.state.username.uid)
      console.log(form.value.uid)
    } else {
      console.log('error !!')
      return false
    }
  })
}
const passwordType = ref('password')
const changeType = () => {
  if (passwordType.value === 'password') {
    passwordType.value = 'text'
  } else {
    passwordType.value = 'password'
  }
}



</script >


<style lang="scss" scoped>
// $bg: #2d3a4b;
$bg:#5583b7;
$dark_gray: #889aa4;
$light_gray: #eee;
$cursor: #fff;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 200px 35px 0;
    margin: 0 auto;
    overflow: hidden;

    ::v-deep .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }

    ::v-deep .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;
      }
    }

    .login-button {
      width: 100%;
      box-sizing: border-box;
    }
  }

  .tips {
    font-size: 16px;
    line-height: 28px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }

    ::v-deep .lang-select {
      position: absolute;
      top: 4px;
      right: 0;
      background-color: white;
      font-size: 22px;
      padding: 4px;
      border-radius: 4px;
      cursor: pointer;
    }
  }

  .show-pwd {
    // position: absolute;
    // right: 10px;
    // top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
.img{
  display: flex;
  padding-right: 70px;
  flex-direction: row-reverse;
  padding-top: 20px;
}
</style>