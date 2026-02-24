<script setup>

import DashboardIcon from "@/assets/icons/dashboard.svg";
import DataIcon from "@/assets/icons/data.svg";
import {ArrowRight} from "@element-plus/icons-vue";
import ZMenuItem from "@/components/ZMenuItem.vue";
import ZUserInfo from "@/components/ZUserInfo.vue";
import {ref, watch} from "vue";
import {useRoute} from "vue-router";

const breadcrumbs = ref([])
const route = useRoute()

function updateBreadcrumbs() {
  breadcrumbs.value = route.matched
}

watch(() => route.fullPath, updateBreadcrumbs, {immediate: true})
</script>

<template>
  <el-container class="z-trader-platform">
    <el-aside width="255px" class="z-trader-aside">
      <el-header class="z-trader-aside-header">
        <img class="z-trader-logo" src="../../assets/images/logo.webp" alt="logo"/>
        <h2>ZTrader</h2>
      </el-header>
      <el-main class="z-trader-aside-main">
        <z-menu-item :icon="DashboardIcon" text="工作台" :selected="route.name==='dashboard'" route="/#/dashboard"/>
        <z-menu-item :icon="DataIcon" text="数据管理" :selected="route.name==='data'" route="/#/data"/>
        <z-menu-item :icon="DataIcon" text="指标管理" :selected="route.name==='indicators'" route="/#/indicators"/>
      </el-main>
      <el-footer class="z-trader-aside-footer">
        <z-user-info/>
      </el-footer>
    </el-aside>
    <el-container class="ztrader-body">
      <el-header class="ztrader-header">
        <img class="home-icon" src="../../assets/icons/home.svg" alt="home">
        <el-breadcrumb :separator-icon="ArrowRight">
          <el-breadcrumb-item v-for="item in breadcrumbs">{{ item.meta.title }}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>
      <el-main class="z-trader-main">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="scss">
.z-trader-platform {
  height: 100%;

  .z-trader-aside {
    border-right: 1px solid var(--el-border-color);
    display: flex;
    flex-direction: column;

    .z-trader-aside-header {
      display: flex;
      align-items: center;
      justify-content: center;
      column-gap: 10px;
      height: 120px;
      border-bottom: 1px solid var(--el-border-color);

      .z-trader-logo {
        width: 48px;
      }
    }

    .z-trader-aside-main {
      display: flex;
      flex-direction: column;
      align-items: center;
      row-gap: 10px;

      .z-menu-item {
        width: 180px;
      }
    }

    .z-trader-aside-footer {
      border-top: 1px solid var(--el-border-color);
      display: flex;

      .z-user-info {
        flex: 1;
      }
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

  .z-trader-main {
    background-color: rgba(0, 0, 0, 0.015);
    padding: 0;
  }
}
</style>