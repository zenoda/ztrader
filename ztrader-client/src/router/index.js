import {createRouter, createWebHashHistory} from 'vue-router'
import {checkLoginStatus} from "@/lib/auth/AuthClient.js";

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/auth/Login.vue'),
        },
        {
            path: '/',
            name: 'admin',
            component: () => import('../views/admin/index.vue'),
            children: [
                {
                    path: '',
                    name: 'home',
                    redirect: '/dashboard'
                },
                {
                    path: '/dashboard',
                    name: 'dashboard',
                    component: () => import('../views/dashboard/index.vue'),
                    meta: {
                        title: '工作台'
                    }
                },
                {
                    path: '/profile',
                    name: 'profile',
                    component: () => import('../views/profile/index.vue'),
                    meta: {
                        title: '个人资料'
                    }
                },
                {
                    path: '/data',
                    name: 'data',
                    component: () => import('../views/data/index.vue'),
                    meta: {
                        title: '数据管理'
                    }
                },
                {
                    path: '/indicators',
                    name: 'indicators',
                    component: () => import('../views/indicators/index.vue'),
                    meta: {
                        title: '指标管理'
                    }
                }
            ]
        }
    ],
})
router.beforeEach(async (to, from, next) => {
    const isLoggedIn = await checkLoginStatus()
    if (isLoggedIn) {
        if (to.name === 'login') {
            next({name: 'home'})
        } else {
            next()
        }
    } else {
        if (to.name === 'login') {
            next()
        } else {
            next({name: 'login'})
        }
    }
})
export default router
