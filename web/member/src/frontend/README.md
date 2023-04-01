## vue cli installation

- Link: [Vue CLI](https://cli.vuejs.org/#getting-started)

install:

```bash
npm install -g @vue/cli
```

## create vue project

create project:

```bash
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
? Choose a version of Vue.js that you want to start the project with 3.x
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
      extends: ['plugin:vue/vue3-essential', 'eslint:recommended', 'plugin:prettier/recommended'],
      parserOptions: {
        parser: '@babel/eslint-parser',
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
      overrides: [
        {
          files: ['**/__tests__/*.{j,t}s?(x)', '**/tests/unit/**/*.spec.{j,t}s?(x)'],
          env: {
            jest: true,
          },
        },
     ],
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

 - install vscode extension
   - ESLint
   - Vue 3 Snippets
   - Vue Language Features (Volar)

## add vue-router
```bash
vue add router
```

## tailwind CSS 설치
```bash
npm install tailwindcss@latest postcss@latest autoprefixer@latest
```

## postCSS 플러그인에 tailwind CSS 추가
```javascript
  // postcss.config.js
module.exports = {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
  },
};
```

## tailwind 설정 파일 생성
```javascript
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

## tailwind CSS 스타일 추가
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

## tailwindUI 의존성 라이브러리 설치
```bash
npm install @headlessui/vue @heroicons/vue
```
