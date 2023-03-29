<template lang="">
  <div>
    <form class="mt-8 space-y-6">
      <input type="hidden" name="remember" value="true" />
      <div class="mt-10 grid grid-cols-1 gap-y-4 gap-x-6 sm:grid-cols-6">
        <div class="col-span-full">
          <label for="memberEmail" class="block text-sm font-medium leading-6 text-gray-900"
            >이메일</label
          >
          <div class="mt-2">
            <input
              id="memberEmail"
              name="memberEmail"
              type="email"
              class="block w-full rounded-md border-2 py-1.5 text-gray-900 shadow-sm placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
              :value="memberEmail"
            />
          </div>
        </div>
        <div class="col-span-full">
          <label for="memberPassword" class="block text-sm font-medium leading-6 text-gray-900"
            >비밀번호</label
          >
          <div class="mt-2">
            <input
              id="memberPassword"
              name="memberPassword"
              type="password"
              class="block w-full rounded-md border-2 py-1.5 text-gray-900 shadow-sm placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
              :value="memberPassword"
            />
          </div>
        </div>
      </div>

      <div class="flex items-center justify-between">
        <div class="flex items-center">
          <input
            id="remember-me"
            name="remember-me"
            type="checkbox"
            class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600"
          />
          <label for="remember-me" class="ml-2 block text-sm text-gray-900">로그인 유지하기</label>
        </div>

        <div class="text-sm">
          <!-- <a href="#" class="font-medium text-indigo-600 hover:text-indigo-500"
        >Forgot your password?</a
      > -->
          <router-link to="/signup" class="font-medium text-indigo-600 hover:text-indigo-500"
            >회원가입 페이지로,,</router-link
          >
        </div>
      </div>

      <div>
        <button
          type="submit"
          class="group relative flex w-full justify-center rounded-md bg-indigo-600 py-2 px-3 text-sm font-semibold text-white hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
        >
          <span class="absolute inset-y-0 left-0 flex items-center pl-3">
            <LockClosedIcon
              class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400"
              aria-hidden="true"
            />
          </span>
          로그인
        </button>
      </div>
    </form>
  </div>
</template>
<script setup>
import { toRefs, defineProps } from 'vue';
import { LockClosedIcon } from '@heroicons/vue/20/solid';

const props = defineProps({
  memberEmail: String,
  memberPassword: String,
});

const { memberEmail, memberPassword } = toRefs(props);
console.log({ memberEmail, memberPassword });
</script>
<style lang=""></style>
