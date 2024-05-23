<template>
  <el-button type="primary" @click="handelDialogValue" class="add">添加货物</el-button>

  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
      <template #default="{ row }" v-if="item.prop === 'count'">
        {{ row.count + row.unitName }}
      </template>

      <template #default="{ row }" v-if="item.prop === 'address'">
        <el-button size="small" @click="Edit(row)">入库</el-button>
        <el-button size="small" @click="Edit1(row)">出库</el-button>
        <el-button size="small" type="warning" @click="scrap(row)">报废</el-button>

        <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
      </template>
      <template #default="{ row }" v-if="item.prop === 'name'">
        <router-link :to="{
          path: '/goodAccount',
          query: { uid: row.uid }
        }">

          <a @click="SearchCountValue(row)" style=" cursor: pointer;">{{ row.name }}</a>
        </router-link>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination v-model:current-page="queryFrom.page" :page-size="queryFrom.count" :small="small" :disabled="disabled"
    :background="background" layout="total, prev, pager, next" :total="total" @size-change="handleSizeChange"
    @current-change="handleCurrentChange" />


  <DialogEditGoods :name="name" :uid="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetClass="initGetClass" />
  <Dialog :uid="warehouseUid" v-model="dialogVisible" v-if="dialogVisible" @initGetClass="initGetClass" />
  <DialogInGoods :value="value" :count="count" :name="name" :uid="AccountUid" v-model="dialogIn" v-if="dialogIn"
    @initGetClass="initGetClass" />
  <DialogOutGoods :value="value" :count="count" :name="name" :uid="AccountUid" v-model="dialogOut" v-if="dialogOut"
    @initGetClass="initGetClass" />
  <DialogScrap :value="value" :count="count" :name="name" :uid="AccountUid" v-model="dialogScrap" v-if="dialogScrap"
    @initGetClass="initGetClass" />
  <DialogAccount :uid="AccountUid" v-model="dialogAccount" v-if="dialogAccount" />
</template>     
<script setup>

import { ref, onMounted } from 'vue'
import { getGoods, deleteGoods } from '../../api/Goods/index'
import DialogEditGoods from './components/dialogeEditGoods.vue'
import { useI18n } from 'vue-i18n'
import { useRoute } from 'vue-router';
import Dialog from './components/dialog.vue'
import DialogInGoods from './components/dialoginGoods.vue'
import DialogScrap from './components/dialogscrap.vue'
import DialogOutGoods from './components/dialogoutGoods.vue'
import DialogAccount from './components/dialogUid.vue'
let total = ref(0); // 总数据量，用于计算分页

const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetClass();
};



const dialogAccount = ref(false)

//查看全部信息
const SearchCountValue = (row) => {
  dialogAccount.value = true
  console.log(row.uid)
  AccountUid.value = row.uid
}


//添加
const dialogVisible = ref(false)
const handelDialogValue = () => {
  dialogVisible.value = true
}

const route = useRoute();
const warehouseUid = route.query.warehouseUid;
const i18n = useI18n()
const AccountUid = ref(null)
const name = ref(null)
const count = ref(null)
const value = ref(null)

//修改
const dialogIn = ref(false)

const Edit = (row) => {
  dialogIn.value = true
  console.log(row.uid)
  AccountUid.value = row.uid
  name.value = row.name
  count.value = row.count
  value.value = row.value
}

const dialogOut = ref(false)

const Edit1 = (row) => {
  dialogOut.value = true
  console.log(row.uid)
  AccountUid.value = row.uid
  name.value = row.name
  count.value = row.count
  value.value = row.value
}

const tableData = ref([])

const queryFrom = ref({
  page: 1,
  count: 10,
  warehouseUid: warehouseUid

})


const initGetClass = async () => {
  const res = await getGoods(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  console.log(warehouseUid)
  total = res.data.total
  
  console.log(total)
}

initGetClass()
const props = defineProps({
  uid: {
    type: String,
    default: "默认值"
  }
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




const options = [
  {
    label: 'name',
    prop: 'name'
  },
  {
    label: 'price',
    prop: 'price'
  },
  {
    label: 'count',
    prop: 'count'
  },
  {
    label: 'value',
    prop: 'value'
  },

  {
    label: 'address',
    prop: 'address'
  },
]


// 删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.deleteGoods'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteGoods(data)
      console.log(res)
      ElMessage({
        type: 'success',
        message: '删除成功',
      })

      initGetClass()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}
const dialogScrap = ref(false)
//报废
const scrap = async (row) => {
  dialogScrap.value = true
  AccountUid.value = row.uid
  name.value = row.name
  count.value = row.count
  value.value = row.value
}



</script>
<style scoped>
.input-with-select {
  background-color: var(--el-fill-color-blank);
}


::v-deep .el-input__suffix {
  align-items: center;
}


.header {
  padding-bottom: 16px;
  box-sizing: border-box;
}

.add {
  margin-bottom: 20px;
}

/*设置点击前的样式 */
a {
  text-decoration: none;
  color: #000;
}

/*设置点击后的样式 */
.router-link-active {
  text-decoration: none;
}
</style>