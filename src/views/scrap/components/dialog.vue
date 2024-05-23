<template>
    <el-dialog :model-value="dialogVisible" title="添加报废记录" width="40%" :@close="handleClose" :show-close="false" >
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="名称" prop="name" >
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="时间" prop="time" >
                <el-input v-model="form.time" type="text" readonly/>
            </el-form-item>
            <el-form-item label="货物名称" prop="goodsName" >
                <el-input v-model="form.goodsName" />
            </el-form-item>
            <el-form-item label="价值" prop="value" >
                <el-input v-model="form.value" />
            </el-form-item>
            <el-form-item label="单价" prop="price" >
                <el-input v-model="form.price" />
            </el-form-item>
            <el-form-item label="报废编号" prop="warehouseUid" >
                <el-input v-model="form.warehouseUid" />
            </el-form-item>
            <el-form-item label="货物编号" prop="goodsUid" >
                <el-input v-model="form.goodsUid" />
            </el-form-item>
            <el-form-item label="数量" prop="count" >
                <el-input v-model="form.count" />
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
<script setup>
import { defineEmits, ref,onMounted } from 'vue'
import { insertScrapRecords } from '../../../api/scrap/index'
import  { ElMessage }  from 'element-plus'
const formattedTime = ref('');

onMounted(() => {
  getCurrentTime();
});

const getCurrentTime = () => {
  const now = new Date();
  const year = now.getFullYear();
  const month = padNumber(now.getMonth() + 1);
  const day = padNumber(now.getDate());
  const hours = padNumber(now.getHours());
  const minutes = padNumber(now.getMinutes());
  const seconds = padNumber(now.getSeconds());

  formattedTime.value = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  setTimeout(getCurrentTime, 1000); // 更新时间，每秒执行一次
};

const padNumber = (number) => {
  return number.toString().padStart(2, '0');
};

const emits = defineEmits(['update:modelValue','initGetClass'])
const handleClose = () => {
    emits('update:modelValue', false)
}
const fromRef = ref(null)
const form = ref({
        name:'',
        time:formattedTime,
        goodsName:'',
        value:'',
        price:'',
        warehouseUid:'',
        goodsUid:'',
        count:''
    })
const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要创建报废记录',
        '警告',
        {
            confirmButtonText: '创建',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {
            
            const res = await insertScrapRecords(form.value)
            console.log(res)
            emits('initGetClass')
            handleClose()
            ElMessage({
                type: 'success',
                message: '创建成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '创建失败',
            })
        })
}


const rules = ref({
    username: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],
    password: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],
    email: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        {
          type: 'email',
          message: '输入正确邮箱',
          trigger: ['blur', 'change'],
        }
    ],
    mobile: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],

})
</script>
<style scoped></style>