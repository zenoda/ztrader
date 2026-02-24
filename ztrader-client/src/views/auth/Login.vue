<script setup>
import {ref} from "vue";
import visibleIcon from "@/assets/icons/visible.svg"
import invisibleIcon from "@/assets/icons/invisible.svg"
import {login} from "@/lib/auth/AuthClient.js";
import router from "@/router/index.js";
import ZPassword from "@/components/ZPassword.vue";

const passwordVisible = ref(false)
const credential = ref({type: 'PASSWORD', username: null, password: null})

async function onLoginButtonClick() {
  const sessionInfo = await login(credential.value)
  await router.push({name: 'home'})
}
</script>

<template>
  <el-container class="z-trader-login">
    <el-header>
      <div class="left-bar">
        <img class="z-trader-logo" src="../../assets/images/logo.webp" alt="logo"/>
        <h2>ZTrader</h2>
      </div>
      <div class="right-bar"></div>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24">
          <el-card>
            <template v-slot:header>
              <h2>欢迎使用ZTrader</h2>
            </template>
            <el-form>
              <el-form-item>
                <el-input size="large" placeholder="请输入用户名" v-model="credential.username">
                  <template v-slot:prefix>
                    <img width="24" src="../../assets/icons/user.svg">
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item>
                <z-password size="large" v-model="credential.password"/>
              </el-form-item>
            </el-form>
            <template v-slot:footer>
              <el-button type="primary" size="large" @click="onLoginButtonClick"><h3>登录</h3></el-button>
            </template>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<style lang="scss">
.z-trader-login.el-container {
  height: 100%;

  .el-header {
    display: flex;
    justify-content: space-between;
    box-shadow: 0 1px 5px -1px rgba(0, 0, 0, 0.15);

    .left-bar {
      display: flex;
      align-items: center;
      column-gap: 10px;

      .z-trader-logo {
        width: 48px;
      }
    }
  }

  .el-main {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.03);

    .el-card {
      width: 460px;
      border-radius: var(--el-border-radius-round);
      margin-bottom: 100px;

      .el-card__header {
        display: flex;
        justify-content: center;
        letter-spacing: 0.1em;
        border: none;
      }

      .el-card__body {
        .el-form {
          .el-form-item {
            .el-form-item__content {
              .el-input {
                .el-input__wrapper {
                  border-radius: 14px;
                  box-shadow: none;
                  background-color: hsl(240 4.76% 95.88%);

                  .el-input__inner {
                    height: 48px;
                  }
                }
              }
            }
          }
        }
      }

      .el-card__footer {
        display: flex;
        justify-content: center;
        border: none;

        .el-button {
          border-radius: 14px;
          width: 100%;
          height: 48px;
          letter-spacing: 0.15em;
        }
      }
    }
  }
}
</style>