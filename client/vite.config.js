import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  define: {
    global: {},
  },
  optimizeDeps: {
    include: ['@emotion/styled'],
  },
  resolve: {
    alias: {
      './runtimeConfig': './runtimeConfig.browser',
    },
  },
  build: {
    sourcemap: true,
    commonjsOptions: {
      include: [/node_modules/],
      extensions: ['.js', '.cjs'],
      strictRequires: true,
      // https://stackoverflow.com/questions/62770883/how-to-include-both-import-and-require-statements-in-the-bundle-using-rollup
      transformMixedEsModules: true,
    },
  },
});
