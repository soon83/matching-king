<template>
  <div class="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="w-full max-w-md space-y-8">
      <div>
        <!-- <img
          class="mx-auto h-12 w-auto"
          src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
          alt="Your Company"
        /> -->
        <h2 class="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">
          원하는 MBTI 랑 놀기
        </h2>
        <p class="mt-2 text-center text-sm text-red-800">절대로 회원가입을 하지마세요,,</p>
      </div>
      <LoginForm :member-email="member.email" :member-password="member.password" />
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import LoginForm from '@/components/auth/LoginForm.vue';

const member = reactive({
  email: '2601948@gmail.com',
  password: '1234',
});

() => {
  member;
};
</script>
