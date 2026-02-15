<script setup>
import {ArrowRight} from '@element-plus/icons-vue'
import ZMenuItem from "@/components/ZMenuItem.vue"
import DashboardIcon from "./assets/dashboard.svg"
import DataIcon from "./assets/data.svg"
import {ref, watch} from "vue";
import {useRoute} from "vue-router";

const breadcrumbs = ref([])
const route = useRoute()

function updateBreadcrumbs() {
  breadcrumbs.value = route.matched
}

watch(() => route.fullPath, updateBreadcrumbs)
</script>

<template>
  <el-container class="ztrader-platform">
    <el-aside width="255px" class="ztrader-aside">
      <el-header class="ztrader-aside-header">
        <img class="ztrader-logo" src="./assets/logo.webp" alt="logo"/>
        <h2>ZTrader</h2>
      </el-header>
      <el-main class="ztrader-aside-main">
        <z-menu-item :icon="DashboardIcon" text="工作台" :selected="route.name==='dashboard'" route="/#/dashboard"/>
        <z-menu-item :icon="DataIcon" text="数据管理" :selected="route.name==='data'" route="/#/data"/>
        <z-menu-item :icon="DataIcon" text="指标管理" :selected="route.name==='indicators'" route="/#/indicators"/>
      </el-main>
      <el-footer class="ztrader-aside-footer">Footer</el-footer>
    </el-aside>
    <el-container class="ztrader-body">
      <el-header class="ztrader-header">
        <img class="home-icon" src="./assets/home.svg" alt="home">
        <el-breadcrumb :separator-icon="ArrowRight">
          <el-breadcrumb-item v-for="item in breadcrumbs">{{ item.meta.title }}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>
      <el-main class="ztrader-main">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="scss">
html, body {
  height: 100%;
  margin: 0;
  font-family: Inter, 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
  'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
  font-size: var(--el-font-size-base);
  color: var(--el-text-color-primary);
}

a {
  text-decoration: none;
  color: var(--el-text-color-primary);
}

#app {
  height: 100%;
}

.ztrader-platform {
  height: 100%;

  .ztrader-aside {
    border-right: 1px solid var(--el-border-color);
    display: flex;
    flex-direction: column;

    .ztrader-aside-header {
      display: flex;
      align-items: center;
      justify-content: center;
      column-gap: 10px;
      height: 120px;
      border-bottom: 1px solid var(--el-border-color);

      .ztrader-logo {
        width: 48px;
      }
    }

    .ztrader-aside-main {
      display: flex;
      flex-direction: column;
      align-items: center;
      row-gap: 10px;

      .z-menu-item {
        width: 180px;
      }
    }

    .ztrader-aside-footer {
      border-top: 1px solid var(--el-border-color);
    }
  }

  .ztrader-header {
    border-bottom: 1px solid var(--el-border-color);
    display: flex;
    align-items: center;
    column-gap: 10px;

    img.home-icon {
      width: 18px;
    }
  }
}
</style>
