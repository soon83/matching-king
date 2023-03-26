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
              class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>

        <div class="col-span-full">
          <label for="memberAge" class="block text-sm font-medium leading-6 text-gray-900"
            >나이</label
          >
          <div class="mt-2">
            <input
              id="memberAge"
              name="memberAge"
              type="number"
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
              class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
            >
              <option>선택</option>
              <option>남성</option>
              <option>여성</option>
            </select>
          </div>
        </div>

        <div class="col-span-full">
          <label for="memberMbti" class="block text-sm font-medium leading-6 text-gray-900"
            >MBTI</label
          >
          <div class="mt-2">
            <select
              id="memberMbti"
              name="memberMbti"
              class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
            >
              <option>선택</option>
              <option>ESFJ</option>
              <option>ESFP</option>
              <option>ENFJ</option>
              <option>ENFP</option>
              <option>ESTJ</option>
              <option>ESTP</option>
              <option>ENTJ</option>
              <option>ENTP</option>
              <option>ISFJ</option>
              <option>ISFP</option>
              <option>INFJ</option>
              <option>INFP</option>
              <option>ISTJ</option>
              <option>ISTP</option>
              <option>INTJ</option>
              <option>INTP</option>
            </select>
          </div>
        </div>
      </div>

      <!-- <div v-show="false" class="border-b border-gray-900/10 pb-12">
        <h2 class="text-base font-semibold leading-7 text-gray-900">누구랑 놀고 싶어요?</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">성별, 나이대, MBTI 를 골라요</p>

        <div class="mt-10 space-y-10">
          <fieldset>
            <legend class="text-sm font-semibold leading-6 text-gray-900">성별</legend>
            <div class="mt-6 space-y-6">
              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="memberMatchingCondition-gender"
                    name="memberMatchingCondition-gender"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="memberMatchingCondition-gender" class="font-medium text-gray-900"
                    >전체</label
                  >
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="male"
                    name="male"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="male" class="font-medium text-gray-900">남성</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="female"
                    name="female"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="female" class="font-medium text-gray-900">여성</label>
                </div>
              </div>
            </div>
          </fieldset>

          <fieldset>
            <legend class="text-sm font-semibold leading-6 text-gray-900">나이대</legend>
            <div class="mt-6 space-y-6">
              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="memberMatchingCondition-age"
                    name="memberMatchingCondition-age"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="memberMatchingCondition-age" class="font-medium text-gray-900"
                    >전체</label
                  >
                </div>
              </div>

              <div class="col-span-full">
                <label
                  for="memberMatchingCondition-minAge"
                  class="block text-sm font-medium leading-6 text-gray-900"
                  >몇 살 부터</label
                >
                <div class="mt-2">
                  <input
                    id="memberMatchingCondition-minAge"
                    name="memberMatchingCondition-minAge"
                    type="number"
                    class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                  />
                </div>
              </div>

              <div class="col-span-full">
                <label
                  for="memberMatchingCondition-maxAge"
                  class="block text-sm font-medium leading-6 text-gray-900"
                  >몇 살 까지</label
                >
                <div class="mt-2">
                  <input
                    id="memberMatchingCondition-maxAge"
                    name="memberMatchingCondition-maxAge"
                    type="number"
                    class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6"
                  />
                </div>
              </div>
            </div>
          </fieldset>

          <fieldset>
            <legend class="text-sm font-semibold leading-6 text-gray-900">MBTI</legend>
            <div class="mt-6 space-y-6">
              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="memberMatchingCondition-mbti"
                    name="memberMatchingCondition-mbti"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="memberMatchingCondition-mbti" class="font-medium text-gray-900"
                    >전체</label
                  >
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="esfj"
                    name="esfj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="esfj" class="font-medium text-gray-900">ESFJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="esfp"
                    name="esfp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="esfp" class="font-medium text-gray-900">ESFP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="enfj"
                    name="enfj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="enfj" class="font-medium text-gray-900">ENFJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="enfp"
                    name="enfp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="enfp" class="font-medium text-gray-900">ENFP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="estj"
                    name="estj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="estj" class="font-medium text-gray-900">ESTJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="estp"
                    name="estp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="estp" class="font-medium text-gray-900">ESTP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="entj"
                    name="entj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="entj" class="font-medium text-gray-900">ENTJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="entp"
                    name="entp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="entp" class="font-medium text-gray-900">ENTP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="isfj"
                    name="isfj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="isfj" class="font-medium text-gray-900">ISFJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="isfp"
                    name="isfp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="isfp" class="font-medium text-gray-900">ISFP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="infj"
                    name="infj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="infj" class="font-medium text-gray-900">INFJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="infp"
                    name="infp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="infp" class="font-medium text-gray-900">INFP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="istj"
                    name="istj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="istj" class="font-medium text-gray-900">ISTJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="istp"
                    name="istp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="istp" class="font-medium text-gray-900">ISTP</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="intj"
                    name="intj"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="intj" class="font-medium text-gray-900">INTJ</label>
                </div>
              </div>

              <div class="relative flex gap-x-3">
                <div class="flex h-6 items-center">
                  <input
                    id="intp"
                    name="intp"
                    type="checkbox"
                    class="h-4 w-4 rounded border-gray-300 text-teal-600 focus:ring-teal-600"
                  />
                </div>
                <div class="text-sm leading-6">
                  <label for="intp" class="font-medium text-gray-900">INTP</label>
                </div>
              </div>
            </div>
          </fieldset>
        </div>
      </div> -->

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
        </div>
      </div>

      <div>
        <button
          type="submit"
          class="group relative flex w-full justify-center rounded-md bg-teal-600 py-2 px-3 text-sm font-semibold text-white hover:bg-teal-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600"
        >
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
