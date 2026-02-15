<script setup>
const {icon, text, route, selected} = defineProps({
  icon: {type: String},
  text: {type: String},
  route: {type: String},
  selected: {type: Boolean}
})
</script>

<template>
  <a class="z-menu-item" :class="{selected}" :href="route">
    <img :src="icon" alt="menu-item-icon"/>
    <div>{{ text }}</div>
  </a>
</template>

<style scoped lang="scss">
.z-menu-item {

  display: flex;
  align-items: center;
  border-radius: var(--el-border-radius-base);
  padding: 10px;
  cursor: pointer;
  column-gap: 10px;

  img {
    width: 18px;
    height: 18px;
  }

  &:hover {
    background-color: var(--el-fill-color-dark);
  }

  &.selected {
    background-color: var(--el-fill-color);
    cursor: default;
  }
}
</style>