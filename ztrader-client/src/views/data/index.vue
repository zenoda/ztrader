<script setup>

import ZView from "@/components/ZView.vue";
</script>

<template>
  <z-view header="数据管理">
    <el-card class="large-border-radius" shadow="never">
      <z-view header="上市公司清单">
        <el-button>下载最新数据</el-button>
      </z-view>
      <el-divider/>
      <z-view header="日K线数据">
        <el-button>下载最新数据</el-button>
      </z-view>
    </el-card>
  </z-view>
</template>

<style scoped lang="scss">

</style>