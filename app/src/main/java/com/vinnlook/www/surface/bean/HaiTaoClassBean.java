package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/1/27$
 * @Author:pk$
 */
public class HaiTaoClassBean extends BaseBean {
    /**
     * banner : [{"value":"392","type":"5","id":"45","end_time":null,"start_time":null,"photo":"http://img.jealook.com/app_img/20210127/20210127134436_82470.png","list":{"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":"45"}}]
     * title : [{"id":"297","name":"日抛区","image":"http://img.jealook.com/app_img/20210127/20210127143628_62500.png"},{"id":"304","name":"双周区","image":"http://img.jealook.com/app_img/20210127/20210127143648_66494.png"},{"id":"315","name":"月抛区","image":"http://img.jealook.com/app_img/20210127/20210127143704_70486.png"},{"id":"0","name":"透明片","image":"http://img.jealook.com/app_img/20210127/20210127143717_89754.png"}]
     * hot_goods_list : {"image":"http://img.jealook.com/app_img/20210128/20210128181256_96020.png","list":[{"product_price":"115.00","search_attr":"5551|5552","preferential_price":"0.00","color_id":"5551","product_number":"55","product_id":"9244","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Sea Green 10片","virtual_sales":"6","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8512.png","attr_name":"Sea Green 10片"},{"product_price":"195.00","search_attr":"10506|10507","preferential_price":"0.00","color_id":"10506","product_number":"66","product_id":"14196","goods_id":"325","goods_name":"强生ACUVUE 安视优恒润氧 Trueye 日抛 透明片 30片","virtual_sales":"5","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20210129/1611908328_6649.png","attr_name":"透明片 30片"},{"product_price":"9.90","search_attr":"20891|20894","preferential_price":"0.00","color_id":"20891","product_number":"1049","product_id":"24585","goods_id":"471","goods_name":"Rohto乐敦 日抛 Hazel Opal","virtual_sales":"123","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Rohto","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","attr_name":"Hazel Opal"},{"product_price":"78.00","search_attr":"22806|22892","preferential_price":"0.00","color_id":"22892","product_number":"69","product_id":"25728","goods_id":"490","goods_name":"Envie 旧款 日抛 Chameau Brown 10片","virtual_sales":"38","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Envie","goods_thumb":"http://img.jealook.com/backend/20210119/1611054188_6419.png","attr_name":"Chameau Brown 10片"},{"product_price":"99.00","search_attr":"23051|23053","preferential_price":"0.00","color_id":"23051","product_number":"30","product_id":"26296","goods_id":"491","goods_name":"新年福利！每个id限购4盒 30片盲盒 日本版日抛 微混血款（三）（14.2mm） 30片","virtual_sales":"12","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"其他","goods_thumb":"http://img.jealook.com/backend/20210126/1611634172_4602.png","attr_name":"微混血款（三）（14.2mm） 30片"}]}
     * brand : [[{"brand_id":"68","brand_name":"MerMer","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094754_22551.png"},{"brand_id":"154","brand_name":"Ciel Sight","brand_logo":"http://img.jealook.com/app_img/20210203/20210203094637_69893.png"},{"brand_id":"139","brand_name":"Alcon爱尔康","brand_logo":"http://img.jealook.com/app_img/20210120/20210120092518_67792.png"},{"brand_id":"113","brand_name":"Tulle","brand_logo":"http://img.jealook.com/app_img/20201224/20201224151539_58142.png"},{"brand_id":"108","brand_name":"Barbie","brand_logo":"http://img.jealook.com/app_img/20201104/20201104142714_60109.png"},{"brand_id":"99","brand_name":"N's collection","brand_logo":"http://img.jealook.com/app_img/20210120/20210120092453_76601.png"},{"brand_id":"97","brand_name":"强生","brand_logo":"http://img.jealook.com/app_img/20201104/20201104142859_72589.png"},{"brand_id":"92","brand_name":"Sustainable","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133408_70842.png"},{"brand_id":"91","brand_name":"Sparkle","brand_logo":"http://img.jealook.com/app_img/20200909/20200909133400_65821.png"},{"brand_id":"85","brand_name":"Acorde","brand_logo":"http://img.jealook.com/app_img/20200909/20200909122740_99794.png"}],[{"brand_id":"83","brand_name":"Dopewink","brand_logo":"http://img.jealook.com/app_img/20201013/20201013172743_78190.png"},{"brand_id":"82","brand_name":"BeeHeartB","brand_logo":"http://img.jealook.com/app_img/20210203/20210203113205_37217.png"},{"brand_id":"60","brand_name":"Angelcolor","brand_logo":"http://img.jealook.com/app_img/20200602/20200602184441_16572.png"},{"brand_id":"54","brand_name":"晶硕香水","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094458_73407.png"},{"brand_id":"52","brand_name":"Aocmu","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094435_68988.png"},{"brand_id":"50","brand_name":"Topards","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094414_53744.png"},{"brand_id":"49","brand_name":"Richbaby","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094356_90357.png"},{"brand_id":"48","brand_name":"Richstandard","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094345_50255.png"},{"brand_id":"46","brand_name":"Refrear","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094301_70366.png"},{"brand_id":"44","brand_name":"Pure Natural","brand_logo":"http://img.jealook.com/app_img/20200521/20200521094233_55885.png"}]]
     * brand_img : http://img.jealook.com/app_img/20201030/20201030113057_89718.png
     * shop : [{"id":297,"image":"http://img.jealook.com/app_img/20201030/20201030113113_26897.png","title":"日抛","data":[{"product_price":"163.00","search_attr":"10069|10070","preferential_price":"154.85","color_id":"10069","product_number":"15","product_id":"13301","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Apricot Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_5803.png","attr_name":"Apricot Brown 20片"},{"product_price":"163.00","search_attr":"10070|10098","preferential_price":"154.85","color_id":"10098","product_number":"6","product_id":"13329","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Classic Cheek 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_9099.png","attr_name":"Classic Cheek 20片"},{"product_price":"163.00","search_attr":"10070|10099","preferential_price":"154.85","color_id":"10099","product_number":"17","product_id":"13357","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Mocha 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_8596.png","attr_name":"Natural Mocha 20片"},{"product_price":"163.00","search_attr":"10070|10101","preferential_price":"154.85","color_id":"10101","product_number":"15","product_id":"13412","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Smooth Coral 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_6979.png","attr_name":"Smooth Coral 20片"},{"product_price":"163.00","search_attr":"10070|10102","preferential_price":"154.85","color_id":"10102","product_number":"5","product_id":"13441","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Pearl Beige 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_4079.png","attr_name":"Pearl Beige 20片"},{"product_price":"163.00","search_attr":"10070|10103","preferential_price":"154.85","color_id":"10103","product_number":"27","product_id":"13468","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Clear Camel 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_2712.png","attr_name":"Clear Camel 20片"},{"product_price":"163.00","search_attr":"10070|19634","preferential_price":"154.85","color_id":"19634","product_number":"14","product_id":"22548","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_4598.png","attr_name":"Natural Brown 20片"},{"product_price":"163.00","search_attr":"10070|19635","preferential_price":"154.85","color_id":"19635","product_number":"10","product_id":"22576","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Black 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_6643.png","attr_name":"Natural Black 20片"},{"product_price":"163.00","search_attr":"10070|19636","preferential_price":"154.85","color_id":"19636","product_number":"7","product_id":"22604","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Champagne Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_6920.png","attr_name":"Champagne Brown 20片"}]},{"id":304,"image":"http://img.jealook.com/app_img/20201030/20201030113128_60921.png","title":"双周抛","data":[{"product_price":"99.00","search_attr":"10105|10106","preferential_price":"0.00","color_id":"10105","product_number":"16","product_id":"13497","goods_id":"310","goods_name":"Rich Standard 双周抛 Innocent Brown 6片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Richstandard","goods_thumb":"http://img.jealook.com/backend/20200911/1599808281_4229.png","attr_name":"Innocent Brown 6片"},{"product_price":"99.00","search_attr":"10106|13177","preferential_price":"0.00","color_id":"13177","product_number":"37","product_id":"18017","goods_id":"310","goods_name":"Rich Standard 双周抛 Relax Brown 6片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Richstandard","goods_thumb":"http://img.jealook.com/backend/20201010/1602294205_7735.png","attr_name":"Relax Brown 6片"},{"product_price":"155.00","search_attr":"1168|1169","preferential_price":"0.00","color_id":"1168","product_number":"41","product_id":"4434","goods_id":"41","goods_name":"Lumia 双周抛 Chiffon Olive 6片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20200928/1601264568_4692.png","attr_name":"Chiffon Olive 6片"},{"product_price":"155.00","search_attr":"1169|1192","preferential_price":"0.00","color_id":"1192","product_number":"29","product_id":"4461","goods_id":"41","goods_name":"Lumia 双周抛 Nudy Brown 6片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20201019/1603089594_2108.png","attr_name":"Nudy Brown 6片"},{"product_price":"155.00","search_attr":"1169|5135","preferential_price":"0.00","color_id":"5135","product_number":"21","product_id":"4485","goods_id":"41","goods_name":"Lumia 双周抛 Sweet Brown 6片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20200928/1601264568_5651.png","attr_name":"Sweet Brown 6片"},{"product_price":"155.00","search_attr":"1545|1546","preferential_price":"0.00","color_id":"1545","product_number":"28","product_id":"5631","goods_id":"54","goods_name":"Ciel Sight 双周抛 Brown 6片","virtual_sales":"3","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943131","promote_end_date":"1599580800","brand_name":"Ciel Sight","goods_thumb":"http://img.jealook.com/backend/20201019/1603079453_4420.png","attr_name":"Brown 6片"},{"product_price":"155.00","search_attr":"1546|1565","preferential_price":"0.00","color_id":"1565","product_number":"34","product_id":"5657","goods_id":"54","goods_name":"Ciel Sight 双周抛 Green 6片","virtual_sales":"3","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943131","promote_end_date":"1599580800","brand_name":"Ciel Sight","goods_thumb":"http://img.jealook.com/backend/20201019/1603079453_5287.png","attr_name":"Green 6片"},{"product_price":"135.00","search_attr":"1596|1597","preferential_price":"0.00","color_id":"1596","product_number":"11","product_id":"5709","goods_id":"55","goods_name":"Pienage Barbie 双周抛 Barbie-Little Secert 6片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Barbie","goods_thumb":"http://img.jealook.com/backend/20200928/1601271475_2307.png","attr_name":"Barbie-Little Secert 6片"},{"product_price":"135.00","search_attr":"1597|1604","preferential_price":"0.00","color_id":"1604","product_number":"27","product_id":"5731","goods_id":"55","goods_name":"Pienage Barbie 双周抛 Barbie-More Dream 6片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Barbie","goods_thumb":"http://img.jealook.com/backend/20200402/1585795387_3534.png","attr_name":"Barbie-More Dream 6片"}]},{"id":315,"image":"http://img.jealook.com/app_img/20201030/20201030113146_31757.png","title":"月抛","data":[{"product_price":"95.00","search_attr":"1017|1018","preferential_price":"0.00","color_id":"1017","product_number":"35","product_id":"3907","goods_id":"35","goods_name":"Lilmoon 月抛 Cream Beige 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_7458.png","attr_name":"Cream Beige 1片"},{"product_price":"95.00","search_attr":"1018|1043","preferential_price":"0.00","color_id":"1043","product_number":"35","product_id":"3933","goods_id":"35","goods_name":"Lilmoon 月抛 Cream Grege 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_7347.png","attr_name":"Cream Grege 1片"},{"product_price":"95.00","search_attr":"1018|1044","preferential_price":"0.00","color_id":"1044","product_number":"30","product_id":"3959","goods_id":"35","goods_name":"Lilmoon 月抛 Skin Beige 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_6217.png","attr_name":"Skin Beige 1片"},{"product_price":"95.00","search_attr":"1018|1045","preferential_price":"0.00","color_id":"1045","product_number":"28","product_id":"3985","goods_id":"35","goods_name":"Lilmoon 月抛 Skin Grege 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_3697.png","attr_name":"Skin Grege 1片"},{"product_price":"95.00","search_attr":"1018|1046","preferential_price":"0.00","color_id":"1046","product_number":"45","product_id":"4011","goods_id":"35","goods_name":"Lilmoon 月抛 Water Water 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_5893.png","attr_name":"Water Water 1片"},{"product_price":"119.00","search_attr":"1048|1049","preferential_price":"0.00","color_id":"1048","product_number":"13","product_id":"3906","goods_id":"36","goods_name":"Lilmoon 月抛 Cream Beige 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733373_8079.png","attr_name":"Cream Beige 2片"},{"product_price":"119.00","search_attr":"1049|1050","preferential_price":"0.00","color_id":"1050","product_number":"43","product_id":"3932","goods_id":"36","goods_name":"Lilmoon 月抛 Cream Grege 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200403/1585881268_8211.png","attr_name":"Cream Grege 2片"},{"product_price":"119.00","search_attr":"1049|1051","preferential_price":"0.00","color_id":"1051","product_number":"20","product_id":"3958","goods_id":"36","goods_name":"Lilmoon 月抛 Skin Beige 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733373_8195.png","attr_name":"Skin Beige 2片"},{"product_price":"119.00","search_attr":"1049|1052","preferential_price":"0.00","color_id":"1052","product_number":"58","product_id":"3984","goods_id":"36","goods_name":"Lilmoon 月抛 Skin Grege 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733373_7573.png","attr_name":"Skin Grege 2片"}]},{"id":0,"image":"http://img.jealook.com/app_img/20201030/20201030113200_55864.png","title":"透明片","data":[{"product_price":"87.00","search_attr":"16286|16370","preferential_price":"0.00","color_id":"16370","product_number":"37","product_id":"21343","goods_id":"424","goods_name":"Refrear 透明片日抛  透明片30片 UV新版 含水量38% 30片","virtual_sales":"4","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20201224/1608783679_8896.png","attr_name":"透明片30片 UV新版 含水量38% 30片"},{"product_price":"169.00","search_attr":"10631|10632","preferential_price":"0.00","color_id":"10631","product_number":"7","product_id":"14332","goods_id":"329","goods_name":"强生ACUVUE 安视优舒日Moist 日抛 透明片 30片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20200914/1600053917_8709.png","attr_name":"透明片 30片"},{"product_price":"105.00","search_attr":"9887|9888","preferential_price":"0.00","color_id":"9887","product_number":"2","product_id":"12857","goods_id":"303","goods_name":"Pure Natural 日抛 透明片38%（新款） 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Pure Natural","goods_thumb":"http://img.jealook.com/backend/20200911/1599788512_5696.png","attr_name":"透明片38%（新款） 30片"},{"product_price":"228.00","search_attr":"2077|2078","preferential_price":"0.00","color_id":"2077","product_number":"25","product_id":"6587","goods_id":"73","goods_name":"Alcon爱尔康 日抛 透明片 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Alcon爱尔康","goods_thumb":"http://img.jealook.com/backend/20200403/1585900813_8644.png","attr_name":"透明片 30片"},{"product_price":"145.00","search_attr":"23090|23190","preferential_price":"0.00","color_id":"23190","product_number":"17","product_id":"26854","goods_id":"492","goods_name":"强生ACUVUE 安视优欧舒适Oasys 双周抛 强生 欧舒适 8.8双周抛（产地爱尔兰） 6片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20210129/1611908277_1901.png","attr_name":"强生 欧舒适 8.8双周抛（产地爱尔兰） 6片"},{"product_price":"84.00","search_attr":"16375|16456","preferential_price":"0.00","color_id":"16456","product_number":"11","product_id":"21374","goods_id":"425","goods_name":"Refrear 透明片 双周 双周抛 限量款 6片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20210119/1611048264_1351.png","attr_name":"双周抛 限量款 6片"},{"product_price":"84.00","search_attr":"16200|16279","preferential_price":"0.00","color_id":"16279","product_number":"28","product_id":"21219","goods_id":"423","goods_name":"Refrear 透明片 月抛  Refrear透明月抛6片 限量包装 6片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20210119/1611048289_8173.png","attr_name":"Refrear透明月抛6片 限量包装 6片"},{"product_price":"105.00","search_attr":"9888|19637","preferential_price":"0.00","color_id":"19637","product_number":"11","product_id":"22524","goods_id":"303","goods_name":"Pure Natural 日抛 透明片55%（新款） 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Pure Natural","goods_thumb":"http://img.jealook.com/backend/20201229/1609244378_1880.png","attr_name":"透明片55%（新款） 30片"},{"product_price":"45.00","search_attr":"5286|5287","preferential_price":"0.00","color_id":"5286","product_number":"8","product_id":"6805","goods_id":"133","goods_name":"Alcon爱尔康 日抛 透明片 5片","virtual_sales":"4","suppliers_id":"2","is_promote":0,"promote_start_date":"1594051200","promote_end_date":"1594656000","brand_name":"Alcon爱尔康","goods_thumb":"http://img.jealook.com/backend/20200415/1586934654_1259.png","attr_name":"透明片 5片"}]},{"id":-1,"image":"http://img.jealook.com/app_img/20201124/20201124161649_36865.png","title":"为你推荐","data":[{"product_price":"155.00","search_attr":"2|3","preferential_price":"154.90","color_id":"2","product_number":"4","product_id":"2","goods_id":"1","goods_name":"Angelcolor Bambi Series Natural系列 日抛 Natural  Black 20片","virtual_sales":"10","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor","goods_thumb":"http://img.jealook.com/backend/20201229/1609241640_8636.png","attr_name":"Natural  Black 20片"},{"product_price":"125.00","search_attr":"26|27","preferential_price":"0.00","color_id":"26","product_number":"11","product_id":"79","goods_id":"2","goods_name":"Angelcolor Bambi Series Vintage系列 日抛 Vintage Brown 10片","virtual_sales":"3","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor","goods_thumb":"http://img.jealook.com/backend/20201229/1609240271_7533.png","attr_name":"Vintage Brown 10片"},{"product_price":"225.00","search_attr":"57|58","preferential_price":"0.00","color_id":"57","product_number":"7","product_id":"184","goods_id":"3","goods_name":"Angelcolor Bambi Series Vintage系列 日抛 Vintage Brown 30片","virtual_sales":"13","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Angelcolor","goods_thumb":"http://img.jealook.com/backend/20201229/1609241272_1465.png","attr_name":"Vintage Brown 30片"},{"product_price":"105.00","search_attr":"89|8758","preferential_price":"0.00","color_id":"8758","product_number":"21","product_id":"14618","goods_id":"4","goods_name":"Angelcolor Bambi Series 日抛 Cream Pink 10片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1594656000","promote_end_date":"1595260800","brand_name":"Angelcolor","goods_thumb":"http://img.jealook.com/backend/20201229/1609238171_6565.png","attr_name":"Cream Pink 10片"},{"product_price":"205.00","search_attr":"118|8637","preferential_price":"185.00","color_id":"8637","product_number":"40","product_id":"14678","goods_id":"5","goods_name":"Angelcolor Bambi Series 日抛 Cream Pink 30片","virtual_sales":"3","suppliers_id":"2","is_promote":0,"promote_start_date":"1612768120","promote_end_date":"1612800000","brand_name":"Angelcolor","goods_thumb":"http://img.jealook.com/backend/20201229/1609239596_1971.png","attr_name":"Cream Pink 30片"},{"product_price":"95.00","search_attr":"299|323","preferential_price":"0.00","color_id":"323","product_number":"6","product_id":"829","goods_id":"12","goods_name":"ChouChou 日抛 Frozen HazeL 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"ChouChou","goods_thumb":"http://img.jealook.com/backend/20200401/1585727870_6792.png","attr_name":"Frozen HazeL 10片"},{"product_price":"58.00","search_attr":"331|332","preferential_price":"0.00","color_id":"331","product_number":"7","product_id":"996","goods_id":"13","goods_name":"ChouChou 月抛 Fresh Lime 1片","virtual_sales":"17","suppliers_id":"2","is_promote":0,"promote_start_date":"1609344000","promote_end_date":"1610121600","brand_name":"ChouChou","goods_thumb":"http://img.jealook.com/backend/20200924/1600915171_3585.png","attr_name":"Fresh Lime 1片"},{"product_price":"89.00","search_attr":"362|363","preferential_price":"0.00","color_id":"362","product_number":"7","product_id":"1144","goods_id":"14","goods_name":"Dorb 日抛 Black 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Dorb","goods_thumb":"http://img.jealook.com/backend/20200603/1591169460_7687.png","attr_name":"Black 10片"},{"product_price":"119.00","search_attr":"391|392","preferential_price":"139.50","color_id":"391","product_number":"38","product_id":"1236","goods_id":"15","goods_name":"Dorb 月抛 Black 3片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Dorb","goods_thumb":"http://img.jealook.com/backend/20200401/1585729119_8936.png","attr_name":"Black 3片"},{"product_price":"99.00","search_attr":"421|13209","preferential_price":"0.00","color_id":"13209","product_number":"26","product_id":"18690","goods_id":"16","goods_name":"Envie 新版UV 日抛 新版 Coral Cheek 10片","virtual_sales":"7","suppliers_id":"2","is_promote":0,"promote_start_date":"1598421716","promote_end_date":"1598976000","brand_name":"Envie","goods_thumb":"http://img.jealook.com/backend/20201013/1602571106_5926.png","attr_name":"新版 Coral Cheek 10片"},{"product_price":"163.00","search_attr":"529|555","preferential_price":"154.85","color_id":"555","product_number":"12","product_id":"2038","goods_id":"19","goods_name":"Evercolor Natural Moist 系列 日抛 Chiffon Brown 20片","virtual_sales":"10","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200924/1600928126_5659.png","attr_name":"Chiffon Brown 20片"},{"product_price":"123.00","search_attr":"563|564","preferential_price":"116.85","color_id":"563","product_number":"18","product_id":"2204","goods_id":"20","goods_name":"Evercolor Luquage 系列 日抛 Airy Brown 10片","virtual_sales":"5","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200401/1585734281_9456.png","attr_name":"Airy Brown 10片"},{"product_price":"257.00","search_attr":"602|603","preferential_price":"244.15","color_id":"602","product_number":"7","product_id":"2540","goods_id":"21","goods_name":"Evercolor Luquage 系列 日抛 Airy Brown 30片","virtual_sales":"0","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200401/1585731110_3905.png","attr_name":"Airy Brown 30片"},{"product_price":"105.00","search_attr":"628|629","preferential_price":"105.00","color_id":"628","product_number":"29","product_id":"2564","goods_id":"22","goods_name":"Eye Closet 日抛 Clear Beige Coral 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1595606400","promote_end_date":"1596211200","brand_name":"Eye Closet","goods_thumb":"http://img.jealook.com/backend/20200618/1592452489_9606.png","attr_name":"Clear Beige Coral 10片"},{"product_price":"125.00","search_attr":"657|681","preferential_price":"0.00","color_id":"657","product_number":"23","product_id":"2643","goods_id":"23","goods_name":"Eye Closet 月抛 Clear Beige 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Eye Closet","goods_thumb":"http://img.jealook.com/backend/20200618/1592453082_1510.png","attr_name":"Clear Beige 2片"},{"product_price":"225.00","search_attr":"702|703","preferential_price":"0.00","color_id":"702","product_number":"13","product_id":"3058","goods_id":"24","goods_name":"Eyeddict 日抛 Baby Dual 30片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Eyeddict","goods_thumb":"http://img.jealook.com/backend/20200928/1601263579_2770.png","attr_name":"Baby Dual 30片"},{"product_price":"105.00","search_attr":"732|733","preferential_price":"0.00","color_id":"732","product_number":"4","product_id":"3135","goods_id":"25","goods_name":"Flanmy 日抛 Maple Chiffon 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1598943049","promote_end_date":"1599634079","brand_name":"Flanmy","goods_thumb":"http://img.jealook.com/backend/20210111/1610359448_7393.png","attr_name":"Maple Chiffon 10片"},{"product_price":"88.00","search_attr":"759|760","preferential_price":"0.00","color_id":"759","product_number":"29","product_id":"3182","goods_id":"26","goods_name":"Flanmy 日抛 Flanmy Clear  20片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Flanmy","goods_thumb":"http://img.jealook.com/backend/20201228/1609150631_2675.png","attr_name":"Flanmy Clear  20片"},{"product_price":"199.00","search_attr":"786|787","preferential_price":"0.00","color_id":"786","product_number":"9","product_id":"3208","goods_id":"27","goods_name":"Flanmy 日抛  Maple Chiffon 30片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1609828088","promote_end_date":"1610121600","brand_name":"Flanmy","goods_thumb":"http://img.jealook.com/backend/20201228/1609150396_5391.png","attr_name":"Maple Chiffon 30片"},{"product_price":"99.00","search_attr":"811|812","preferential_price":"0.00","color_id":"811","product_number":"24","product_id":"3254","goods_id":"28","goods_name":"Givre Sparkle 系列 日抛 Foggy Lime 10片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802432","promote_end_date":"1598371200","brand_name":"Sparkle","goods_thumb":"http://img.jealook.com/backend/20200401/1585732109_5405.png","attr_name":"Foggy Lime 10片"},{"product_price":"105.00","search_attr":"842|843","preferential_price":"0.00","color_id":"842","product_number":"63","product_id":"3358","goods_id":"29","goods_name":"Honey Drops 日抛 Shine 10片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Honey Drops","goods_thumb":"http://img.jealook.com/backend/20200618/1592451695_2412.png","attr_name":"Shine 10片"},{"product_price":"112.00","search_attr":"893|894","preferential_price":"0.00","color_id":"893","product_number":"3","product_id":"3448","goods_id":"31","goods_name":"Lalish 日抛 Chic Vintage 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lalish","goods_thumb":"http://img.jealook.com/backend/20200401/1585732582_5281.png","attr_name":"Chic Vintage 10片"},{"product_price":"108.00","search_attr":"921|922","preferential_price":"0.00","color_id":"921","product_number":"10","product_id":"3519","goods_id":"32","goods_name":"Larme 日抛 Classic Navy 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Larme","goods_thumb":"http://img.jealook.com/backend/20200618/1592451476_5386.png","attr_name":"Classic Navy 10片"},{"product_price":"95.00","search_attr":"953|954","preferential_price":"0.00","color_id":"953","product_number":"12","product_id":"3646","goods_id":"33","goods_name":"Lilmoon 日抛 Skin Beige 10片","virtual_sales":"4","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733034_2613.png","attr_name":"Skin Beige 10片"},{"product_price":"235.00","search_attr":"985|986","preferential_price":"0.00","color_id":"985","product_number":"19","product_id":"3776","goods_id":"34","goods_name":"Lilmoon 日抛 Skin Beige 30片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733149_9026.png","attr_name":"Skin Beige 30片"},{"product_price":"95.00","search_attr":"1017|1018","preferential_price":"0.00","color_id":"1017","product_number":"35","product_id":"3907","goods_id":"35","goods_name":"Lilmoon 月抛 Cream Beige 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733266_7458.png","attr_name":"Cream Beige 1片"},{"product_price":"119.00","search_attr":"1048|1049","preferential_price":"0.00","color_id":"1048","product_number":"13","product_id":"3906","goods_id":"36","goods_name":"Lilmoon 月抛 Cream Beige 2片","virtual_sales":"1","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lilmoon","goods_thumb":"http://img.jealook.com/backend/20200401/1585733373_8079.png","attr_name":"Cream Beige 2片"},{"product_price":"79.00","search_attr":"1141|1142","preferential_price":"0.00","color_id":"1141","product_number":"6","product_id":"4307","goods_id":"40","goods_name":"Lumia 日抛 Chiffon Olive 10片","virtual_sales":"4","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20200401/1585733799_6330.png","attr_name":"Chiffon Olive 10片"},{"product_price":"155.00","search_attr":"1168|1169","preferential_price":"0.00","color_id":"1168","product_number":"41","product_id":"4434","goods_id":"41","goods_name":"Lumia 双周抛 Chiffon Olive 6片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Lumia","goods_thumb":"http://img.jealook.com/backend/20200928/1601264568_4692.png","attr_name":"Chiffon Olive 6片"},{"product_price":"85.00","search_attr":"1195|1196","preferential_price":"0.00","color_id":"1195","product_number":"1","product_id":"4509","goods_id":"42","goods_name":"Luna 日抛 Almond 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1599550881","promote_end_date":"1600099200","brand_name":"Luna","goods_thumb":"http://img.jealook.com/backend/20200401/1585734479_3386.png","attr_name":"Almond 10片"},{"product_price":"66.00","search_attr":"1217|1218","preferential_price":"0.00","color_id":"1217","product_number":"11","product_id":"4605","goods_id":"43","goods_name":"Luna 月抛 Almond 1片","virtual_sales":"22","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Luna","goods_thumb":"http://img.jealook.com/backend/20200401/1585734670_3038.png","attr_name":"Almond 1片"},{"product_price":"115.00","search_attr":"1247|1271","preferential_price":"0.00","color_id":"1271","product_number":"3","product_id":"4726","goods_id":"44","goods_name":"Melange 日抛 Coquettish Brown 10片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Melange","goods_thumb":"http://img.jealook.com/backend/20201019/1603087082_2012.png","attr_name":"Coquettish Brown 10片"},{"product_price":"95.00","search_attr":"1277|1278","preferential_price":"0.00","color_id":"1277","product_number":"2","product_id":"4806","goods_id":"45","goods_name":"Miche Bloomin 日抛 Ivy Lady 206 10片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Miche Bloomin","goods_thumb":"http://img.jealook.com/backend/20210111/1610346111_4192.png","attr_name":"Ivy Lady 206 10片"},{"product_price":"225.00","search_attr":"1306|1307","preferential_price":"0.00","color_id":"1306","product_number":"4","product_id":"4883","goods_id":"46","goods_name":"Miche Bloomin 日抛 Ivy Lady 206 30片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Miche Bloomin","goods_thumb":"http://img.jealook.com/backend/20210111/1610349681_5168.png","attr_name":"Ivy Lady 206 30片"},{"product_price":"68.00","search_attr":"1336|1355","preferential_price":"0.00","color_id":"1355","product_number":"1","product_id":"4986","goods_id":"47","goods_name":"Miche Bloomin 月抛 Chamois Muse 1片","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Miche Bloomin","goods_thumb":"http://img.jealook.com/backend/20200402/1585792592_2671.png","attr_name":"Chamois Muse 1片"},{"product_price":"85.00","search_attr":"1368|1369","preferential_price":"0.00","color_id":"1368","product_number":"4","product_id":"5115","goods_id":"48","goods_name":"Nadeshiko Color 日抛 Blue 10片","virtual_sales":"2","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Nadeshiko Color","goods_thumb":"http://img.jealook.com/backend/20200402/1585793496_5601.png","attr_name":"Blue 10片"}]}]
     */

