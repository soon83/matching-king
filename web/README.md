## vue cli installation

- Link: [Vue CLI](https://cli.vuejs.org/#getting-started)

install:

```
npm install -g @vue/cli
```

## create vue project

create project:

```
vue create vue-project
```

select Manually select features:

```
Vue CLI v5.0.8
? Please pick a preset:
  Default ([Vue 3] babel, eslint)
  Default ([Vue 2] babel, eslint)
❯ Manually select features
```

final fetures:

```
Vue CLI v5.0.8
? Please pick a preset: Manually select features
? Check the features needed for your project: Babel, Linter, Unit
? Choose a version of Vue.js that you want to start the project with 2.x
? Pick a linter / formatter config: Prettier
? Pick additional lint features: Lint on save
? Pick a unit testing solution: Jest
? Where do you prefer placing config for Babel, ESLint, etc.? In dedicated config files
? Save this as a preset for future projects? (y/N) y
```

## setting prettier

 - `.eslintrc.js` 파일내용 수정

    ```javascript
    module.exports = {
      root: true,
      env: {
        node: true,
      },
      extends: [
        'eslint:recommended',
        'plugin:vue/recommended', // Use this if you are using Vue.js 2.x.
        'plugin:prettier/recommended',
      ],
      parserOptions: {
        ecmaVersion: 2020,
      },
      rules: {
        'prettier/prettier': [
          'error',
          {
            singleQuote: true,
            semi: true,
            tabWidth: 2,
            useTabs: false,
            printWidth: 100,
            trailingComma: 'es5',
            arrowParens: 'always',
            endOfLine: 'lf',
            bracketSpacing: true,
          },
        ],
        // override/add rules settings here, such as:
        'vue/no-unused-vars': 'error',
        'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
      },
    };
    ```

 - 프로젝트 최상위에 `.vscode` 폴더 생성

 - `.vscode` 폴더에 `settings.json` 파일 생성

    ```json
    {
      "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
      },
      "editor.defaultFormatter": "dbaeumer.vscode-eslint",
      "editor.formatOnSave": true
    }
    ```

 - vscode 에 prettier 플러그인을 안깔아도 됨

## add vue-router
```
# vue add router
```

## Tailwind CSS 설치
```
# npm install tailwindcss@latest postcss@latest autoprefixer@latest
```

## PostCSS 플러그인에 Tailwind CSS 추가
```
// postcss.config.js
module.exports = {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
  },
};
```

## tailwind 설정 파일 생성
```
// tailwind.config.js
module.exports = {
  purge: ['./src/**/*.{vue,js,jsx,ts,tsx}'], // 프로덕션 빌드 시 사용되지 않는 클래스 삭제
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {},
  },
  variants: {},
  plugins: [],
};
```

## Tailwind CSS 스타일 추가
```
// assets/tailwind.css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

## import tailwind css
```
// main.js
import '@/assets/tailwind.css';
```

## TailwindUI 의존성 라이브러리 설치
```
# npm install @headlessui/vue @heroicons/vue
```