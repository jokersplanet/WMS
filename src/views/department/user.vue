<template>
  <!-- <el-button type="primary" @click="handelDialogValue" class="add">添加记录</el-button> -->

  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
    </el-table-column>
  </el-table>

  <el-pagination
      v-model:current-page="queryFrom.page"
      :page-size="queryFrom.count"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, prev, pager, next"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />


</template>
<script setup>
import { ref } from 'vue' 
import { useRoute } from 'vue-router';

import { selectUserByDepartment } from '../../api/department/selectUser'
let total = ref(0); // 总数据量，用于计算分页
const route = useRoute();

const DepartmentUid = route.query.DepartmentUid;

const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetClass();
};

const queryFrom = ref({
  page: 1,
  count: 10,
  department:DepartmentUid
  
})
const tableData = ref([])

const initGetClass = async () => {
  const res = await selectUserByDepartment(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  total = res.data.total

}

initGetClass()

const options = [
{
    label: 'username',
    prop: 'username'
  },
  {
    label: 'department',
    prop: 'name'
  },
  {
    label: 'gender',
    prop: 'gender'
  },
  {
    label: 'telephone',
    prop: 'telephone'
  },
  {
    label: 'number',
    prop: 'number'
  },
  {
    label: 'addresss',
    prop: 'address'
  },


]
</script>
<style scoped>

</style>