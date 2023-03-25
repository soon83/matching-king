import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: '로그인',
    component: () => import('@/views/LoginPage.vue'),
  },
  {
    path: '/signup',
    name: '회원가입',
    component: () => import('@/views/SignupPage.vue'),
  },
  {
    path: '*',
    component: () => import('@/views/errors/PageNotFoundView.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
