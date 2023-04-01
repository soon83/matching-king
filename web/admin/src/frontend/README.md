# Next.js 13

- 터보팩 출시
- 리액트 문법 사용 가능한 프론트 + 백엔드 프레임 워크

## 프로젝트 세팅

- nvm 을 설치하면 버전 관리가 쉽다

    ```bash
    // macOS
    brew install nvm
    ```

- npm 버전 확인

    ```bash
    npm -v
    ```

- npm 의 모든버전 목록 조회

    ```bash
    nvm ls-remote
    ```

- npm 이 최신 version 이 아니라면 최신 LTS 버전으로 설치

    ```bash
    nvm i 18.15.0
    ```

    - 설치된 npm version 이 여러개 일 때 목록 조회

        ```bash
        nvm ls
        ```

    - npm 특정 version 으로 switching

        ```bash
        nvm use 16.19.0
        ```

    - 현재 npm version 확인

        ```bash
        nvm current
        ```

    - npm 특정 version 을 default 로 설정

        ```bash
        nvm alias default [version]
        ```

- Next 프로젝트 생성 (with typescript)

    ```bash
    npx create-next-app@latest gongchin-frontend
    ```

    ```bash
    // 아래의 옵션을 선택하여 프로젝트를 생성
    ✔ Would you like to use TypeScript with this project? … No / Yes // -> Yes
    ✔ Would you like to use ESLint with this project? … No / Yes // -> Yes
    ✔ Would you like to use `src/` directory with this project? … No / Yes // -> No
    ✔ Would you like to use experimental `app/` directory with this project? … No / Yes // -> No
    ✔ What import alias would you like configured? … @/* // 그냥 엔터
    Creating a new Next.js app in /Users/ds/Devtools/VscodeProjects/gongchin-frontend
    ```

- ESLint (Ecma Script + Lint)
    - Ecma Script - Ecma에서 만든 표준 javascript
    - Lint - 에러가 있는 코드에 표시를 달아놓는 것

    ```bash
    npm i -D eslint
    ```

- Airbnb 코드 스타일
    - 레퍼런스: [https://github.com/airbnb/javascript](https://github.com/airbnb/javascript)
    - eslint-config-airbnb 설치 전 어떤 의존성을 같이 설치해주어야 하는지 먼저 확인하기

    ```bash
    npm info "eslint-config-airbnb@latest" peerDependencies
    ```

    - Airbnb 의존성 라이브러리 설치

    ```bash
    npm i -D eslint-config-airbnb eslint-plugin-import eslint-plugin-jsx-a11y eslint-plugin-react eslint-plugin-react-hooks
    ```

- Prettier 설치
    - 코드 스타일 통일을 위한 플러그인 (VS extension 필요없음)

    ```bash
    npm i -D prettier eslint-config-prettier eslint-plugin-prettier
    ```

- Babel 설치
    - ES6 코드를 내부적으로 ES5 이전의 형태로 만들어서 ES6을 지원하지 않는 브라우저에서 동작하게 해주는 애

    ```bash
    npm i -D babel-eslint eslint-plugin-babel
    ```

- .eslintrc.js 작성

    ```javascript
    module.exports = {
      root: true,
      env: {
        node: true,
        browser: true,
      },
      extends: [
        'next/core-web-vitals',
        'eslint:recommended',
        'plugin:react/recommended',
        'airbnb',
        'plugin:prettier/recommended',
      ],
      parserOptions: {
        ecmaVersion: 2020,
        sourceType: 'module',
        ecmaFeatures: {
          jsx: true,
        },
      },
      plugins: ['react', 'react-hooks'],
      rules: {
        'react/react-in-jsx-scope': 0,
        'react/prefer-stateless-function': 0,
        'react/jsx-filename-extension': 0,
        'react/jsx-one-expression-per-line': 0,
        'no-nested-ternary': 0,
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
      },
      globals: {
        React: 'writable',
      },
    };
    ```

- .vscode/settings.json 작성
    - 이 프로젝트에서만 eslint-prettier 가 동작하게 한다.
    - 프로젝트 별로 eslint-prettier 규칙이 다르게 적용될 수 있기 때문에 이렇게 하는 것을 추천한다. (vscode 에 prettier extension을 설치하지 않아도 됨)

    ```json
    {
        "editor.codeActionsOnSave": {
          "source.fixAll.eslint": true
        },
        "editor.defaultFormatter": "dbaeumer.vscode-eslint",
        "editor.formatOnSave": true
    }
    ```

- vscode extension 설치
    - eslint (이게 있어야 eslint-prettier 가 동작함)
    - material icon theme
    - ES7+ React/Redux/React-Native snippets