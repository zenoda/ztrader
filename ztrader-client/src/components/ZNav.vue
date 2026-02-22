<script setup>
import {ref} from "vue";

const {items} = defineProps({items: {type: Object}})
const selectedItem = defineModel({type: Object})

function onNavItemClick(item) {
  selectedItem.value = item
}
</script>

<template>
  <div class="z-nav">
    <div class="z-nav__item" v-for="item in items" :key="item.name" @click="onNavItemClick(item)"
         :class="{selected: item === selectedItem}">
      <slot name="prefix" v-bind="item">
        <img v-if="item.icon" width="18" :src="item.icon"/>
      </slot>
      <slot name="default" v-bind="item">
        <span>{{ item.label }}</span>
      </slot>
    </div>
  </div>
</template>

<style lang="scss">
.z-nav {
  border: 0.5px solid rgba(0, 0, 0, 0.2);
  border-radius: 12px;
  padding: 2px;
  display: flex;
  column-gap: 4px;
  background-color: rgba(0, 0, 10, 0.03);

  .z-nav__item {
    display: flex;
    column-gap: 8px;
    align-items: center;
    border-radius: 8px;
    padding: 8px;
    cursor: pointer;

    &:hover {
      opacity: 0.5;
    }

    &.selected {
      background-color: rgba(255, 255, 255, 1);
      border: 0.5px solid rgba(0, 0, 0, 0.2);
      opacity: 1;
    }
  }
}
</style>