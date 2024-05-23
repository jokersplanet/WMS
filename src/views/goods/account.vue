<template>
    <h1 style="padding-left: 50px;">货物信息：</h1>
        <el-form label-width="120px">
            <el-form-item label="编号">{{ tableData.uid }}</el-form-item>
            <el-form-item label="名称">{{ tableData.name }}</el-form-item>
            <el-form-item label="单位名称">{{ tableData.unitName }}</el-form-item>
            <el-form-item label="大类型名称">{{ tableData.groupName }}</el-form-item>
            <el-form-item label="小类型名称">{{ tableData.className }}</el-form-item>
            <el-form-item label="总价">{{ tableData.value }}</el-form-item>     
            <el-form-item label="单价">{{ tableData.price }}</el-form-item>     
            <el-form-item label="数量">{{ tableData.count }}</el-form-item>     
            <el-form-item label="单位">{{ tableData.unitName }}</el-form-item>     
            <el-form-item label="所在仓库">{{ tableData.warehouseName }}</el-form-item>     
            <!-- <el-form-item label="最小限制">{{ tableData.lowerLimit }}</el-form-item>     
            <el-form-item label="最大限制">{{ tableData.upperLimit }}</el-form-item>      -->
        </el-form>
</template>

<script setup>
import { getGoods } from '../../api/Goods/index'
import { ref } from 'vue'
import { useRoute } from 'vue-router';
const route = useRoute();
const goodUid = route.query.uid;


const tableData = ref({})
const params = {
    page:1,
    count:1,
    uid:  goodUid
}
const init = async () => {
    const res = await getGoods(params)
    tableData.value = res.data.records[0]
    console.log(tableData.value)    
    console.log(res)
}
init()


</script>

<style scoped>

</style>