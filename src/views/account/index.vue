<template>

  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index" />
  </el-table>
</template>


<script setup>
import { getAccount } from '../../api/account';
import { ref } from 'vue'
import { options } from './options1';
import { useStore } from 'vuex'
const store = useStore()
const queryFrom = ref({
  page: 1,
  count: 1,
  uid:localStorage.getItem('uid')
})
const tableData = ref([])

const initGetUsersList = async () => {
  const res = await getAccount(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData)
  console.log(store.state.username.uid)
}

initGetUsersList()
</script>
<style scoped>
.my-label {
  background: var(--el-color-success-light-9);
}

.my-content {
  background: var(--el-color-danger-light-9);
}
</style>