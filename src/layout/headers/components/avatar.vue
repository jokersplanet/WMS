<template>
  <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar shape="square" :size="40" :src="squareUrl"></el-avatar>
    </span>
    <template #dropdown>
      <el-dropdown-menu router>
        <el-dropdown-item @click="logout">退出</el-dropdown-item>

        <router-link to="/account" class="no-underline">
          <el-dropdown-item>账户</el-dropdown-item>
        </router-link>

      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup>
import { ref } from 'vue'
import { useStore } from 'vuex'
import { getAccount } from '../../../api/account';
const queryFrom = ref({
  page: 1,
  count: 1,
  uid:localStorage.getItem('uid')
})
const tableData = ref([])
let gender = null
const initGetUsersList = async () => {
  const res = await getAccount(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  gender = res.data.records[0].gender
  console.log(gender)
  console.log(tableData)
  console.log(store.state.username.uid)
}

initGetUsersList()
const store = useStore()
const squareUrl = ref(
   gender === 'female' ? '2.png' : '1.png'
)

const logout = () => {
  store.dispatch('app/logout')
}

</script>

<style lang="scss" scoped>
.no-underline {
  text-decoration: none;
}


</style>
