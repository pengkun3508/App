package com.vinnlook.www.surface.bean;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDong2Bean extends BaseBean {
    /**
     * id : 5
     * name : 买二赠一
     * active_list : [{"act_name":"买二赠一","gift":"141","act_range_ext":"141","start_time":"1611037173","end_time":"1705593600","act_type":"0","act_type_ext":"0.00","type":"5","min_amount":"2.00","figure":"1","type_name":"买2件赠1件","details":"以下商品买2赠MerMer Mix系列 合作款 日抛","goods_list":[{"product_id":"9244","color_id":"5551","product_price":"115.00","preferential_price":"0.00","search_attr":"5551|5552","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Sea Green 10片","virtual_sales":"15","attr_value":"Sea Green","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8512.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9300","color_id":"5581","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5581","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Slate Gray 10片","virtual_sales":"15","attr_value":"Slate Gray","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_9970.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9328","color_id":"5582","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5582","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Sea Blue 10片","virtual_sales":"15","attr_value":"Sea Blue","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_7915.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9384","color_id":"5584","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5584","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Amber 10片","virtual_sales":"15","attr_value":"Amber","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_1570.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"23700","color_id":"20341","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20341","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Soda Blue 10片","virtual_sales":"15","attr_value":"Soda Blue","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4675.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"23764","color_id":"20342","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20342","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Moss Green 10片","virtual_sales":"15","attr_value":"Moss Green","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8111.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"25057","color_id":"20343","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20343","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Pearl Pink 10片","virtual_sales":"15","attr_value":"Pearl Pink","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_1505.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"19179","color_id":"13150","product_price":"115.00","preferential_price":"72.50","search_attr":"5552|13150","diameter":" 14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Salmon 10片","virtual_sales":"15","attr_value":"Salmon","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4550.png","attr":"直径： 14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"19211","color_id":"13151","product_price":"115.00","preferential_price":"72.50","search_attr":"5552|13151","diameter":" 14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Smoky Grege 10片","virtual_sales":"15","attr_value":"Smoky Grege","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4074.png","attr":"直径： 14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""}]},{"act_name":"买一赠一","gift":"392","act_range_ext":"392","start_time":"1611037412","end_time":"1674057600","act_type":"0","act_type_ext":"0.00","type":"5","min_amount":"1.00","figure":"1","type_name":"买1件赠1件","details":"以下商品买1赠Juicy Drop 日抛","goods_list":[{"product_id":"20357","color_id":"13788","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13788","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38%","goods_id":"392","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Pure Sky 10片","virtual_sales":"177","attr_value":"Pure Sky","goods_thumb":"http://img.jealook.com/backend/20201228/1609147501_1415.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38%","active_name":""},{"product_id":"20393","color_id":"13789","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13789","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38%","goods_id":"392","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Honey Dew 10片","virtual_sales":"177","attr_value":"Honey Dew","goods_thumb":"http://img.jealook.com/backend/20201228/1609147501_9985.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38%","active_name":""},{"product_id":"20419","color_id":"13790","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13790","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38%","goods_id":"392","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Mint Cream 10片","virtual_sales":"177","attr_value":"Mint Cream","goods_thumb":"http://img.jealook.com/backend/20201228/1609147855_9311.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38%","active_name":""},{"product_id":"20460","color_id":"13791","product_price":"115.00","preferential_price":"0.00","search_attr":"13716|13791","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38%","goods_id":"392","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Misty Gray 10片","virtual_sales":"177","attr_value":"Misty Gray","goods_thumb":"http://img.jealook.com/backend/20201228/1609147501_5995.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38%","active_name":""}]},{"act_name":"买二赠一","gift":"179","act_range_ext":"179","start_time":"1611043045","end_time":"1674057600","act_type":null,"act_type_ext":null,"type":"5","min_amount":"2.00","figure":"1","type_name":"买2件赠1件","details":"以下商品买2赠Mermer Natural 系列 日抛","goods_list":[{"product_id":"11028","color_id":"7991","product_price":"115.00","preferential_price":"0.00","search_attr":"7960|7991","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Linen 10片","virtual_sales":"77","attr_value":"Linen","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_6508.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"11047","color_id":"7992","product_price":"115.00","preferential_price":"0.00","search_attr":"7960|7992","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Charcoal Gray 10片","virtual_sales":"77","attr_value":"Charcoal Gray","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_5740.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"11013","color_id":"7959","product_price":"115.00","preferential_price":"0.00","search_attr":"7959|7960","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Lime Green 10片","virtual_sales":"77","attr_value":"Lime Green","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_8093.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"11078","color_id":"7993","product_price":"115.00","preferential_price":"0.00","search_attr":"7960|7993","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Blue Violet 10片","virtual_sales":"77","attr_value":"Blue Violet","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_5520.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"11119","color_id":"7994","product_price":"115.00","preferential_price":"0.00","search_attr":"7960|7994","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Aqua Water 10片","virtual_sales":"77","attr_value":"Aqua Water","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_2012.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"11140","color_id":"7995","product_price":"115.00","preferential_price":"0.00","search_attr":"7960|7995","diameter":"14.2mm","base_curve":"8.7mm","water_content":"55%","goods_id":"179","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Mermer Natural 系列 日抛 Coral Pink 10片","virtual_sales":"77","attr_value":"Coral Pink","goods_thumb":"http://img.jealook.com/backend/20200731/1596175279_9918.png","attr":"直径：14.2mm | 基弧：8.7mm | 含水量：55%","active_name":""}]},{"act_name":"买二赠一","gift":"387","act_range_ext":"387","start_time":"1611043386","end_time":"1642579317","act_type":null,"act_type_ext":null,"type":"5","min_amount":"2.00","figure":"1","type_name":"买2件赠1件","details":"以下商品买2赠MerMer Light 系列 日抛","goods_list":[{"product_id":"20024","color_id":"13401","product_price":"115.00","preferential_price":"0.00","search_attr":"13329|13401","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_id":"387","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Light 系列 日抛 Light Lime 10片","virtual_sales":"95","attr_value":"Light Lime","goods_thumb":"http://img.jealook.com/backend/20201021/1603259751_5069.png","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"20055","color_id":"13402","product_price":"115.00","preferential_price":"0.00","search_attr":"13329|13402","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_id":"387","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Light 系列 日抛 Light Peach 10片","virtual_sales":"95","attr_value":"Light Peach","goods_thumb":"http://img.jealook.com/backend/20201021/1603259751_3120.png","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"20086","color_id":"13403","product_price":"115.00","preferential_price":"0.00","search_attr":"13329|13403","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_id":"387","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Light 系列 日抛 Light Cyan 10片","virtual_sales":"95","attr_value":"Light Cyan","goods_thumb":"http://img.jealook.com/backend/20201021/1603259751_9557.png","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"20117","color_id":"13404","product_price":"115.00","preferential_price":"0.00","search_attr":"13329|13404","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_id":"387","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Light 系列 日抛 Light Gray 10片","virtual_sales":"95","attr_value":"Light Gray","goods_thumb":"http://img.jealook.com/backend/20201021/1603259751_2122.png","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":""},{"product_id":"20179","color_id":"13405","product_price":"115.00","preferential_price":"0.00","search_attr":"13329|13405","diameter":"14.0mm","base_curve":"8.7mm","water_content":"55%","goods_id":"387","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Light 系列 日抛 Light Navy 10片","virtual_sales":"95","attr_value":"Light Navy","goods_thumb":"http://img.jealook.com/backend/20201021/1603259751_9894.png","attr":"直径：14.0mm | 基弧：8.7mm | 含水量：55%","active_name":""}]}]
     */

    private String id;
    private String name;
    private List<ActiveListBean> active_list;
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }



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

    public List<ActiveListBean> getActive_list() {
        return active_list;
    }

    public void setActive_list(List<ActiveListBean> active_list) {
        this.active_list = active_list;
    }

    public static class ActiveListBean {
        /**
         * act_name : 买二赠一
         * gift : 141
         * act_range_ext : 141
         * start_time : 1611037173
         * end_time : 1705593600
         * act_type : 0
         * act_type_ext : 0.00
         * type : 5
         * min_amount : 2.00
         * figure : 1
         * type_name : 买2件赠1件
         * details : 以下商品买2赠MerMer Mix系列 合作款 日抛
         * goods_list : [{"product_id":"9244","color_id":"5551","product_price":"115.00","preferential_price":"0.00","search_attr":"5551|5552","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Sea Green 10片","virtual_sales":"15","attr_value":"Sea Green","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8512.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9300","color_id":"5581","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5581","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Slate Gray 10片","virtual_sales":"15","attr_value":"Slate Gray","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_9970.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9328","color_id":"5582","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5582","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Sea Blue 10片","virtual_sales":"15","attr_value":"Sea Blue","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_7915.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"9384","color_id":"5584","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|5584","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Amber 10片","virtual_sales":"15","attr_value":"Amber","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_1570.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"23700","color_id":"20341","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20341","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Soda Blue 10片","virtual_sales":"15","attr_value":"Soda Blue","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4675.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"23764","color_id":"20342","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20342","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Moss Green 10片","virtual_sales":"15","attr_value":"Moss Green","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_8111.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"25057","color_id":"20343","product_price":"115.00","preferential_price":"0.00","search_attr":"5552|20343","diameter":"14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Pearl Pink 10片","virtual_sales":"15","attr_value":"Pearl Pink","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_1505.png","attr":"直径：14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"19179","color_id":"13150","product_price":"115.00","preferential_price":"72.50","search_attr":"5552|13150","diameter":" 14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Salmon 10片","virtual_sales":"15","attr_value":"Salmon","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4550.png","attr":"直径： 14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""},{"product_id":"19211","color_id":"13151","product_price":"115.00","preferential_price":"72.50","search_attr":"5552|13151","diameter":" 14.2mm","base_curve":"8.6mm","water_content":"38.5%","goods_id":"141","is_promote":0,"promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"MerMer Mix系列 合作款 日抛 Smoky Grege 10片","virtual_sales":"15","attr_value":"Smoky Grege","goods_thumb":"http://img.jealook.com/backend/20210107/1610002858_4074.png","attr":"直径： 14.2mm | 基弧：8.6mm | 含水量：38.5%","active_name":""}]
         */

        private String act_name;
        private String gift;
        private String act_range_ext;
        private String start_time;
        private String end_time;
        private String act_type;
        private String act_type_ext;
        private String type;
        private String min_amount;
        private String figure;
        private String type_name;
        private String details;
        private List<GoodsListBean> goods_list;

        public String getAct_name() {
            return act_name;
        }

        public void setAct_name(String act_name) {
            this.act_name = act_name;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getAct_range_ext() {
            return act_range_ext;
        }

        public void setAct_range_ext(String act_range_ext) {
            this.act_range_ext = act_range_ext;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAct_type() {
            return act_type;
        }

        public void setAct_type(String act_type) {
            this.act_type = act_type;
        }

        public String getAct_type_ext() {
            return act_type_ext;
        }

        public void setAct_type_ext(String act_type_ext) {
            this.act_type_ext = act_type_ext;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * product_id : 9244
             * color_id : 5551
             * product_price : 115.00
             * preferential_price : 0.00
             * search_attr : 5551|5552
             * diameter : 14.2mm
             * base_curve : 8.6mm
             * water_content : 38.5%
             * goods_id : 141
             * is_promote : 0
             * promote_start_date : 0
             * promote_end_date : 0
             * suppliers_id : 2
             * goods_name : MerMer Mix系列 合作款 日抛 Sea Green 10片
             * virtual_sales : 15
             * attr_value : Sea Green
             * goods_thumb : http://img.jealook.com/backend/20210107/1610002858_8512.png
             * attr : 直径：14.2mm | 基弧：8.6mm | 含水量：38.5%
             * active_name :
             */

            private String product_id;
            private String color_id;
            private String product_price;
            private String preferential_price;
            private String search_attr;
            private String diameter;
            private String base_curve;
            private String water_content;
            private String goods_id;
            private int is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String suppliers_id;
            private String goods_name;
            private String virtual_sales;
            private String attr_value;
            private String goods_thumb;
            private String attr;
            private String active_name;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
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

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
            }

            public String getDiameter() {
                return diameter;
            }

            public void setDiameter(String diameter) {
                this.diameter = diameter;
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

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
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

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getAttr() {
                return attr;
            }

            public void setAttr(String attr) {
                this.attr = attr;
            }

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
            }
        }
    }
}
