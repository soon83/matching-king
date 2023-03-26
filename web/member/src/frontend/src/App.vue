<template>
  <div id="app">
    <!-- <nav>
      <router-link to="/login">로그인</router-link> |
      <router-link to="/signup">회원가입</router-link>
    </nav>
    <router-view /> -->
    <CommonNavBar />
  </div>
</template>
<script>
import CommonNavBar from '@/components/CommonNavBar.vue';

export default {
  component: { CommonNavBar },
};
</script>
<style></style>
