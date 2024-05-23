import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'



export default defineConfig({
  publicPath: '/WMS',
  css: {
    preprocessorOptions: {
      sass: {
        additionalData:   
        `
          @import "../styles/variables.scss";
          @import "./styles/mixin.scss";     
        `
      }
    }
  },
  plugins: [
    vue(),
    //按需导入element
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
})
