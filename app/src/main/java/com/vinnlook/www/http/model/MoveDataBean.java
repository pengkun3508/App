package com.vinnlook.www.http.model;

import com.vinnlook.www.surface.mvp.model.bean.ProductBean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/4/23$
 * @Author:pk$
 */
public class MoveDataBean extends BaseBean {
    /**
     * info : {"color_id":"13789","product_id":"20388","product_number":"329","product_price":"115.00","preferential_price":"0.00","color":"棕","search_attr":"13716|13789","product_sn":"AS4570041580633","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","goods_sn":"JLH10006743","goods_id":"392","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","shop_name":"Juicy Drop 日抛","is_new":"1","brand_name":"Juicy Drop","surplus_time":0,"shop_attr_name":"honey dew 10枚","details":["http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064174993162.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064180412429.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064189164240.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064194560536.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064200335140.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064210855065.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064221550274.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064226432481.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064232950051.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064239125494.jpg"],"banner":[{"url":"http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4","type":2},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4473.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7098.png"}],"toss_period":"日抛","origin":"韩国","life_span":"具体见包装","textures":"水胶","user_notes":["http://shop.jealook.com/image/ht-1.jpg","http://shop.jealook.com/image/ht-2.jpg","http://shop.jealook.com/image/ht-3.jpg","http://shop.jealook.com/image/all-1.jpg"],"waybill_url":"http://h5.jealook.com/vinnlook/htPackage.html","post_fee":"邮费￥10.00,满200元包邮(部分地区)","tossPeriodId":"297","member_discount":"109.25","is_collect":0,"comment_count":"0","question_count":"3"}
     * attr : [{"attr_name":"颜色","attr_id":"1","value":[{"goods_attr_id":"13789","attr_value":"honey dew","banner":[{"url":"http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4","type":2},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4473.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7098.png"}]},{"goods_attr_id":"13790","attr_value":"mint cream","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8770.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4925.png"}]},{"goods_attr_id":"13791","attr_value":"misty gray","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7455.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8610.png"}]},{"goods_attr_id":"13788","attr_value":"pure sky","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8563.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_6176.png"}]}]},{"attr_name":"规格（片数）","attr_id":"2","value":[{"goods_attr_id":"13716","attr_value":"10枚"}]},{"attr_name":"度数","attr_id":"3","value":[{"goods_attr_id":"13744","attr_value":"-0.00"},{"goods_attr_id":"13785","attr_value":"-0.75"},{"goods_attr_id":"13745","attr_value":"-1.00"},{"goods_attr_id":"13746","attr_value":"-1.25"},{"goods_attr_id":"13747","attr_value":"-1.50"},{"goods_attr_id":"13748","attr_value":"-1.75"},{"goods_attr_id":"13749","attr_value":"-2.00"},{"goods_attr_id":"13750","attr_value":"-2.25"},{"goods_attr_id":"13751","attr_value":"-2.50"},{"goods_attr_id":"13752","attr_value":"-2.75"},{"goods_attr_id":"13753","attr_value":"-3.00"},{"goods_attr_id":"13754","attr_value":"-3.25"},{"goods_attr_id":"13755","attr_value":"-3.50"},{"goods_attr_id":"13756","attr_value":"-3.75"},{"goods_attr_id":"13757","attr_value":"-4.00"},{"goods_attr_id":"13758","attr_value":"-4.25"},{"goods_attr_id":"13759","attr_value":"-4.50"},{"goods_attr_id":"13760","attr_value":"-4.75"},{"goods_attr_id":"13761","attr_value":"-5.00"},{"goods_attr_id":"13762","attr_value":"-5.25"},{"goods_attr_id":"13763","attr_value":"-5.50"},{"goods_attr_id":"13764","attr_value":"-5.75"},{"goods_attr_id":"13765","attr_value":"-6.00"},{"goods_attr_id":"13766","attr_value":"-6.50"},{"goods_attr_id":"13767","attr_value":"-7.00"},{"goods_attr_id":"13768","attr_value":"-7.50"},{"goods_attr_id":"13769","attr_value":"-8.00"},{"goods_attr_id":"13780","attr_value":"-8.50"},{"goods_attr_id":"13770","attr_value":"-9.00"},{"goods_attr_id":"13771","attr_value":"-9.50"},{"goods_attr_id":"13783","attr_value":"-10.00"}]}]
     * product : [{"goods_attr":"13716|13744|13788","product_id":"20357","product_sn":"AS4570041580015","product_number":"145","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -0.00 pure sky"},{"goods_attr":"13716|13785|13788","product_id":"20358","product_sn":"AS4570041580022","product_number":"46","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -0.75 pure sky"},{"goods_attr":"13716|13745|13788","product_id":"20359","product_sn":"AS4570041580039","product_number":"44","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.00 pure sky"},{"goods_attr":"13716|13746|13788","product_id":"20360","product_sn":"AS4570041580046","product_number":"11","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.25 pure sky"},{"goods_attr":"13716|13747|13788","product_id":"20361","product_sn":"AS4570041580053","product_number":"11","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.50 pure sky"},{"goods_attr":"13716|13748|13788","product_id":"20362","product_sn":"AS4570041580060","product_number":"8","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.75 pure sky"},{"goods_attr":"13716|13749|13788","product_id":"20363","product_sn":"AS4570041580077","product_number":"13","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.00 pure sky"},{"goods_attr":"13716|13750|13788","product_id":"20364","product_sn":"AS4570041580084","product_number":"20","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.25 pure sky"},{"goods_attr":"13716|13751|13788","product_id":"20365","product_sn":"AS4570041580091","product_number":"111","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.50 pure sky"},{"goods_attr":"13716|13752|13788","product_id":"20366","product_sn":"AS4570041580107","product_number":"111","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.75 pure sky"},{"goods_attr":"13716|13753|13788","product_id":"20367","product_sn":"AS4570041580114","product_number":"95","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.00 pure sky"},{"goods_attr":"13716|13754|13788","product_id":"20368","product_sn":"AS4570041580121","product_number":"102","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.25 pure sky"},{"goods_attr":"13716|13756|13788","product_id":"20369","product_sn":"AS4570041580145","product_number":"88","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.75 pure sky"},{"goods_attr":"13716|13757|13788","product_id":"20370","product_sn":"AS4570041580152","product_number":"34","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.00 pure sky"},{"goods_attr":"13716|13758|13788","product_id":"20371","product_sn":"AS4570041580169","product_number":"17","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.25 pure sky"},{"goods_attr":"13716|13759|13788","product_id":"20372","product_sn":"AS4570041580176","product_number":"35","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.50 pure sky"},{"goods_attr":"13716|13760|13788","product_id":"20373","product_sn":"AS4570041580183","product_number":"34","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.75 pure sky"},{"goods_attr":"13716|13761|13788","product_id":"20374","product_sn":"AS4570041580190","product_number":"31","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.00 pure sky"},{"goods_attr":"13716|13762|13788","product_id":"20375","product_sn":"AS4570041580206","product_number":"68","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.25 pure sky"},{"goods_attr":"13716|13763|13788","product_id":"20376","product_sn":"AS4570041580213","product_number":"30","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.50 pure sky"},{"goods_attr":"13716|13764|13788","product_id":"20377","product_sn":"AS4570041580220","product_number":"46","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.75 pure sky"},{"goods_attr":"13716|13765|13788","product_id":"20378","product_sn":"AS4570041580237","product_number":"83","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -6.00 pure sky"},{"goods_attr":"13716|13766|13788","product_id":"20379","product_sn":"AS4570041580244","product_number":"103","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -6.50 pure sky"},{"goods_attr":"13716|13767|13788","product_id":"20380","product_sn":"AS4570041580251","product_number":"88","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -7.00 pure sky"},{"goods_attr":"13716|13768|13788","product_id":"20381","product_sn":"AS4570041580268","product_number":"61","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -7.50 pure sky"},{"goods_attr":"13716|13769|13788","product_id":"20382","product_sn":"AS4570041580275","product_number":"70","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -8.00 pure sky"},{"goods_attr":"13716|13780|13788","product_id":"20383","product_sn":"AS4570041580282","product_number":"54","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -8.50 pure sky"},{"goods_attr":"13716|13770|13788","product_id":"20384","product_sn":"AS4570041580299","product_number":"51","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -9.00 pure sky"},{"goods_attr":"13716|13771|13788","product_id":"20385","product_sn":"AS4570041580305","product_number":"54","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -9.50 pure sky"},{"goods_attr":"13716|13783|13788","product_id":"20386","product_sn":"AS4570041580312","product_number":"59","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -10.00 pure sky"},{"goods_attr":"13716|13755|13788","product_id":"20387","product_sn":"AS4570041580138","product_number":"109","product_price":"115.00","preferential_price":"0.00","color_id":"13788","search_attr":"13716|13788","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.50 pure sky"},{"goods_attr":"13716|13744|13789","product_id":"20388","product_sn":"AS4570041580633","product_number":"329","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -0.00 honey dew"},{"goods_attr":"13716|13785|13789","product_id":"20389","product_sn":"AS4570041580640","product_number":"10","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -0.75 honey dew"},{"goods_attr":"13716|13745|13789","product_id":"20390","product_sn":"AS4570041580657","product_number":"6","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.00 honey dew"},{"goods_attr":"13716|13746|13789","product_id":"20391","product_sn":"AS4570041580664","product_number":"0","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.25 honey dew"},{"goods_attr":"13716|13747|13789","product_id":"20392","product_sn":"AS4570041580671","product_number":"0","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.50 honey dew"},{"goods_attr":"13716|13748|13789","product_id":"20393","product_sn":"AS4570041580688","product_number":"6","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.75 honey dew"},{"goods_attr":"13716|13749|13789","product_id":"20394","product_sn":"AS4570041580695","product_number":"15","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.00 honey dew"},{"goods_attr":"13716|13750|13789","product_id":"20395","product_sn":"AS4570041580701","product_number":"0","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.25 honey dew"},{"goods_attr":"13716|13751|13789","product_id":"20396","product_sn":"AS4570041580718","product_number":"51","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.50 honey dew"},{"goods_attr":"13716|13752|13789","product_id":"20397","product_sn":"AS4570041580725","product_number":"61","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.75 honey dew"},{"goods_attr":"13716|13753|13789","product_id":"20398","product_sn":"AS4570041580732","product_number":"61","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.00 honey dew"},{"goods_attr":"13716|13754|13789","product_id":"20399","product_sn":"AS4570041580749","product_number":"61","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.25 honey dew"},{"goods_attr":"13716|13756|13789","product_id":"20400","product_sn":"AS4570041580763","product_number":"28","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.75 honey dew"},{"goods_attr":"13716|13757|13789","product_id":"20401","product_sn":"AS4570041580770","product_number":"-1","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.00 honey dew"},{"goods_attr":"13716|13758|13789","product_id":"20402","product_sn":"AS4570041580787","product_number":"75","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.25 honey dew"},{"goods_attr":"13716|13759|13789","product_id":"20403","product_sn":"AS4570041580794","product_number":"67","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.50 honey dew"},{"goods_attr":"13716|13760|13789","product_id":"20404","product_sn":"AS4570041580800","product_number":"97","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.75 honey dew"},{"goods_attr":"13716|13761|13789","product_id":"20405","product_sn":"AS4570041580817","product_number":"57","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.00 honey dew"},{"goods_attr":"13716|13762|13789","product_id":"20406","product_sn":"AS4570041580824","product_number":"13","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.25 honey dew"},{"goods_attr":"13716|13763|13789","product_id":"20407","product_sn":"AS4570041580831","product_number":"64","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.50 honey dew"},{"goods_attr":"13716|13764|13789","product_id":"20408","product_sn":"AS4570041580848","product_number":"44","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.75 honey dew"},{"goods_attr":"13716|13765|13789","product_id":"20409","product_sn":"AS4570041580855","product_number":"37","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -6.00 honey dew"},{"goods_attr":"13716|13766|13789","product_id":"20410","product_sn":"AS4570041580862","product_number":"64","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -6.50 honey dew"},{"goods_attr":"13716|13767|13789","product_id":"20411","product_sn":"AS4570041580879","product_number":"129","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -7.00 honey dew"},{"goods_attr":"13716|13768|13789","product_id":"20412","product_sn":"AS4570041580886","product_number":"33","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -7.50 honey dew"},{"goods_attr":"13716|13769|13789","product_id":"20413","product_sn":"AS4570041580893","product_number":"52","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -8.00 honey dew"},{"goods_attr":"13716|13780|13789","product_id":"20414","product_sn":"AS4570041580909","product_number":"72","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -8.50 honey dew"},{"goods_attr":"13716|13770|13789","product_id":"20415","product_sn":"AS4570041580916","product_number":"81","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -9.00 honey dew"},{"goods_attr":"13716|13771|13789","product_id":"20416","product_sn":"AS4570041580923","product_number":"84","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -9.50 honey dew"},{"goods_attr":"13716|13783|13789","product_id":"20417","product_sn":"AS4570041580930","product_number":"75","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -10.00 honey dew"},{"goods_attr":"13716|13755|13789","product_id":"20418","product_sn":"AS4570041580756","product_number":"0","product_price":"115.00","preferential_price":"0.00","color_id":"13789","search_attr":"13716|13789","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.50 honey dew"},{"goods_attr":"13716|13744|13790","product_id":"20419","product_sn":"AS4570041580947","product_number":"376","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -0.00 mint cream"},{"goods_attr":"13716|13785|13790","product_id":"20420","product_sn":"AS4570041580954","product_number":"21","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -0.75 mint cream"},{"goods_attr":"13716|13745|13790","product_id":"20421","product_sn":"AS4570041580961","product_number":"28","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.00 mint cream"},{"goods_attr":"13716|13746|13790","product_id":"20422","product_sn":"AS4570041580978","product_number":"34","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.25 mint cream"},{"goods_attr":"13716|13747|13790","product_id":"20423","product_sn":"AS4570041580985","product_number":"25","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.50 mint cream"},{"goods_attr":"13716|13748|13790","product_id":"20424","product_sn":"AS4570041580992","product_number":"26","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -1.75 mint cream"},{"goods_attr":"13716|13749|13790","product_id":"20425","product_sn":"AS4570041581005","product_number":"51","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.00 mint cream"},{"goods_attr":"13716|13750|13790","product_id":"20426","product_sn":"AS4570041581012","product_number":"55","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.25 mint cream"},{"goods_attr":"13716|13751|13790","product_id":"20427","product_sn":"AS4570041581029","product_number":"140","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.50 mint cream"},{"goods_attr":"13716|13752|13790","product_id":"20428","product_sn":"AS4570041581036","product_number":"128","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -2.75 mint cream"},{"goods_attr":"13716|13753|13790","product_id":"20429","product_sn":"AS4570041581043","product_number":"142","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.00 mint cream"},{"goods_attr":"13716|13754|13790","product_id":"20430","product_sn":"AS4570041581050","product_number":"135","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.25 mint cream"},{"goods_attr":"13716|13756|13790","product_id":"20431","product_sn":"AS4570041581074","product_number":"120","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.75 mint cream"},{"goods_attr":"13716|13757|13790","product_id":"20432","product_sn":"AS4570041581081","product_number":"130","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.00 mint cream"},{"goods_attr":"13716|13758|13790","product_id":"20433","product_sn":"AS4570041581098","product_number":"122","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.25 mint cream"},{"goods_attr":"13716|13759|13790","product_id":"20434","product_sn":"AS4570041581418","product_number":"144","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.50 mint cream"},{"goods_attr":"13716|13760|13790","product_id":"20435","product_sn":"AS4570041581425","product_number":"160","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -4.75 mint cream"},{"goods_attr":"13716|13761|13790","product_id":"20436","product_sn":"AS4570041581432","product_number":"135","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.00 mint cream"},{"goods_attr":"13716|13762|13790","product_id":"20437","product_sn":"AS4570041581449","product_number":"56","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.25 mint cream"},{"goods_attr":"13716|13763|13790","product_id":"20438","product_sn":"AS4570041581456","product_number":"155","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.50 mint cream"},{"goods_attr":"13716|13764|13790","product_id":"20439","product_sn":"AS4570041581463","product_number":"73","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -5.75 mint cream"},{"goods_attr":"13716|13765|13790","product_id":"20440","product_sn":"AS4570041581470","product_number":"126","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -6.00 mint cream"},{"goods_attr":"13716|13766|13790","product_id":"20441","product_sn":"AS4570041581487","product_number":"105","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -6.50 mint cream"},{"goods_attr":"13716|13767|13790","product_id":"20442","product_sn":"AS4570041581494","product_number":"159","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -7.00 mint cream"},{"goods_attr":"13716|13768|13790","product_id":"20443","product_sn":"AS4570041581500","product_number":"55","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -7.50 mint cream"},{"goods_attr":"13716|13769|13790","product_id":"20444","product_sn":"AS4570041581517","product_number":"76","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -8.00 mint cream"},{"goods_attr":"13716|13780|13790","product_id":"20445","product_sn":"AS4570041581524","product_number":"70","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -8.50 mint cream"},{"goods_attr":"13716|13770|13790","product_id":"20446","product_sn":"AS4570041581531","product_number":"85","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -9.00 mint cream"},{"goods_attr":"13716|13771|13790","product_id":"20447","product_sn":"AS4570041581548","product_number":"75","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -9.50 mint cream"},{"goods_attr":"13716|13783|13790","product_id":"20448","product_sn":"AS4570041581555","product_number":"64","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -10.00 mint cream"},{"goods_attr":"13716|13755|13790","product_id":"20449","product_sn":"AS4570041581067","product_number":"130","product_price":"115.00","preferential_price":"0.00","color_id":"13790","search_attr":"13716|13790","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.0mm","attr_name":"10枚 -3.50 mint cream"},{"goods_attr":"13716|13744|13791","product_id":"20450","product_sn":"AS4570041581562","product_number":"318","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -0.00 misty gray"},{"goods_attr":"13716|13785|13791","product_id":"20451","product_sn":"AS4570041581579","product_number":"18","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -0.75 misty gray"},{"goods_attr":"13716|13745|13791","product_id":"20452","product_sn":"AS4570041581586","product_number":"26","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.00 misty gray"},{"goods_attr":"13716|13746|13791","product_id":"20453","product_sn":"AS4570041581593","product_number":"16","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.25 misty gray"},{"goods_attr":"13716|13747|13791","product_id":"20454","product_sn":"AS4570041581609","product_number":"11","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.50 misty gray"},{"goods_attr":"13716|13748|13791","product_id":"20455","product_sn":"AS4570041581616","product_number":"27","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -1.75 misty gray"},{"goods_attr":"13716|13749|13791","product_id":"20456","product_sn":"AS4570041581623","product_number":"44","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.00 misty gray"},{"goods_attr":"13716|13750|13791","product_id":"20457","product_sn":"AS4570041581630","product_number":"24","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.25 misty gray"},{"goods_attr":"13716|13751|13791","product_id":"20458","product_sn":"AS4570041581647","product_number":"129","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.50 misty gray"},{"goods_attr":"13716|13752|13791","product_id":"20459","product_sn":"AS4570041581654","product_number":"114","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -2.75 misty gray"},{"goods_attr":"13716|13753|13791","product_id":"20460","product_sn":"AS4570041581661","product_number":"115","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.00 misty gray"},{"goods_attr":"13716|13754|13791","product_id":"20461","product_sn":"AS4570041581678","product_number":"109","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.25 misty gray"},{"goods_attr":"13716|13756|13791","product_id":"20462","product_sn":"AS4570041581692","product_number":"97","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.75 misty gray"},{"goods_attr":"13716|13757|13791","product_id":"20463","product_sn":"AS4570041581708","product_number":"108","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.00 misty gray"},{"goods_attr":"13716|13758|13791","product_id":"20464","product_sn":"AS4570041581715","product_number":"107","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.25 misty gray"},{"goods_attr":"13716|13759|13791","product_id":"20465","product_sn":"AS4570041581722","product_number":"126","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.50 misty gray"},{"goods_attr":"13716|13760|13791","product_id":"20466","product_sn":"AS4570041581739","product_number":"129","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -4.75 misty gray"},{"goods_attr":"13716|13761|13791","product_id":"20467","product_sn":"AS4570041581746","product_number":"114","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.00 misty gray"},{"goods_attr":"13716|13762|13791","product_id":"20468","product_sn":"AS4570041581753","product_number":"56","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.25 misty gray"},{"goods_attr":"13716|13763|13791","product_id":"20469","product_sn":"AS4570041581760","product_number":"118","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.50 misty gray"},{"goods_attr":"13716|13764|13791","product_id":"20470","product_sn":"AS4570041581777","product_number":"68","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -5.75 misty gray"},{"goods_attr":"13716|13765|13791","product_id":"20471","product_sn":"AS4570041581784","product_number":"96","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -6.00 misty gray"},{"goods_attr":"13716|13766|13791","product_id":"20472","product_sn":"AS4570041581791","product_number":"93","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -6.50 misty gray"},{"goods_attr":"13716|13767|13791","product_id":"20473","product_sn":"AS4570041581807","product_number":"128","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -7.00 misty gray"},{"goods_attr":"13716|13768|13791","product_id":"20474","product_sn":"AS4570041581814","product_number":"56","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -7.50 misty gray"},{"goods_attr":"13716|13769|13791","product_id":"20475","product_sn":"AS4570041581821","product_number":"76","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -8.00 misty gray"},{"goods_attr":"13716|13780|13791","product_id":"20476","product_sn":"AS4570041581838","product_number":"75","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -8.50 misty gray"},{"goods_attr":"13716|13770|13791","product_id":"20477","product_sn":"AS4570041581845","product_number":"85","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -9.00 misty gray"},{"goods_attr":"13716|13771|13791","product_id":"20478","product_sn":"AS4570041581852","product_number":"80","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -9.50 misty gray"},{"goods_attr":"13716|13783|13791","product_id":"20479","product_sn":"AS4570041581869","product_number":"84","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -10.00 misty gray"},{"goods_attr":"13716|13755|13791","product_id":"20480","product_sn":"AS4570041581685","product_number":"106","product_price":"115.00","preferential_price":"0.00","color_id":"13791","search_attr":"13716|13791","base_curve":"8.6mm","water_content":"38%","diameter":"14.2mm","coloring_diameter":"13.2mm","attr_name":"10枚 -3.50 misty gray"}]
     * comment : []
     * recommend : [{"product_price":"245.00","search_attr":"3222|3223","preferential_price":"0.00","color_id":"3222","product_number":"10","goods_id":"86","goods_name":"中国版 Naturali日抛 Charming Brown 30枚","virtual_sales":"21","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Naturali","goods_thumb":"http://img.jealook.com/backend/20200424/1587713337_3842.png"},{"product_price":"225.00","search_attr":"1307|1332","preferential_price":"0.00","color_id":"1332","product_number":"10","goods_id":"46","goods_name":"Miche Bloomin日抛型 Smoky Marron 104 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Michebloomin","goods_thumb":"http://img.jealook.com/backend/20200402/1585816037_5105.png"},{"product_price":"205.00","search_attr":"118|8636","preferential_price":"0.00","color_id":"8636","product_number":"14","goods_id":"5","goods_name":"Angelcolor bambi series日抛型 Milk Beige 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802090","promote_end_date":"1598371200","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20200821/1597998882_8312.png"},{"product_price":"108.00","search_attr":"1650|1676","preferential_price":"85.00","color_id":"1676","product_number":"5","goods_id":"57","goods_name":"Pienage 日抛型 108 Moony 12枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802466","promote_end_date":"1598371200","brand_name":"Pienage","goods_thumb":"http://img.jealook.com/backend/20200402/1585809619_9816.png"},{"product_price":"105.00","search_attr":"870|891","preferential_price":"104.00","color_id":"891","product_number":"170","goods_id":"30","goods_name":"Juicy drop日抛型 Kiwi 10枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Juicydrop","goods_thumb":"http://img.jealook.com/backend/20200401/1585732390_3148.png"},{"product_price":"245.00","search_attr":"3223|3249","preferential_price":"0.00","color_id":"3249","product_number":"9","goods_id":"86","goods_name":"中国版 Naturali日抛 Charming Hazel 30枚","virtual_sales":"21","suppliers_id":"1","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Naturali","goods_thumb":"http://img.jealook.com/backend/20200424/1587713337_2602.png"},{"product_price":"225.00","search_attr":"1307|11864","preferential_price":"0.00","color_id":"11864","product_number":"5","goods_id":"46","goods_name":"Miche Bloomin日抛型 Virgin Honey 204 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Michebloomin","goods_thumb":"http://img.jealook.com/backend/20200916/1600229958_1590.png"},{"product_price":"205.00","search_attr":"118|13219","preferential_price":"0.00","color_id":"13219","product_number":"3","goods_id":"5","goods_name":"Angelcolor bambi series日抛型 chocolate 30枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"1597802090","promote_end_date":"1598371200","brand_name":"Angelcolor bambi seriesvintage","goods_thumb":"http://img.jealook.com/backend/20201013/1602577988_5035.png"},{"product_price":"98.00","search_attr":"1798|1799","preferential_price":"0.00","color_id":"1798","product_number":"13","goods_id":"63","goods_name":"Refrear 月抛型 Refrear经典版 6枚","virtual_sales":"0","suppliers_id":"2","is_promote":0,"promote_start_date":"0","promote_end_date":"0","brand_name":"Refrear","goods_thumb":"http://img.jealook.com/backend/20200403/1585904645_8005.png"}]
     * question_list : [{"id":"68","question":"这是新款吗？","answer_count":"0","content":"","user_name":""},{"id":"46","question":"舒适度高吗？","answer_count":"0","content":"","user_name":""}]
     * salable : {"ranking":12,"type":1,"image":["http://img.jealook.com/backend/20200731/1596175279_6508.png","http://img.jealook.com/backend/20200923/1600843104_2220.png","http://img.jealook.com/backend/20200612/1591936019_7202.png"]}
     */

