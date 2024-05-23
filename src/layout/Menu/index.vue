<template>
    <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo" default-active="2"
        text-color="#fff" @open="handleOpen" @close="handleClose" router>
        <el-menu-item index="order">
            <el-icon><icon-menu /></el-icon>
            <span>订单</span>
        </el-menu-item>
        <el-menu-item index="goods">
            <el-icon><icon-menu /></el-icon>
            <span>仓库</span>
        </el-menu-item>
        <el-sub-menu index="1">
            <template #title>
                <el-icon>
                    <location />
                </el-icon>
                <span>出入库</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="inGoods">入库记录</el-menu-item>
                <el-menu-item index="outGoods">出库记录</el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="2">
            <template #title>
                <el-icon>
                    <location />
                </el-icon>
                <span>收支</span>
            </template>
            <el-menu-item-group>
                <el-menu-item   index="income">收入记录</el-menu-item>
                <el-menu-item index="expenditure">支出记录</el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>
        <el-menu-item index="category">
            <el-icon>
                <setting />
            </el-icon>
            <span>货物类别</span>
        </el-menu-item>
        <el-menu-item index="department">
            <el-icon>
                <setting />
            </el-icon>
            <span>部门管理</span>
        </el-menu-item>
        <el-menu-item index="user">
            <el-icon>
                <setting />
            </el-icon>
            <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="scrap">
            <el-icon>
                <setting />
            </el-icon>
            <span>报废管理</span>
        </el-menu-item>
        <el-menu-item index="unit">
            <el-icon>
                <setting />
            </el-icon>
            <span>单位</span>
        </el-menu-item>
        <el-menu-item index="status">
            <el-icon>
                <setting />
            </el-icon>
            <span>状态</span>
        </el-menu-item>
    </el-menu>
</template>
<script setup>
</script>
<style scoped></style>    