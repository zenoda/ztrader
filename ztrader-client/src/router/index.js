import {createRouter, createWebHashHistory} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            redirect: {path: '/dashboard'},
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
    ],
})

export default router
