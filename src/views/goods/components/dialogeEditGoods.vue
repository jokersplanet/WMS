<template>
    <el-dialog v-model="dialogVisible" title="货物信息" width="30%" :before-close="handleClose" :show-close="false" >
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="编号" prop="uid">
                <el-input v-model="tableData.uid"   type="text" readonly/>
            </el-form-item>
            <el-form-item label="名称" prop="name">
                <el-input v-model="tableData.name"  type="text" readonly/>
            </el-form-item>
            <el-form-item label="价值" prop="value">
                <el-input v-model="tableData.value" type="value" />
            </el-form-item>
            <el-form-item label="备注" prop="notes">
                <el-input v-model="tableData.notes" type="notes" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="handleClose">取消</el-button>
                <el-button type="primary" @click="handleConfirm">
                    确认
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script setup >
import { ref,defineEmits } from 'vue'
import  { updateGoods }  from '../../../api/Goods/index'
// import { updateScrapRecords } from '../../../api/scrap/index'

import { ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()

const emits = defineEmits(['update:modelValue','initGetClass'])
const handleClose = () => {
    emits('update:modelValue', false)
}

const props = defineProps({
    uid: {
        type: String,
        default: "默认值"
    },
    name: {
        type: String,
        default: "默认值"
    }
})

const tableData = ref({
    uid:props.uid,
    name:props.name,
})

const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要修改单位',
        '警告',
        {
            confirmButtonText: '修改',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {
            
            const res = await updateGoods(tableData.value)
            console.log(res)
            emits('initGetClass')
            handleClose()
            ElMessage({
                type: 'success',
                message: '修改成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '修改失败',
            })
        })

}
console.log(tableData.value)

</script>
<style scoped>
p {
    padding-bottom: 15px;
}
</style>