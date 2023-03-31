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
    parser: '@babel/eslint-parser',
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
    'no-unused-vars': 'warn',
    'react/jsx-props-no-spreading': ['warn'],
    'import/no-extraneous-dependencies': 0,
    'react/forbid-prop-types': 0,
    'react/require-default-props': 0,
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