    private InfoBean info;
    private SalableBean salable;
    private List<AttrBean> attr;
    private List<ProductBean> product;
    private List<CommentBean> comment;
    private List<RecommendBean> recommend;
    private List<QuestionListBean> question_list;
    private ShopActiveInfoBean shopActiveInfo;
    private List<GroupListBean> group_list;
    private String surplus_time;
    private AdInfoBean ad_info;


    public AdInfoBean getAd_info() {
        return ad_info;
    }

    public void setAd_info(AdInfoBean ad_info) {
        this.ad_info = ad_info;
    }


    public String getSurplus_time() {
        return surplus_time;
    }

    public void setSurplus_time(String surplus_time) {
        this.surplus_time = surplus_time;
    }


    public List<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<GroupListBean> group_list) {
        this.group_list = group_list;
    }


    public ShopActiveInfoBean getShopActiveInfo() {
        return shopActiveInfo;
    }

    public void setShopActiveInfo(ShopActiveInfoBean shopActiveInfo) {
        this.shopActiveInfo = shopActiveInfo;
    }


    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public SalableBean getSalable() {
        return salable;
    }

    public void setSalable(SalableBean salable) {
        this.salable = salable;
    }

    public List<AttrBean> getAttr() {
        return attr;
    }

    public void setAttr(List<AttrBean> attr) {
        this.attr = attr;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public List<QuestionListBean> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(List<QuestionListBean> question_list) {
        this.question_list = question_list;
    }

    public static class AdInfoBean {
        /**
         * value :
         * type :
         * id :
         * photo :
         * list : {"goods_id":"","search_attr":"","active_id":"","url":"","text":"","id":""}
         */

        private String value;
        private String type;
        private String id;
        private String photo;
        private ListBean list;
        private String title_color;

        public String getTitle_color() {
            return title_color;
        }

        public void setTitle_color(String title_color) {
            this.title_color = title_color;
        }


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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id :
             * search_attr :
             * active_id :
             * url :
             * text :
             * id :
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

    public static class ShopActiveInfoBean {

        /**
         * short_flag :
         * long_flag :
         * act_id :
         * type :
         */

        private String short_flag;
        private String long_flag;
        private String act_id;
        private String type;
        private String group_people;
        private String group_price;
        private String astrict_num;
        private String end_time;
        private String again_invite;
        private String group_id;
        private String is_join_group;
        private String group_status;
        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }


        public String getGroup_status() {
            return group_status;
        }

        public void setGroup_status(String group_status) {
            this.group_status = group_status;
        }


        public String getIs_join_group() {
            return is_join_group;
        }

        public void setIs_join_group(String is_join_group) {
            this.is_join_group = is_join_group;
        }

        public String getAgain_invite() {
            return again_invite;
        }

        public void setAgain_invite(String again_invite) {
            this.again_invite = again_invite;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }


        public String getGroup_people() {
            return group_people;
        }

        public void setGroup_people(String group_people) {
            this.group_people = group_people;
        }

        public String getGroup_price() {
            return group_price;
        }

        public void setGroup_price(String group_price) {
            this.group_price = group_price;
        }

        public String getAstrict_num() {
            return astrict_num;
        }

        public void setAstrict_num(String astrict_num) {
            this.astrict_num = astrict_num;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShort_flag() {
            return short_flag;
        }

        public void setShort_flag(String short_flag) {
            this.short_flag = short_flag;
        }

        public String getLong_flag() {
            return long_flag;
        }

        public void setLong_flag(String long_flag) {
            this.long_flag = long_flag;
        }

        public String getAct_id() {
            return act_id;
        }

        public void setAct_id(String act_id) {
            this.act_id = act_id;
        }
    }

    public static class InfoBean {
        /**
         * color_id : 13789
         * product_id : 20388
         * product_number : 329
         * product_price : 115.00
         * preferential_price : 0.00
         * color : 棕
         * search_attr : 13716|13789
         * product_sn : AS4570041580633
         * base_curve : 8.6mm
         * water_content : 38%
         * diameter : 14.2mm
         * coloring_diameter : 13.0mm
         * goods_sn : JLH10006743
         * goods_id : 392
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * suppliers_id : 2
         * shop_name : Juicy Drop 日抛
         * is_new : 1
         * brand_name : Juicy Drop
         * surplus_time : 0
         * shop_attr_name : honey dew 10枚
         * details : ["http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064174993162.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064180412429.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064189164240.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064194560536.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064200335140.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064210855065.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064221550274.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064226432481.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064232950051.jpg","http://admin.jealook.com/ueditor/php/upload/image/20201111/1605064239125494.jpg"]
         * banner : [{"url":"http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4","type":2},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4473.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7098.png"}]
         * toss_period : 日抛
         * origin : 韩国
         * life_span : 具体见包装
         * textures : 水胶
         * user_notes : ["http://shop.jealook.com/image/ht-1.jpg","http://shop.jealook.com/image/ht-2.jpg","http://shop.jealook.com/image/ht-3.jpg","http://shop.jealook.com/image/all-1.jpg"]
         * waybill_url : http://h5.jealook.com/vinnlook/htPackage.html
         * post_fee : 邮费￥10.00,满200元包邮(部分地区)
         * tossPeriodId : 297
         * member_discount : 109.25
         * is_collect : 0
         * comment_count : 0
         * question_count : 3
         */

        private String color_id;
        private String product_id;
        private String product_number;
        private String product_price;
        private String preferential_price;
        private String color;
        private String search_attr;
        private String product_sn;
        private String base_curve;
        private String water_content;
        private String diameter;
        private String coloring_diameter;
        private String goods_sn;
        private String goods_id;
        private String is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String suppliers_id;
        private String shop_name;
        private String is_new;
        private String brand_name;
        private String surplus_time;
        private String shop_attr_name;
        private String toss_period;
        private String origin;
        private String life_span;
        private String textures;
        private String waybill_url;
        private String post_fee;
        private String tossPeriodId;
        private String member_discount;
        private int is_collect;
        private String comment_count;
        private String question_count;
        private List<String> details;
        private List<BannerBean> banner;
        private List<String> user_notes;
        private String virtual_sales;
        private String border_image;
        private String active_name;
        private String image_code;
        private String purchasing;
        private String presell;
        private String goods_brief;
        private String is_show_sye;
        private String is_group;

        public String getIs_group() {
            return is_group;
        }

        public void setIs_group(String is_group) {
            this.is_group = is_group;
        }

        public String getIs_show_sye() {
            return is_show_sye;
        }

        public void setIs_show_sye(String is_show_sye) {
            this.is_show_sye = is_show_sye;
        }

        public String getGoods_brief() {
            return goods_brief;
        }

        public void setGoods_brief(String goods_brief) {
            this.goods_brief = goods_brief;
        }

        public String getPresell() {
            return presell;
        }

        public void setPresell(String presell) {
            this.presell = presell;
        }

        public String getPurchasing() {
            return purchasing;
        }

        public void setPurchasing(String purchasing) {
            this.purchasing = purchasing;
        }


        public String getImage_code() {
            return image_code;
        }

        public void setImage_code(String image_code) {
            this.image_code = image_code;
        }

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
        }


        public String getBorder_image() {
            return border_image;
        }

        public void setBorder_image(String border_image) {
            this.border_image = border_image;
        }


        public String getVirtual_sales() {
            return virtual_sales;
        }

        public void setVirtual_sales(String virtual_sales) {
            this.virtual_sales = virtual_sales;
        }


        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_number() {
            return product_number;
        }

        public void setProduct_number(String product_number) {
            this.product_number = product_number;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getPreferential_price() {
            return preferential_price;
        }

        public void setPreferential_price(String preferential_price) {
            this.preferential_price = preferential_price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
        }

        public String getProduct_sn() {
            return product_sn;
        }

        public void setProduct_sn(String product_sn) {
            this.product_sn = product_sn;
        }

        public String getBase_curve() {
            return base_curve;
        }

        public void setBase_curve(String base_curve) {
            this.base_curve = base_curve;
        }

        public String getWater_content() {
            return water_content;
        }

        public void setWater_content(String water_content) {
            this.water_content = water_content;
        }

        public String getDiameter() {
            return diameter;
        }

        public void setDiameter(String diameter) {
            this.diameter = diameter;
        }

        public String getColoring_diameter() {
            return coloring_diameter;
        }

        public void setColoring_diameter(String coloring_diameter) {
            this.coloring_diameter = coloring_diameter;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
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

        public String getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(String suppliers_id) {
            this.suppliers_id = suppliers_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getSurplus_time() {
            return surplus_time;
        }

        public void setSurplus_time(String surplus_time) {
            this.surplus_time = surplus_time;
        }

        public String getShop_attr_name() {
            return shop_attr_name;
        }

        public void setShop_attr_name(String shop_attr_name) {
            this.shop_attr_name = shop_attr_name;
        }

        public String getToss_period() {
            return toss_period;
        }

        public void setToss_period(String toss_period) {
            this.toss_period = toss_period;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getLife_span() {
            return life_span;
        }

        public void setLife_span(String life_span) {
            this.life_span = life_span;
        }

        public String getTextures() {
            return textures;
        }

        public void setTextures(String textures) {
            this.textures = textures;
        }

        public String getWaybill_url() {
            return waybill_url;
        }

        public void setWaybill_url(String waybill_url) {
            this.waybill_url = waybill_url;
        }

        public String getPost_fee() {
            return post_fee;
        }

        public void setPost_fee(String post_fee) {
            this.post_fee = post_fee;
        }

        public String getTossPeriodId() {
            return tossPeriodId;
        }

        public void setTossPeriodId(String tossPeriodId) {
            this.tossPeriodId = tossPeriodId;
        }

        public String getMember_discount() {
            return member_discount;
        }

        public void setMember_discount(String member_discount) {
            this.member_discount = member_discount;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getQuestion_count() {
            return question_count;
        }

        public void setQuestion_count(String question_count) {
            this.question_count = question_count;
        }

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<String> getUser_notes() {
            return user_notes;
        }

        public void setUser_notes(List<String> user_notes) {
            this.user_notes = user_notes;
        }

        public static class BannerBean {
            /**
             * url : http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4
             * type : 2
             */

            private String url;
            private int type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }

    public static class SalableBean {
        /**
         * ranking : 12
         * type : 1
         * image : ["http://img.jealook.com/backend/20200731/1596175279_6508.png","http://img.jealook.com/backend/20200923/1600843104_2220.png","http://img.jealook.com/backend/20200612/1591936019_7202.png"]
         */

        private String ranking;
        private String type;
        private List<String> image;

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }

    public static class AttrBean {
        /**
         * attr_name : 颜色
         * attr_id : 1
         * value : [{"goods_attr_id":"13789","attr_value":"honey dew","banner":[{"url":"http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4","type":2},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4473.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7098.png"}]},{"goods_attr_id":"13790","attr_value":"mint cream","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8770.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4925.png"}]},{"goods_attr_id":"13791","attr_value":"misty gray","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7455.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8610.png"}]},{"goods_attr_id":"13788","attr_value":"pure sky","banner":[{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_8563.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_6176.png"}]}]
         */

        private String attr_name;
        private String attr_id;
        private List<ValueBean> value;

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public String getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(String attr_id) {
            this.attr_id = attr_id;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            /**
             * goods_attr_id : 13789
             * attr_value : honey dew
             * banner : [{"url":"http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4","type":2},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_4473.png"},{"type":1,"url":"http://img.jealook.com/backend/20201111/1605089590_7098.png"}]
             */

            private String goods_attr_id;
            private String attr_value;
            private List<BannerBeanX> banner;
            private boolean isSelected = false;//0代表没选择，1代表选择，2代表有。3代表没有
            private boolean degree = false;//0代表没选择，1代表选择，2代表有。3代表没有
            private List<String> colorList;
            private List<String> typeList;
            private List<String> degreesIdList;
            private List<ProductBean> productBeanList;
            private String flage;
            private String shop_attr_name;

            public String getShop_attr_name() {
                return shop_attr_name;
            }

            public void setShop_attr_name(String shop_attr_name) {
                this.shop_attr_name = shop_attr_name;
            }


            public String getFlage() {
                return flage;
            }

            public void setFlage(String flage) {
                this.flage = flage;
            }

            public List<ProductBean> getProductBeanList() {
                return productBeanList;
            }

            public void setProductBeanList(List<ProductBean> productBeanList) {
                this.productBeanList = productBeanList;
            }

            public List<String> getColorList() {
                return colorList;
            }

            public void setValue(List<String> setColorList) {
                this.colorList = colorList;
            }

            public List<String> getTypeList() {
                return typeList;
            }

            public void setTypeList(List<String> typeList) {
                this.typeList = typeList;
            }

            public List<String> getDegreesIdList() {
                return degreesIdList;
            }

            public void setDegreesIdList(List<String> degreesIdList) {
                this.degreesIdList = degreesIdList;
            }


            public boolean getDegree() {
                return degree;
            }

            public void setDegree(boolean degree) {
                this.degree = degree;
            }

            public boolean getIsSelected() {
                return isSelected;
            }

            public void setIsSelected(boolean isSelected) {
                this.isSelected = isSelected;
            }


            public String getGoods_attr_id() {
                return goods_attr_id;
            }

            public void setGoods_attr_id(String goods_attr_id) {
                this.goods_attr_id = goods_attr_id;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }

            public List<BannerBeanX> getBanner() {
                return banner;
            }


            public void setBanner(List<BannerBeanX> banner) {
                this.banner = banner;
            }

            public static class BannerBeanX {
                /**
                 * url : http://img.jealook.com/app_video/20201123/20201123102455_79815.mp4
                 * type : 2
                 */

                private String url;
                private int type;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }


    public static class CommentBean {

        /**
         * user_name : 星雨
         * add_time : 2020-05-03
         * image : ["http://img.vinnlook.combackend/20200401/1585726597_1699.png"]
         * content : 1111
         * product_id : 2038
         * search_attr : 529|555
         * img_url : http://thirdwx.qlogo.cn/mmopen/vi_32/G8T3U4de9iaPb22cxW2Y05ReqwElxianetEn0FoK7CtbiaDSITsjdUEvkyyVfYOIt5Hld2GrUKEKNrP9l0RFJtaxw/132
         * info : 规格：20枚   颜色：Chiff on Brown
         */

        private String user_name;
        private String add_time;
        private String content;
        private String product_id;
        private String search_attr;
        private String img_url;
        private String info;
        private List<String> image;
        private String is_member;
        private String reply_content;

        public String getReply_content() {
            return reply_content;
        }

        public void setReply_content(String reply_content) {
            this.reply_content = reply_content;
        }

        public String getIs_member() {
            return is_member;
        }

        public void setIs_member(String is_member) {
            this.is_member = is_member;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getSearch_attr() {
            return search_attr;
        }

        public void setSearch_attr(String search_attr) {
            this.search_attr = search_attr;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }

    public static class RecommendBean {
        /**
         * product_price : 245.00
         * search_attr : 3222|3223
         * preferential_price : 0.00
         * color_id : 3222
         * product_number : 10
         * goods_id : 86
         * goods_name : 中国版 Naturali日抛 Charming Brown 30枚
         * virtual_sales : 21
         * suppliers_id : 1
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * brand_name : Naturali
         * goods_thumb : http://img.jealook.com/backend/20200424/1587713337_3842.png
         */

        private String product_price;
        private String search_attr;
        private String preferential_price;
        private String color_id;
        private String product_number;
        private String goods_id;
        private String goods_name;
        private String virtual_sales;
        private String suppliers_id;
        private int is_promote;
        private String promote_start_date;
        private String promote_end_date;
        private String brand_name;
        private String goods_thumb;

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
    }

    public static class GroupListBean {
        /**
         * user_id :
         * user_name :
         * img_url :
         */

        private String user_id;
        private String user_name;
        private String img_url;
        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }

    public static class QuestionListBean {
        /**
         * id : 68
         * question : 这是新款吗？
         * answer_count : 0
         * content :
         * user_name :
         */

        private String id;
        private String question;
        private String answer_count;
        private String content;
        private String user_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer_count() {
            return answer_count;
        }

        public void setAnswer_count(String answer_count) {
            this.answer_count = answer_count;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}

