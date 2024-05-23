<template>
    <el-dialog v-model="dialogVisible" title="货物信息 " width="30%" :before-close="handleClose" :show-close="false" >
        <el-form label-width="120px">
            <el-form-item label="编号">{{ tableData.uid }}</el-form-item>
            <el-form-item label="名称">{{ tableData.name }}</el-form-item>
            <el-form-item label="单位名称">{{ tableData.unitName }}</el-form-item>
            <el-form-item label="大类型名称">{{ tableData.groupName }}</el-form-item>
            <el-form-item label="小类型名称">{{ tableData.className }}</el-form-item>
            <el-form-item label="仓库名称">{{ tableData.warehouseName }}</el-form-item>            
        </el-form>
    </el-dialog>
</template>
<script setup >
import { getGoods } from '../../../api/Goods/index'
import { ref } from 'vue'
const props = defineProps({
    uid: {
        type: String,
        default: "默认值"
    }
})


const tableData = ref({})
const params = {
    page:1,
    count:1,
    uid: props.uid
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
p {
    padding-bottom: 15px;
}
</style>