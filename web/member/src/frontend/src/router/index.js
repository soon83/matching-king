import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: '로그인',
    component: () => import('@/views/auth/LoginPage.vue'),
  },
  {
    path: '/signup',
    name: '회원가입',
    component: () => import('@/views/auth/SignupPage.vue'),
  },
  {
    path: '/page-not-found',
    name: 'PageNotFound',
    component: () => import('@/views/errors/PageNotFound.vue'),
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/page-not-found',
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
