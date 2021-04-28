package com.vinnlook.www.http.model;

import java.util.List;

import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @Description:
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class AllOrderListBean extends BaseBean {
    /**
     * list : [{"order_id":"1865","order_sn":"EC2021020917370660","order_status":"1","shipping_status":"0","pay_status":"0","goods_amount":"9.90","order_amount":"19.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":1,"shop_list":[{"product_id":"25273","goods_id":"471","goods_attr":"20686|20749|20893","color_id":"20893","search_attr":"20686|20893","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Larimar Blue 5片","number":"1","product_price":"9.90","rec_id":"2388","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-4.75,Larimar Blue","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_5394.png","refund_id":""}],"num":1},{"order_id":"1860","order_sn":"EC2021020917205465","order_status":"1","shipping_status":"0","pay_status":"2","goods_amount":"9.90","order_amount":"19.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":2,"shop_list":[{"product_id":"25221","goods_id":"471","goods_attr":"20686|20753|20892","color_id":"20892","search_attr":"20686|20892","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Crystal  Gray 5片","number":"1","product_price":"9.90","rec_id":"2380","comment_id":"0","refund_status":"-1","shop_status":1,"goods_attr_name":"5片,-5.75,Crystal  Gray","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3613.png","refund_id":"505"}],"num":1},{"order_id":"1848","order_sn":"EC2021020916202073","order_status":"1","shipping_status":"0","pay_status":"0","goods_amount":"9.90","order_amount":"19.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":1,"shop_list":[{"product_id":"25247","goods_id":"471","goods_attr":"20686|20751|20891","color_id":"20891","search_attr":"20686|20891","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Hazel Opal 5片","number":"1","product_price":"9.90","rec_id":"2363","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-5.25,Hazel Opal","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","refund_id":""}],"num":1},{"order_id":"1843","order_sn":"EC2021020912213075","order_status":"1","shipping_status":"1","pay_status":"2","goods_amount":"1.90","order_amount":"11.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":3,"shop_list":[{"product_id":"21216","goods_id":"420","goods_attr":"15977|16025|16027","color_id":"16027","search_attr":"15977|16027","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"隐形眼镜佩戴工具 伴侣盒 收纳盒 小镊子 颜色随机 盒","number":"1","product_price":"1.90","rec_id":"2357","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"盒,盒,小镊子 颜色随机","goods_thumb":"http://img.jealook.com/backend/20210118/1610952942_6226.png","refund_id":""}],"num":1},{"order_id":"1842","order_sn":"EC2021020912211473","order_status":"1","shipping_status":"1","pay_status":"2","goods_amount":"39.00","order_amount":"49.00","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":3,"shop_list":[{"product_id":"13684","goods_id":"316","goods_attr":"10233|10234|10236","color_id":"10233","search_attr":"10233|10234","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Sustainable 日抛 Pale Gray 10片","number":"1","product_price":"39.00","rec_id":"2358","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"Pale Gray,10片,-1.25","goods_thumb":"http://img.jealook.com/backend/20200911/1599812128_2505.png","refund_id":""}],"num":1},{"order_id":"1839","order_sn":"EC2021020910460568","order_status":"1","shipping_status":"1","pay_status":"2","goods_amount":"11.90","order_amount":"21.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":3,"shop_list":[{"product_id":"22731","goods_id":"467","goods_attr":"20330|20337|20338","color_id":"20337","search_attr":"20337|20338","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜工具 （碗+刷） 1组","number":"1","product_price":"3.00","rec_id":"2351","comment_id":"0","refund_status":"2","shop_status":2,"goods_attr_name":"无,（碗+刷）,1组","goods_thumb":"http://img.jealook.com/backend/20201230/1609298967_6783.png","refund_id":"478"},{"product_id":"22446","goods_id":"420","goods_attr":"15977|16025|18771","color_id":"18771","search_attr":"15977|18771","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"隐形眼镜佩戴工具 伴侣盒 收纳盒 护理盒 大款 颜色随机 盒","number":"1","product_price":"8.90","rec_id":"2352","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"盒,盒,护理盒 大款 颜色随机","goods_thumb":"http://img.jealook.com/backend/20210119/1611052964_8036.png","refund_id":"479"}],"num":2},{"order_id":"1838","order_sn":"EC2021020910454961","order_status":"1","shipping_status":"1","pay_status":"2","goods_amount":"48.90","order_amount":"58.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":3,"shop_list":[{"product_id":"25273","goods_id":"471","goods_attr":"20686|20749|20893","color_id":"20893","search_attr":"20686|20893","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Larimar Blue 5片","number":"1","product_price":"9.90","rec_id":"2353","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-4.75,Larimar Blue","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_5394.png","refund_id":"480"},{"product_id":"18795","goods_id":"316","goods_attr":"10234|10236|13213","color_id":"13213","search_attr":"10234|13213","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Sustainable 日抛 Light Brown 10片","number":"1","product_price":"39.00","rec_id":"2354","comment_id":"0","refund_status":"3","shop_status":3,"goods_attr_name":"10片,-1.25,Light Brown","goods_thumb":"http://img.jealook.com/backend/20201013/1602573424_7794.png","refund_id":"481"}],"num":2},{"order_id":"1837","order_sn":"EC2021020910424865","order_status":"2","shipping_status":"0","pay_status":"0","goods_amount":"61.80","order_amount":"81.80","bonus":"0.00","shipping_fee":"20.00","is_comment":"0","status":7,"shop_list":[{"product_id":"22731","goods_id":"467","goods_attr":"20330|20337|20338","color_id":"20337","search_attr":"20337|20338","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜工具 （碗+刷） 1组","number":"1","product_price":"3.00","rec_id":"2347","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"无,（碗+刷）,1组","goods_thumb":"http://img.jealook.com/backend/20201230/1609298967_6783.png","refund_id":""},{"product_id":"25942","goods_id":"420","goods_attr":"15977|16025|22916","color_id":"22916","search_attr":"15977|22916","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"隐形眼镜佩戴工具 伴侣盒 收纳盒 护理盒 新款 颜色随机 盒","number":"1","product_price":"9.90","rec_id":"2348","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"盒,盒,护理盒 新款 颜色随机","goods_thumb":"http://img.jealook.com/backend/20210122/1611297344_2058.png","refund_id":""},{"product_id":"25244","goods_id":"471","goods_attr":"20686|20748|20891","color_id":"20891","search_attr":"20686|20891","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Hazel Opal 5片","number":"1","product_price":"9.90","rec_id":"2349","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-4.50,Hazel Opal","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","refund_id":""},{"product_id":"13733","goods_id":"316","goods_attr":"10234|10237|10259","color_id":"10259","search_attr":"10234|10259","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Sustainable 日抛 Pale Brown 10片","number":"1","product_price":"39.00","rec_id":"2350","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"10片,-1.50,Pale Brown","goods_thumb":"http://img.jealook.com/backend/20201013/1602573424_7738.png","refund_id":""}],"num":4},{"order_id":"1836","order_sn":"EC2021020910400475","order_status":"1","shipping_status":"0","pay_status":"0","goods_amount":"61.80","order_amount":"81.80","bonus":"0.00","shipping_fee":"20.00","is_comment":"0","status":1,"shop_list":[{"product_id":"22731","goods_id":"467","goods_attr":"20330|20337|20338","color_id":"20337","search_attr":"20337|20338","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜工具 （碗+刷） 1组","number":"1","product_price":"3.00","rec_id":"2343","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"无,（碗+刷）,1组","goods_thumb":"http://img.jealook.com/backend/20201230/1609298967_6783.png","refund_id":""},{"product_id":"25942","goods_id":"420","goods_attr":"15977|16025|22916","color_id":"22916","search_attr":"15977|22916","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"隐形眼镜佩戴工具 伴侣盒 收纳盒 护理盒 新款 颜色随机 盒","number":"1","product_price":"9.90","rec_id":"2344","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"盒,盒,护理盒 新款 颜色随机","goods_thumb":"http://img.jealook.com/backend/20210122/1611297344_2058.png","refund_id":""},{"product_id":"25266","goods_id":"471","goods_attr":"20686|20742|20893","color_id":"20893","search_attr":"20686|20893","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Larimar Blue 5片","number":"1","product_price":"9.90","rec_id":"2345","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-3.00,Larimar Blue","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_5394.png","refund_id":""},{"product_id":"18796","goods_id":"316","goods_attr":"10234|10237|13213","color_id":"13213","search_attr":"10234|13213","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Sustainable 日抛 Light Brown 10片","number":"1","product_price":"39.00","rec_id":"2346","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"10片,-1.50,Light Brown","goods_thumb":"http://img.jealook.com/backend/20201013/1602573424_7794.png","refund_id":""}],"num":4},{"order_id":"1834","order_sn":"EC2021020910141671","order_status":"4","shipping_status":"4","pay_status":"3","goods_amount":"48.90","order_amount":"58.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":5,"shop_list":[{"product_id":"25216","goods_id":"471","goods_attr":"20686|20748|20892","color_id":"20892","search_attr":"20686|20892","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Crystal  Gray 5片","number":"1","product_price":"9.90","rec_id":"2340","comment_id":"0","refund_status":"2","shop_status":2,"goods_attr_name":"5片,-4.50,Crystal  Gray","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3613.png","refund_id":"488"},{"product_id":"13684","goods_id":"316","goods_attr":"10233|10234|10236","color_id":"10233","search_attr":"10233|10234","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Sustainable 日抛 Pale Gray 10片","number":"1","product_price":"39.00","rec_id":"2341","comment_id":"0","refund_status":"2","shop_status":2,"goods_attr_name":"Pale Gray,10片,-1.25","goods_thumb":"http://img.jealook.com/backend/20200911/1599812128_2505.png","refund_id":"489"}],"num":2},{"order_id":"1833","order_sn":"EC2021020909275952","order_status":"1","shipping_status":"0","pay_status":"0","goods_amount":"55.00","order_amount":"65.00","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":1,"shop_list":[{"product_id":"7384","goods_id":"91","goods_attr":"3371|3372|3374","color_id":"3371","search_attr":"3371|3372","is_promote":"0","promote_start_date":"1599550839","promote_end_date":"1606216724","suppliers_id":"1","goods_name":"Femii 妃蜜莉日抛 自然灰棕 10片","number":"1","product_price":"55.00","rec_id":"2339","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"自然灰棕,10片,-1.25","goods_thumb":"http://img.jealook.com/backend/20200622/1592807730_1191.png","refund_id":""}],"num":1},{"order_id":"1685","order_sn":"EC2021020210371376","order_status":"1","shipping_status":"1","pay_status":"2","goods_amount":"115.00","order_amount":"125.00","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":3,"shop_list":[{"product_id":"20367","goods_id":"392","goods_attr":"13716|13753|13788","color_id":"13788","search_attr":"13716|13788","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Pure Sky 10片","number":"1","product_price":"115.00","rec_id":"2159","comment_id":"0","refund_status":"3","shop_status":3,"goods_attr_name":"10片,-3.00,Pure Sky","goods_thumb":"http://img.jealook.com/backend/20201228/1609147501_1415.png","refund_id":"397"},{"product_id":"20357","goods_id":"392","goods_attr":"13716|13744|13788","color_id":"13788","search_attr":"13716|13788","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Juicy Drop 日抛 Pure Sky 10片","number":"1","product_price":"0.00","rec_id":"2160","comment_id":"0","refund_status":"2","shop_status":2,"goods_attr_name":"10片,-0.00,Pure Sky","goods_thumb":"http://img.jealook.com/backend/20201228/1609147501_1415.png","refund_id":"398"}],"num":2},{"order_id":"1684","order_sn":"EC2021020210251775","order_status":"6","shipping_status":"2","pay_status":"2","goods_amount":"4.90","order_amount":"14.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"1","status":6,"shop_list":[{"product_id":"21216","goods_id":"420","goods_attr":"15977|16025|16027","color_id":"16027","search_attr":"15977|16027","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"隐形眼镜佩戴工具 伴侣盒 收纳盒 小镊子 颜色随机 盒","number":"1","product_price":"1.90","rec_id":"2155","comment_id":"32","refund_status":"0","shop_status":0,"goods_attr_name":"盒,盒,小镊子 颜色随机","goods_thumb":"http://img.jealook.com/backend/20210118/1610952942_6226.png","refund_id":"448"},{"product_id":"22731","goods_id":"467","goods_attr":"20330|20337|20338","color_id":"20337","search_attr":"20337|20338","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"1","goods_name":"面膜工具 （碗+刷） 1组","number":"1","product_price":"3.00","rec_id":"2156","comment_id":"33","refund_status":"0","shop_status":0,"goods_attr_name":"无,（碗+刷）,1组","goods_thumb":"http://img.jealook.com/backend/20201230/1609298967_6783.png","refund_id":"449"}],"num":2},{"order_id":"1683","order_sn":"EC2021020210245178","order_status":"1","shipping_status":"2","pay_status":"2","goods_amount":"19.80","order_amount":"29.80","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":4,"shop_list":[{"product_id":"25215","goods_id":"471","goods_attr":"20686|20747|20892","color_id":"20892","search_attr":"20686|20892","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Crystal  Gray 5片","number":"1","product_price":"9.90","rec_id":"2157","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-4.25,Crystal  Gray","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3613.png","refund_id":"367"},{"product_id":"25229","goods_id":"471","goods_attr":"20686|20774|20891","color_id":"20891","search_attr":"20686|20891","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Hazel Opal 5片","number":"1","product_price":"9.90","rec_id":"2158","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-0.75,Hazel Opal","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","refund_id":"368"}],"num":2},{"order_id":"1677","order_sn":"EC2021020118252371","order_status":"1","shipping_status":"0","pay_status":"2","goods_amount":"9.90","order_amount":"19.90","bonus":"0.00","shipping_fee":"10.00","is_comment":"0","status":2,"shop_list":[{"product_id":"25243","goods_id":"471","goods_attr":"20686|20747|20891","color_id":"20891","search_attr":"20686|20891","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Hazel Opal 5片","number":"1","product_price":"9.90","rec_id":"2149","comment_id":"0","refund_status":"3","shop_status":3,"goods_attr_name":"5片,-4.25,Hazel Opal","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_3049.png","refund_id":"402"}],"num":1}]
     * count : 22
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * order_id : 1865
         * order_sn : EC2021020917370660
         * order_status : 1
         * shipping_status : 0
         * pay_status : 0
         * goods_amount : 9.90
         * order_amount : 19.90
         * bonus : 0.00
         * shipping_fee : 10.00
         * is_comment : 0
         * status : 1
         * shop_list : [{"product_id":"25273","goods_id":"471","goods_attr":"20686|20749|20893","color_id":"20893","search_attr":"20686|20893","is_promote":"0","promote_start_date":"0","promote_end_date":"0","suppliers_id":"2","goods_name":"Rohto乐敦 日抛 Larimar Blue 5片","number":"1","product_price":"9.90","rec_id":"2388","comment_id":"0","refund_status":"0","shop_status":0,"goods_attr_name":"5片,-4.75,Larimar Blue","goods_thumb":"http://img.jealook.com/backend/20210113/1610518009_5394.png","refund_id":""}]
         * num : 1
         */

        private String order_id;
        private String order_sn;
        private String order_status;
        private String shipping_status;
        private String pay_status;
        private String goods_amount;
        private String order_amount;
        private String bonus;
        private String shipping_fee;
        private String is_comment;
        private int status;
        private int num;
        private List<ShopListBean> shop_list;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ShopListBean> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBean> shop_list) {
            this.shop_list = shop_list;
        }

        public static class ShopListBean {
            /**
             * product_id : 25273
             * goods_id : 471
             * goods_attr : 20686|20749|20893
             * color_id : 20893
             * search_attr : 20686|20893
             * is_promote : 0
             * promote_start_date : 0
             * promote_end_date : 0
             * suppliers_id : 2
             * goods_name : Rohto乐敦 日抛 Larimar Blue 5片
             * number : 1
             * product_price : 9.90
             * rec_id : 2388
             * comment_id : 0
             * refund_status : 0
             * shop_status : 0
             * goods_attr_name : 5片,-4.75,Larimar Blue
             * goods_thumb : http://img.jealook.com/backend/20210113/1610518009_5394.png
             * refund_id :
             */

            private String product_id;
            private String goods_id;
            private String goods_attr;
            private String color_id;
            private String search_attr;
            private String is_promote;
            private String promote_start_date;
            private String promote_end_date;
            private String suppliers_id;
            private String goods_name;
            private String number;
            private String product_price;
            private String rec_id;
            private String comment_id;
            private String refund_status;
            private String shop_status;
            private String goods_attr_name;
            private String goods_thumb;
            private String refund_id;
            private String type;
            private String is_gift;

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(String goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getColor_id() {
                return color_id;
            }

            public void setColor_id(String color_id) {
                this.color_id = color_id;
            }

            public String getSearch_attr() {
                return search_attr;
            }

            public void setSearch_attr(String search_attr) {
                this.search_attr = search_attr;
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

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getRefund_status() {
                return refund_status;
            }

            public void setRefund_status(String refund_status) {
                this.refund_status = refund_status;
            }

            public String getShop_status() {
                return shop_status;
            }

            public void setShop_status(String shop_status) {
                this.shop_status = shop_status;
            }

            public String getGoods_attr_name() {
                return goods_attr_name;
            }

            public void setGoods_attr_name(String goods_attr_name) {
                this.goods_attr_name = goods_attr_name;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getRefund_id() {
                return refund_id;
            }

            public void setRefund_id(String refund_id) {
                this.refund_id = refund_id;
            }
        }
    }
}
