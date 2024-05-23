import { createI18n } from "vue-i18n";
import EN from './en'
import ZH from './zh'

const messages = {
    en:{
        ...EN
    },
    zh:{
        ...ZH
    }   
}


const getCurrentLanguage = () =>{
    const UAlang = navigator.language  //zh-CN
    const langCOde = UAlang.indexOf('zh') !== -1 ? 'zh' : 'en'
    localStorage.setItem('lang',langCOde)
    return langCOde
}

const i18n = createI18n({
    legacy:false,
    globalInjection:true,
    locale:getCurrentLanguage() || 'zh',
    messages: messages
})

export default i18n