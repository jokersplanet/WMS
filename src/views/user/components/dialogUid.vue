<template>
    <el-dialog v-model="dialogVisible" title="账户信息 " width="30%" :before-close="handleClose" :show-close="false" >
        <el-form label-width="120px">
            <el-form-item label="编号">{{ tableData.number }}</el-form-item>
            <el-form-item label="姓名">{{ tableData.username }}</el-form-item>
            <el-form-item label="性别">{{ tableData.gender }}</el-form-item>
            <!-- <el-form-item label="生日">{{ tableData.birthday }}</el-form-item> -->
            <el-form-item label="电话">{{ tableData.telephone }}</el-form-item>
            <el-form-item label="部门">{{ tableData.name }}</el-form-item>
            <el-form-item label="职务">{{ tableData.duties }}</el-form-item>
            <el-form-item label="地址">{{ tableData.address }}</el-form-item>
            <!-- <el-form-item label="照片">
                <img :src="tableData.photo" width="100px" height="100px" />
            </el-form-item> -->
        </el-form>
    </el-dialog>
</template>
<script setup >
import { searchUid } from '../../../api/search/uid'
import { ref } from 'vue'
const props = defineProps({
    uidId: {
        type: String,
        default: "默认值"
    }
})


const tableData = ref({})
const params = {
    uid: props.uidId
}
const init = async () => {
    const res = await searchUid(params)
    tableData.value = res.data
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