    private HotGoodsListBean hot_goods_list;
    private String brand_img;
    private List<BannerBean> banner;
    private List<TitleBean> title;
    private List<List<BrandBean>> brand;
    private List<ShopBean> shop;

    public HotGoodsListBean getHot_goods_list() {
        return hot_goods_list;
    }

    public void setHot_goods_list(HotGoodsListBean hot_goods_list) {
        this.hot_goods_list = hot_goods_list;
    }

    public String getBrand_img() {
        return brand_img;
    }

    public void setBrand_img(String brand_img) {
        this.brand_img = brand_img;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<TitleBean> getTitle() {
        return title;
    }

    public void setTitle(List<TitleBean> title) {
        this.title = title;
    }

    public List<List<BrandBean>> getBrand() {
        return brand;
    }

    public void setBrand(List<List<BrandBean>> brand) {
        this.brand = brand;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public static class HotGoodsListBean {
        /**
         * image : http://img.jealook.com/app_img/20210128/20210128181256_96020.png
         * list : [{"product_price":"115.00","search_attr":"5551|5552","preferential_price":"0.00","color_id":"5551","product_number":"55","product_id":"9244","goods_id":"141","goods_name":"MerMer Mix系列 合作款 日抛 Sea Green 10片","virtual_sales":"6","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"MerMer","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8512.png","attr_name":"Sea Green 10片"},{"product_price":"195.00","search_attr":"10506|10507","preferential_price":"0.00","color_id":"10506","product_number":"66","product_id":"14196","goods_id":"325","goods_name":"强生ACUVUE 安视优恒润氧 Trueye 日抛 透明片 30片","virtual_sales":"5","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"强生","goods_thumb":"http://img.jealook.com/backend/20210129/1611908328_6649.png","attr_name":"透明片 30片"},{"product_price":"9.90","search_attr":"20891|20894","preferential_price":"0.00","color_id":"20891","product_number":"1049","product_id":"24585","goods_id":"471","goods_name":"Rohto乐敦 日抛 Hazel Opal","virtual_sales":"123","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Rohto","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","attr_name":"Hazel Opal"},{"product_price":"78.00","search_attr":"22806|22892","preferential_price":"0.00","color_id":"22892","product_number":"69","product_id":"25728","goods_id":"490","goods_name":"Envie 旧款 日抛 Chameau Brown 10片","virtual_sales":"38","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Envie","goods_thumb":"http://img.jealook.com/backend/20210119/1611054188_6419.png","attr_name":"Chameau Brown 10片"},{"product_price":"99.00","search_attr":"23051|23053","preferential_price":"0.00","color_id":"23051","product_number":"30","product_id":"26296","goods_id":"491","goods_name":"新年福利！每个id限购4盒 30片盲盒 日本版日抛 微混血款（三）（14.2mm） 30片","virtual_sales":"12","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"其他","goods_thumb":"http://img.jealook.com/backend/20210126/1611634172_4602.png","attr_name":"微混血款（三）（14.2mm） 30片"}]
         */

        private String image;
        private List<ListBean> list;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * product_price : 115.00
             * search_attr : 5551|5552
             * preferential_price : 0.00
             * color_id : 5551
             * product_number : 55
             * product_id : 9244
             * goods_id : 141
             * goods_name : MerMer Mix系列 合作款 日抛 Sea Green 10片
             * virtual_sales : 6
             * suppliers_id : 2
             * is_promote : 0
             * promote_start_date : 0
             * promote_end_date : 0
             * brand_name : MerMer
             * goods_thumb : http://img.jealook.com/backend/20210107/1610002858_8512.png
             * attr_name : Sea Green 10片
             */

            private String product_price;
            private String search_attr;
            private String preferential_price;
            private String color_id;
            private String product_number;
            private String product_id;
            private String goods_id;
            private String goods_name;
            private String virtual_sales;
            private String suppliers_id;
            private int is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String brand_name;
            private String goods_thumb;
            private String attr_name;

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getPreferential_price() {
                return preferential_price;
            }

            public void setPreferential_price(String preferential_price) {
                this.preferential_price = preferential_price;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
            }

            public String getProduct_number() {
                return product_number;
            }

            public void setProduct_number(String product_number) {
                this.product_number = product_number;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getVirtual_sales() {
                return virtual_sales;
            }

            public void setVirtual_sales(String virtual_sales) {
                this.virtual_sales = virtual_sales;
            }

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }
        }
    }

    public static class BannerBean {
        /**
         * value : 392
         * type : 5
         * id : 45
         * end_time : null
         * start_time : null
         * photo : http://img.jealook.com/app_img/20210127/20210127134436_82470.png
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":"45"}
         */

        private String value;
        private String type;
        private String id;
        private Object end_time;
        private Object start_time;
        private String photo;
        private ListBeanX list;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * goods_id :
             * search_attr :
             * active_id :
             * url :
             * text :
             * id : 45
             */

            private String goods_id;
            private String search_attr;
            private String active_id;
            private String url;
            private String text;
            private String id;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class TitleBean {
        /**
         * id : 297
         * name : 日抛区
         * image : http://img.jealook.com/app_img/20210127/20210127143628_62500.png
         */

        private String id;
        private String name;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class BrandBean {
        /**
         * brand_id : 68
         * brand_name : MerMer
         * brand_logo : http://img.jealook.com/app_img/20200521/20200521094754_22551.png
         */

        private String brand_id;
        private String brand_name;
        private String brand_logo;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }
    }

    public static class ShopBean {
        /**
         * id : 297
         * image : http://img.jealook.com/app_img/20201030/20201030113113_26897.png
         * title : 日抛
         * data : [{"product_price":"163.00","search_attr":"10069|10070","preferential_price":"154.85","color_id":"10069","product_number":"15","product_id":"13301","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Apricot Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_5803.png","attr_name":"Apricot Brown 20片"},{"product_price":"163.00","search_attr":"10070|10098","preferential_price":"154.85","color_id":"10098","product_number":"6","product_id":"13329","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Classic Cheek 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_9099.png","attr_name":"Classic Cheek 20片"},{"product_price":"163.00","search_attr":"10070|10099","preferential_price":"154.85","color_id":"10099","product_number":"17","product_id":"13357","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Mocha 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_8596.png","attr_name":"Natural Mocha 20片"},{"product_price":"163.00","search_attr":"10070|10101","preferential_price":"154.85","color_id":"10101","product_number":"15","product_id":"13412","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Smooth Coral 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_6979.png","attr_name":"Smooth Coral 20片"},{"product_price":"163.00","search_attr":"10070|10102","preferential_price":"154.85","color_id":"10102","product_number":"5","product_id":"13441","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Pearl Beige 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_4079.png","attr_name":"Pearl Beige 20片"},{"product_price":"163.00","search_attr":"10070|10103","preferential_price":"154.85","color_id":"10103","product_number":"27","product_id":"13468","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Clear Camel 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20200911/1599795217_2712.png","attr_name":"Clear Camel 20片"},{"product_price":"163.00","search_attr":"10070|19634","preferential_price":"154.85","color_id":"19634","product_number":"14","product_id":"22548","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_4598.png","attr_name":"Natural Brown 20片"},{"product_price":"163.00","search_attr":"10070|19635","preferential_price":"154.85","color_id":"19635","product_number":"10","product_id":"22576","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Natural Black 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_6643.png","attr_name":"Natural Black 20片"},{"product_price":"163.00","search_attr":"10070|19636","preferential_price":"154.85","color_id":"19636","product_number":"7","product_id":"22604","goods_id":"309","goods_name":"Evercolor Natural 系列 日抛 Champagne Brown 20片","virtual_sales":"4","suppliers_id":"2","is_promote":1,"promote_start_date":"1612835775","promote_end_date":"1614441600","brand_name":"Evercolor","goods_thumb":"http://img.jealook.com/backend/20210108/1610102314_6920.png","attr_name":"Champagne Brown 20片"}]
         */

        private int id;
        private String image;
        private String title;
        private List<DataBean> data;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * product_price : 163.00
             * search_attr : 10069|10070
             * preferential_price : 154.85
             * color_id : 10069
             * product_number : 15
             * product_id : 13301
             * goods_id : 309
             * goods_name : Evercolor Natural 系列 日抛 Apricot Brown 20片
             * virtual_sales : 4
             * suppliers_id : 2
             * is_promote : 1
             * promote_start_date : 1612835775
             * promote_end_date : 1614441600
             * brand_name : Evercolor
             * goods_thumb : http://img.jealook.com/backend/20200911/1599795217_5803.png
             * attr_name : Apricot Brown 20片
             */

            private String product_price;
            private String search_attr;
            private String preferential_price;
            private String color_id;
            private String product_number;
            private String product_id;
            private String goods_id;
            private String goods_name;
            private String virtual_sales;
            private String suppliers_id;
            private int is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String brand_name;
            private String goods_thumb;
            private String attr_name;

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getPreferential_price() {
                return preferential_price;
            }

            public void setPreferential_price(String preferential_price) {
                this.preferential_price = preferential_price;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
            }

            public String getProduct_number() {
                return product_number;
            }

            public void setProduct_number(String product_number) {
                this.product_number = product_number;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getVirtual_sales() {
                return virtual_sales;
            }

            public void setVirtual_sales(String virtual_sales) {
                this.virtual_sales = virtual_sales;
            }

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }
        }
    }
}
