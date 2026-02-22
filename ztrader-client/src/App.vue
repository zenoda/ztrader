<script setup>
</script>

<template>
  <router-view/>
</template>

<style lang="scss">
:root {
  --z-trader-padding-card: 24px;
  --z-trader-border-card: 0.5px solid rgba(0, 0, 0, 0.1);
  --z-trader-border-radius-large: 14px;
  --z-trader-bg-color-white: rgb(255, 255, 255);
  --z-trader-bg-color-base: rgba(0, 0, 0, 0.015);
}

.flex {
  display: flex;
}

.justify-end {
  justify-content: flex-end;
}

.justify-center {
  justify-content: center;
}

.justify-start {
  justify-content: flex-start;
}

.direction-vertical {
  flex-direction: column;
}

.direction-horizontal {
  flex-direction: row;
}

.items-center {
  align-items: center;
}

.items-start {
  align-items: flex-start;
}

.items-end {
  align-items: flex-end;
}

html, body {
  height: 100%;
  margin: 0;
  font-family: Inter, 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
  'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
  font-size: var(--el-font-size-base);
  color: var(--el-text-color-primary);
}

* {
  box-sizing: border-box;
}

a {
  text-decoration: none;
  color: var(--el-text-color-primary);
}

#app {
  height: 100%;
}
</style>
