<template lang="">
  <div>
    <form>
      <div class="space-y-12">
        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">나는 누구인가요?</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">
            MBTI 모르면 검사하고 오셈, MBTI 가 핵심이라고!
          </p>

          <div class="mt-10 grid grid-cols-1 gap-y-8 gap-x-6 sm:grid-cols-6">
            <div class="col-span-full">
              <label for="memberEmail" class="block text-sm font-medium leading-6 text-gray-900"
                >이메일</label
              >
              <div class="mt-2">
                <input
                  id="memberEmail"
                  name="memberEmail"
                  type="email"
                  autocomplete="email"
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
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
                  autocomplete="current-password"
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div class="col-span-full">
              <label
                for="memberPasswordConfirm"
                class="block text-sm font-medium leading-6 text-gray-900"
                >비밀번호 확인</label
              >
              <div class="mt-2">
                <input
                  id="memberPasswordConfirm"
                  name="memberPasswordConfirm"
                  type="password"
                  autocomplete="current-password"
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div class="col-span-full">
              <label for="memberGender" class="block text-sm font-medium leading-6 text-gray-900"
                >성별</label
              >
              <div class="mt-2">
                <select
                  id="memberGender"
                  name="memberGender"
                  autocomplete="country-name"
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                >
                  <option>남성</option>
                  <option>여성</option>
                </select>
              </div>
            </div>

            <div class="col-span-full">
              <label for="memberGender" class="block text-sm font-medium leading-6 text-gray-900"
                >MBTI</label
              >
              <div class="mt-2">
                <select
                  id="memberGender"
                  name="memberGender"
                  autocomplete="country-name"
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                >
                  <option>남성</option>
                  <option>여성</option>
                </select>
              </div>
            </div>
          </div>
        </div>

        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">누구랑 놀고 싶어요?</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">성별, 나이대, MBTI 를 골라요</p>

          <div class="mt-10 space-y-10">
            <fieldset>
              <legend class="text-sm font-semibold leading-6 text-gray-900">By Email</legend>
              <div class="mt-6 space-y-6">
                <div class="relative flex gap-x-3">
                  <div class="flex h-6 items-center">
                    <input
                      id="comments"
                      name="comments"
                      type="checkbox"
                      class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                    />
                  </div>
                  <div class="text-sm leading-6">
                    <label for="comments" class="font-medium text-gray-900">Comments</label>
                    <p class="text-gray-500">
                      Get notified when someones posts a comment on a posting.
                    </p>
                  </div>
                </div>
                <div class="relative flex gap-x-3">
                  <div class="flex h-6 items-center">
                    <input
                      id="candidates"
                      name="candidates"
                      type="checkbox"
                      class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                    />
                  </div>
                  <div class="text-sm leading-6">
                    <label for="candidates" class="font-medium text-gray-900">Candidates</label>
                    <p class="text-gray-500">Get notified when a candidate applies for a job.</p>
                  </div>
                </div>
                <div class="relative flex gap-x-3">
                  <div class="flex h-6 items-center">
                    <input
                      id="offers"
                      name="offers"
                      type="checkbox"
                      class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                    />
                  </div>
                  <div class="text-sm leading-6">
                    <label for="offers" class="font-medium text-gray-900">Offers</label>
                    <p class="text-gray-500">
                      Get notified when a candidate accepts or rejects an offer.
                    </p>
                  </div>
                </div>
              </div>
            </fieldset>
            <fieldset>
              <legend class="text-sm font-semibold leading-6 text-gray-900">
                Push Notifications
              </legend>
              <p class="mt-1 text-sm leading-6 text-gray-600">
                These are delivered via SMS to your mobile phone.
              </p>
              <div class="mt-6 space-y-6">
                <div class="flex items-center gap-x-3">
                  <input
                    id="push-everything"
                    name="push-notifications"
                    type="radio"
                    class="h-4 w-4 border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                  <label
                    for="push-everything"
                    class="block text-sm font-medium leading-6 text-gray-900"
                    >Everything</label
                  >
                </div>
                <div class="flex items-center gap-x-3">
                  <input
                    id="push-email"
                    name="push-notifications"
                    type="radio"
                    class="h-4 w-4 border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                  <label for="push-email" class="block text-sm font-medium leading-6 text-gray-900"
                    >Same as email</label
                  >
                </div>
                <div class="flex items-center gap-x-3">
                  <input
                    id="push-nothing"
                    name="push-notifications"
                    type="radio"
                    class="h-4 w-4 border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                  <label
                    for="push-nothing"
                    class="block text-sm font-medium leading-6 text-gray-900"
                    >No push notifications</label
                  >
                </div>
              </div>
            </fieldset>
          </div>
        </div>
      </div>
      <div class="flex items-center justify-between">
        <div class="flex items-center">
          <!-- <input
              id="remember-me"
              name="remember-me"
              type="checkbox"
              class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
            />
            <label for="remember-me" class="ml-2 block text-sm text-gray-900">Remember me</label> -->
        </div>

        <div class="text-sm">
          <router-link class="font-medium text-teal-600 hover:text-teal-500" to="/login"
            >로그인 페이지로,,</router-link
          >
          <!-- <a href="#" class="font-medium text-teal-600 hover:text-teal-500"
              >이미 가입을 하셨나요?</a
            > -->
        </div>
      </div>

      <div>
        <button
          type="submit"
          class="group relative flex w-full justify-center rounded-md bg-teal-600 py-2 px-3 text-sm font-semibold text-white hover:bg-teal-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600"
        >
          <!-- <span class="absolute inset-y-0 left-0 flex items-center pl-3">
              <LockClosedIcon
                class="h-5 w-5 text-teal-500 group-hover:text-teal-400"
                aria-hidden="true"
              />
            </span> -->
          회원가입
        </button>
      </div>
    </form>
  </div>
</template>
<script setup>
//import { PhotoIcon, UserCircleIcon } from '@heroicons/vue/24/solid';
</script>
<style lang=""></style>
