<template>
    <el-dialog v-model="dialogVisible" title="货物信息" width="30%" :before-close="handleClose" :show-close="false" >
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="编号" prop="uid">
                <el-input v-model="tableData.uid" type="text" readonly />
            </el-form-item>
            <el-form-item label="名称" prop="name">
                <el-input v-model="tableData.name" />
            </el-form-item>
            <el-form-item label="增加数量" prop="count">
                <el-input v-model="data.count" />
            </el-form-item>
            <el-form-item label="价值" prop="value">
                <el-input v-model="data.value" />
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
import { ref, defineEmits, computed,onMounted } from 'vue'
import { updateGoods } from '../../../api/Goods/index'
// import { updateScrapRecords } from '../../../api/scrap/index'
import { insertExpenditure } from '../../../api/money/expenditure'
import { insertInboundRecords } from '../../../api/warehouse/inGoods'

import { ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()

const emits = defineEmits(['update:modelValue', 'initGetClass'])
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
    },
    count: {
        type: String,
        default: "默认值"
    },
    value: {
        type: Number,
        default: 0
    },
})


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





const data = ref({
    count: '',
    value: ''
})
const tableData = computed(() => {
    const totalCount = props.count + parseInt(data.value.count);
    const totalValue = (props.value + parseFloat(data.value.value)).toFixed(2);

    return {
        uid: props.uid,
        name: props.name,
        count: totalCount,
        value: totalValue
    }
})

const handleConfirm = async () => {
  ElMessageBox.confirm(
    '确定要入库货物',
    '警告',
    {
      confirmButtonText: '创建',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(() => {
      updateGoods(tableData.value)
      
        .then((res) => {
          console.log(res.data.value);
          emits('initGetClass');
          handleClose();

          ElMessageBox.confirm(
            '是否要同步支出记录？',
            '提示',
            {
              confirmButtonText: '是',
              cancelButtonText: '否',
              type: 'info',
            }
          )
            .then(async () => {
              const data1 = {
                time: formattedTime.value,
                value: (res.data.value - parseFloat(data.value.value)).toFixed(2),
              };

              // 执行insertExpenditure
             await insertExpenditure(data1);
              console.logI(res)
              ElMessage({
                type: 'success',
                message: '创建成功并同步支出记录',
              });
              const data2 = ref({
                        time: formattedTime,
                        warehouseUid: props.uid,
                        value: (res.data.value -  parseFloat(data.value.value)).toFixed(2),
                        goodsName: res.data.name,
                        goodsUid: res.data.uid,
                        count: res.data.count - parseInt( props.count),
                        price: res.data.price,
                    })
              // 执行insertInboundRecords
              await insertInboundRecords(data2.value);

              ElMessage({
                type: 'success',
                message: '入库记录添加成功',
              });
            })
            .catch(() => {
              ElMessage({
                type: 'success',
                message: '创建成功',
              });
              const data2 = ref({
                        time: formattedTime,
                        warehouseUid: props.uid,
                        value: (res.data.value - parseFloat(data.value.value)).toFixed(2),
                        goodsName: res.data.name,
                        goodsUid: res.data.uid,
                        count: res.data.count - parseInt(props.count),
                        price: res.data.price,
                    })
              // 执行insertInboundRecords
              insertInboundRecords(data2.value)
                .then(() => {
                  ElMessage({
                    type: 'success',
                    message: '入库记录添加成功',
                  });
                })
                .catch(() => {
                  ElMessage({
                    type: 'error',
                    message: '入库记录添加失败',
                  });
                });
            });
        })
        .catch(() => {
          ElMessage({
            type: 'error',
            message: '更新货物失败',
          });
        });
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '创建失败',
      });
    });
};


console.log(tableData.value)

</script>
<style scoped>
p {
    padding-bottom: 15px;
}
</style>