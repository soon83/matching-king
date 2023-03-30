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
