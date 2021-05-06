package com.vinnlook.www.http;


import com.vinnlook.www.common.Config;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.http.model.NotCouponListBean;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.http.model.ShopCartListBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.bean.ALiPayBean;
import com.vinnlook.www.surface.bean.AddressJsonBean;
import com.vinnlook.www.surface.bean.ApplyDetailsBean;
import com.vinnlook.www.surface.bean.ApplyListBean;
import com.vinnlook.www.surface.bean.BrandDetailsBean;
import com.vinnlook.www.surface.bean.BrowseListBean;
import com.vinnlook.www.surface.bean.CertifyListBean;
import com.vinnlook.www.surface.bean.ClassifyBean;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;
import com.vinnlook.www.surface.bean.CommodityTitleBean;
import com.vinnlook.www.surface.bean.CompanyBean;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.EvaluateListBean;
import com.vinnlook.www.surface.bean.ExchangeBean;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.surface.bean.HomePublicListBean;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.surface.bean.HomeTab2Bean;
import com.vinnlook.www.surface.bean.HuoDong2Bean;
import com.vinnlook.www.surface.bean.HuoDongBean;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.surface.bean.ModifyTypeBean;
import com.vinnlook.www.surface.bean.MsggingListBean;
import com.vinnlook.www.surface.bean.MsggingTypeBean;
import com.vinnlook.www.surface.bean.NewNotCouponListBean;
import com.vinnlook.www.surface.bean.NoticeListBean;
import com.vinnlook.www.surface.bean.OrderLogisticsBean;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.surface.bean.PointsMallBean;
import com.vinnlook.www.surface.bean.PointsMallListBean;
import com.vinnlook.www.surface.bean.PreferentialBean;
import com.vinnlook.www.surface.bean.ProblemBean;
import com.vinnlook.www.surface.bean.ProblemDetailsBean;
import com.vinnlook.www.surface.bean.ProductDetailsBean;
import com.vinnlook.www.surface.bean.PublishComment;
import com.vinnlook.www.surface.bean.QrCodeImgBean;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.bean.RealNameDetailsBean;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.SavingOrderBean;
import com.vinnlook.www.surface.bean.SearchListBean;
import com.vinnlook.www.surface.bean.SearchListListBean;
import com.vinnlook.www.surface.bean.SetMealBean;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.bean.TypeGoodsBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.bean.WayBillLogisticsBean;
import com.vinnlook.www.surface.bean.WaybillListBean;
import com.vinnlook.www.surface.bean.WeCatPayBean;
import com.vinnlook.www.surface.mvp.model.bean.AddressDetailsBean;
import com.vinnlook.www.utils.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import per.goweii.rxhttp.request.Api;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 描述：网络请求接口
 *
 * @author Cuizhen
 * @date 2018/9/4
 */
public class ProjectApi extends Api {

    public static ApiService api() {
        return api(ApiService.class);
    }

    public interface ApiCode {
        int TIME_OUT = 1000;                     // 请求延迟
        int REQUEST_ERROR = 1001;                // 请求方式错误
        int ILLEGAL_PARAMETER = 1002;            // 非法参数

        int SUCCESS = 2000;                      // 获取信息成功
        int SUCCESS_OLD = 104;                   // 获取信息成功 老版本的
        int SUCCESS_NO_DATA = 2001;              // 暂无相关数据

        int FAILED = 3000;                       // 获取信息失败
        int PHONE_EXIST = 3001;                  // 该手机号已注册过
        int PASSWARD_ERROR = 3002;               // 密码错误
        int PHONE_ILLEGAL = 3003;                // 手机号不合法
        int PHONE_NOT_BIND = 3004;               // 请绑定手机号
        int PHONE_NOT_REGIST = 3005;             // 该手机号未注册
        int REGIST_NONE_LOCATE = 3006;           // 注册时没有经纬度
        int PANDA_COIN_INSUFFICIENT = 3007;      // 熊猫币不足，请充值


        int ACCOUNT_NOT_ERROR = 4000;            // 数据错误

        int ACCOUNT_NOT_EXIST = 4001;            // 账号不存在
        int ACCOUNT_EXCEPTION = 4002;            // 账号异常,请重新登录
        int ACCOUNT_FROZEN = 4003;               // 该账户已被冻结,请联系管理员
        int ACCOUNT_DELETED = 4004;              // 该账户已被管理员删除

        int ERROR = 5000;                        // 访问异常
        int ERROR_NET = 5001;                    // 网络异常

        int PWD_HAD_BEEN_UPDATE = 6000;          // 密码已修改，请重新登录
    }

    public interface ApiConfig {
        String BASE_URL = Config.HTTP_HOST + Config.HTTP_VERSION;
    }


    public interface ApiService {


        /**
         * 0001---获取系统时间
         */
        @GET("public/get-sign")
        Observable<ResponseBean<SignBean>> getSign();


        /**
         * 0002---获取首页数据
         */
        @GET("index/get-index-data")
        Observable<ResponseBean<HomeDataBean>> getHomeData();

        /**
         * 0002---获取首页数据---新
         */
        @GET("index/get-index-data")
        Observable<ResponseBean<HomeTab1Bean>> getHomeTab1Data();

        /**
         * 0002---获取首页品牌列表---新
         */
        @GET("list/get-brand-list")
        Observable<ResponseBean<HomeTab2Bean>> getBrendList();


        /**
         * 0002---获取搜索页面数据
         */
        @GET("list/get-search")
        Observable<ResponseBean<SearchListBean>> getSearchData();


        /**
         * 0004---获取商品详情数据
         */
        @GET("shop/get-shop-info")
        Observable<ResponseBean<MoveDataBean>> getMoveData(@Query("goods_id") String goods_id,
                                                           @Query("attr") String search_attr);

        /**
         * 0004---消息列表
         */
        @GET("push/get-type-list")
        Observable<ResponseBean<List<MsggingTypeBean>>> getTypeListData();

        /**
         * 0004---清除未读
         */
        @FormUrlEncoded
        @POST("push/clear-unread")
        Observable<ResponseBean<List<MsggingTypeBean>>> getClearUnread(@Field("id") String order_id);


        /**
         * 0004---二级-消息列表
         */
        @GET("push/get-push-list")
        Observable<ResponseBean<MsggingListBean>> getPushListData(@Query("page") int page,
                                                                  @Query("limit") int limit,
                                                                  @Query("id") String getType);


        /**
         * 0004---提交退款申请
         */
        @FormUrlEncoded
        @POST("order/add-refund-apply")
        Observable<ResponseBean<Object>> getAddRefundApply(@Field("order_id") String order_id,
                                                           @Field("refund_list") String refund_list,
                                                           @Field("type") String type,
                                                           @Field("status") String status,
                                                           @Field("content") String conten,
                                                           @Field("image") String img,
                                                           @Field("is_all_refund") String getIs_all_refund);

        /**
         * 0004---下载退款详情数据
         */
        @GET("order/get-refund-info")
        Observable<ResponseBean<RefundInfoBean>> getRefundInfo(@Query("order_id") String order_id,
                                                               @Query("rec_id") String sb);

        /**
         * 0004---退款列表
         */
        @GET("order/get-refund-list")
        Observable<ResponseBean<ApplyListBean>> getApplyList(@Query("page") int page,
                                                             @Query("limit") int limit);

        /**
         * 0004---查看退款详情
         */
        @GET("order/get-refund-back")
        Observable<ResponseBean<ApplyDetailsBean>> getApplyDetailsData(@Query("order_id") String order_id,
                                                                       @Query("id") String id);

        /**
         * 0004---撤销退款请求
         */
        @FormUrlEncoded
        @POST("order/cancel-refund-apply")
        Observable<ResponseBean<Object>> getApplyCancel(@Field("order_id") String order_id,
                                                        @Field("id") String id);


        /**
         * 0004---评论页面数据
         */
        @GET("shop/get-shop-name")
        Observable<ResponseBean<PublishComment>> getPublicComment(@Query("goods_id") String goods_id,
                                                                  @Query("attr") String search_attr);


        /**
         * 0004---获取限时列表数据
         */
        @GET("list/get-discounts-data")
        Observable<ResponseBean<LimitedBean>> getLimitedData(@Query("page") int page,
                                                             @Query("limit") int limit);

        /**
         * 0004---获取百万补贴列表数据
         */
        @GET("list/get-subsidy-list")
        Observable<ResponseBean<LimitedBean>> getBaiWanList(@Query("page") int page,
                                                             @Query("limit") int limit);



        /**
         * 0004---获取活动列表数据
         */
        @GET("list/active-list")
        Observable<ResponseBean<List<HuoDongBean>>> getHuoDongList();

        /**
         * 0004---获取活动列表数据--2
         */
        @GET("list/get-active-list")
        Observable<ResponseBean<List<HuoDong2Bean>>> getActivityList();


        /**
         * 0004---获取分类列表数据
         */
        @GET("type/get-screen-list")
        Observable<ResponseBean<ClassifyBean>> getClassifyData(@Query("goods_name") String goods_name,
                                                               @Query("page") int page,
                                                               @Query("limit") int limit,
                                                               @Query("type") int type,
                                                               @Query("sort_key") String sort_key,
                                                               @Query("sort_value") String sort_value,
                                                               @Query("attr") String str);


        /**
         * 0004---检索
         */
        @GET("list/get-search-mind")
        Observable<ResponseBean<List<String>>> getSearchList(@Query("keyword") String toString);


        /**
         * 0004---获取分类列表数据
         */
        @GET("type/get-type-list")
        Observable<ResponseBean<List<ClassifyTypeBean>>> getTypeList();


        /**
         * 0004---获取地址列表数据
         */
        @GET("my/get-address-list")
        Observable<ResponseBean<List<AddressListBean>>> getAddressData();


        /**
         * 0004---地址详情页面
         */
        @GET("my/get-address-info")
        Observable<ResponseBean<AddressDetailsBean>> getAddressDetails(@Query("address_id") String address_id);

        /**
         * 0004---更改，编辑地址
         *
         * @param name
         * @param phone
         * @param address
         */
        @FormUrlEncoded
        @POST("my/edit-address")
        Observable<ResponseBean<List<AddressListBean>>> getEditressindex(@Field("province") String pId,
                                                                         @Field("city") String cId,
                                                                         @Field("district") String dId,
                                                                         @Field("address_name") String name,
                                                                         @Field("mobile") String phone,
                                                                         @Field("address") String address,
                                                                         @Field("is_default") String getIs_default,
                                                                         @Field("address_id") String getAddress_id);

        /**
         * 0004---提交退款物流信息
         */
        @FormUrlEncoded
        @POST("order/up-order-waybill")
        Observable<ResponseBean<Object>> postWaybillData(@Field("order_id") String order_id,
                                                         @Field("id") String id,
                                                         @Field("waybill_company") String waybill_company,
                                                         @Field("waybill") String waybill,
                                                         @Field("waybill_image") String waybill_image);


        /**
         * 0004---更改，编辑地址
         */
        @FormUrlEncoded
        @POST("my/edit-address")
        Observable<ResponseBean<List<AddressListBean>>> getEditressindex_1(@Field("is_default") String getIs_default,
                                                                           @Field("address_id") String getAddress_id);


        /**
         * 0004---更改，编辑地址
         *
         * @param name
         * @param phone
         * @param address
         */
        @FormUrlEncoded
        @POST("my/add-address")
        Observable<ResponseBean<List<AddressListBean>>> getAddAddressindex(@Field("province") String pId,
                                                                           @Field("city") String cId,
                                                                           @Field("district") String dId,
                                                                           @Field("address_name") String name,
                                                                           @Field("mobile") String phone,
                                                                           @Field("address") String address,
                                                                           @Field("is_default") String getIs_default);

        /**
         * 0004---下载地址JSON
         */
        @GET("list/get-region")
        Observable<ResponseBean<List<AddressJsonBean>>> getAddressJson();


        /**
         * 0004---删除地址
         */
        @FormUrlEncoded
        @POST("my/del-address")
        Observable<ResponseBean<List<AddressListBean>>> getDeleteAddressindex(@Field("address_id") String address_id);


        /**
         * 0004---收藏商品
         */
        @FormUrlEncoded
        @POST("shop/collect")
        Observable<ResponseBean<Object>> getMoveCollectionShop(@Field("goods_id") String goods_id,
                                                               @Field("type") String mark);

        /**
         * 0004---意见反馈
         */
        @FormUrlEncoded
        @POST("my/feedback")
        Observable<ResponseBean<Object>> postFeedBack(@Field("content") String goods_id,
                                                      @Field("image") String imgFiles);

        /**
         * 0004---添加购物车
         */
        @FormUrlEncoded
        @POST("cart/add-cart")
        Observable<ResponseBean<Object>> getAddShopCar(@Field("goods_id") String goods_id,
                                                       @Field("product_id") String product_id,
                                                       @Field("num") String num);


        /**
         * 0004---购物车列表
         */
        @GET("cart/get-cart-list")
        Observable<ResponseBean<ShopCartListBean>> getShopListData(@Query("type") int type);

        /**
         * 0004---新购物车列表
         */
        @GET("cart/get-cart-list")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getShopListData_1();

        /**
         * 0004---新全选
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getSelectAllShopping(@Field("type") int type,
                                                                                @Field("is_all") int isAll,
                                                                                @Field("is_check") int is_check);

        /**
         * 0004---新单选
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getDanSelectShopping(@Field("rec_id") String rec_id,
                                                                                @Field("is_check") int isCheck);

        /**
         * 0004---新修改数量
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getNumberData(@Field("num") String num,
                                                                         @Field("rec_id") String rec_id);


        /**
         * 0004---购物车加减
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<ShopCartListBean>> getAddAndReduce(@Field("type") int mark,
                                                                   @Field("num") String number,
                                                                   @Field("rec_id") String getRec_id);


        /**
         * 0004---删除购物车数据
         */
        @FormUrlEncoded
        @POST("cart/del-cart")
        Observable<ResponseBean<ShopCartListBean>> getDeleteData(@Field("rec_id") String getRec_id,
                                                                 @Field("is_all") String is_all);

        /**
         * 0004---新删除购物车数据
         */
        @FormUrlEncoded
        @POST("cart/del-cart")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getDeleteData_1(@Field("rec_id") String getRec_id,
                                                                           @Field("is_all") String is_all);

        /**
         * 0004---修改商品规格
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<ModifyTypeBean>> getModifyType(@Field("type") int mark,
                                                               @Field("rec_id") String getRec_id,
                                                               @Field("num") String num,
                                                               @Field("product_id") String product_id);

        /**
         * 0004---新修改商品规格
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getModifyType_1(@Field("rec_id") String getRec_id,
                                                                           @Field("num") String num,
                                                                           @Field("product_id") String product_id);

//        ModifyTypeBean_1

        /**
         * 0004---选择类型
         */

        @GET("shop/get-product-list")
        Observable<ResponseBean<MoveDataBean>> getTypeShopData(@Query("goods_id") String goods_id);

        //移除失效
        @FormUrlEncoded
        @POST("cart/clear-invalid-goods")
        Observable<ResponseBean<List<ShopCartListBean_1>>> getRemoveData(@Field("act_id") String act_id);


        /**
         * 0004---套餐加入购物车
         */

        @FormUrlEncoded
        @POST("cart/add-cart-combo")
        Observable<ResponseBean<Object>> addShoppingCarData(@Field("act_id") String act_id,
                                                            @Field("product_ids") StringBuilder productIdSB);


        /**
         * 0004---修改全选状态
         */
        @FormUrlEncoded
        @POST("cart/set-shop-attr")
        Observable<ResponseBean<ShopCartListBean>> getSelectShopping(@Field("type") int mark,
                                                                     @Field("rec_id") String rec_id,
                                                                     @Field("is_check") int is_check,
                                                                     @Field("is_all") int is_all);


        /**
         * 首页--海淘--自营专区
         */
        @GET("index/get-type-index")
        Observable<ResponseBean<HaiTaoClassBean>> getHaiListData(@Query("type") String type);


        /**
         * 0004---确认订单
         */
        @FormUrlEncoded
        @POST("order/confrim-order")
        Observable<ResponseBean<ConfirmOrderBean>> getConfirmOrderData(@Field("rec_id") String recId,
                                                                       @Field("goods_id") String goods_id,
                                                                       @Field("product_id") String product_id,
                                                                       @Field("num") String num,
                                                                       @Field("waybill") String wayBillId,
                                                                       @Field("point") String integralNum,
                                                                       @Field("address_id") String address_id,
                                                                       @Field("id") String type_id,
                                                                       @Field("ht_sendid") String ht_sendid,
                                                                       @Field("zy_sendid") String zy_sendid,
                                                                       @Field("zeng_product") String proIdSb);


        /**
         * 0004---确认订单
         */
        @FormUrlEncoded
        @POST("order/confrim-order")
        Observable<ResponseBean<ConfirmOrderBean>> getConfirmOrderData1(@Field("rec_id") String recId,
                                                                        @Field("goods_id") String goods_id,
                                                                        @Field("product_id") String product_id,
                                                                        @Field("num") String num,
                                                                        @Field("id") String type_id,
                                                                        @Field("address_id") String address_id);

        /**
         * 0004---确认订单--更改赠品
         */
        @FormUrlEncoded
        @POST("order/confrim-order")
        Observable<ResponseBean<ConfirmOrderBean>> getConfirmTypeData(@Field("rec_id") String recId,
                                                                      @Field("goods_id") String goods_id,
                                                                      @Field("product_id") String product_id,
                                                                      @Field("num") String num,
                                                                      @Field("ht_sendid") String ht_sendid,
                                                                      @Field("zy_sendid") String zy_sendid,
                                                                      @Field("zeng_product") String proIdSb);


        /**
         * 0004---新--优惠券列表
         */
        @GET("my/discount-list")
        Observable<ResponseBean<List<NewNotCouponListBean>>> getNewCounponList(@Query("type") String type);


        /**
         * 0004---提交订单--微信支付
         */
        @FormUrlEncoded
        @POST("order/submit-order")
        Observable<ResponseBean<WeCatPayBean>> postSubmitOrder(@Field("rec_id") String recIds,
                                                               @Field("goods_id") String goods_ids,
                                                               @Field("product_id") String product_ids,
                                                               @Field("num") String nums,
                                                               @Field("real_id") String real_ids,
                                                               @Field("address_id") String address_ids,
                                                               @Field("type") String types,
                                                               @Field("zy_postscript") String confirmMessages,
                                                               @Field("ht_postscript") String confirmMessage2,
                                                               @Field("order_id") String order_ids,
                                                               @Field("id") String bonus_id,
                                                               @Field("waybill") String waybill_id,
                                                               @Field("zy_sendid") String zYSb,
                                                               @Field("ht_sendid") String hTSb);

        /**
         * 0004---提交订单--支付宝支付
         */
        @FormUrlEncoded
        @POST("order/submit-order")
        Observable<ResponseBean<ALiPayBean>> postALiSubmitOrder(@Field("rec_id") String recIds,
                                                                @Field("goods_id") String goods_ids,
                                                                @Field("product_id") String product_ids,
                                                                @Field("num") String nums,
                                                                @Field("real_id") String real_ids,
                                                                @Field("address_id") String address_ids,
                                                                @Field("type") String types,
                                                                @Field("zy_postscript") String confirmMessages,
                                                                @Field("ht_postscript") String confirmMessage2,
                                                                @Field("order_id") String order_ids,
                                                                @Field("id") String bonus_id,
                                                                @Field("waybill") String waybill_id,
                                                                @Field("zy_sendid") String zYSb,
                                                                @Field("ht_sendid") String hTSb);


        /**
         * 0004---下载优惠券未领取列表
         */
        @GET("shop/get-discount-list")
        Observable<ResponseBean<List<NotCouponListBean>>> getCouponListData(@Query("rec_id") String recIds,
                                                                            @Query("goods_id") String goods_ids,
                                                                            @Query("product_id") String product_ids,
                                                                            @Query("num") String nums);

        /**
         * 0004---下载优惠券已领取列表
         */
        @GET("my/get-my-discount-list")
        Observable<ResponseBean<List<AlreadyCouponListBean>>> getCouponListData1(@Query("rec_id") String recIds,
                                                                                 @Query("goods_id") String goods_ids,
                                                                                 @Query("product_id") String product_ids,
                                                                                 @Query("num") String nums);

        /**
         * 0004---领取优惠券
         */
        @GET("shop/get-discount")
        Observable<ResponseBean<Object>> getCollectCoupons(@Query("id") String type_id);


        /**
         * 0004---获取订单列表数据
         */
        @GET("order/get-order-list")
        Observable<ResponseBean<AllOrderListBean>> getOrderListData(@Query("page") int page,
                                                                    @Query("limit") int limit,
                                                                    @Query("type") int type);

        /**
         * 0004---删除订单
         */
        @FormUrlEncoded
        @POST("order/cancel")
        Observable<ResponseBean<AllOrderListBean>> getCelearOrderData(@Field("order_id") String order_id,
                                                                      @Field("type") String type);

        /**
         * 0004---取消订单
         */
        @FormUrlEncoded
        @POST("order/abrogate-order")
        Observable<ResponseBean<AllOrderListBean>> getCelearOrderData_1(@Field("order_id") String order_id,
                                                                        @Field("type") String type);


        /**
         * 0004---签收订单
         */
        @GET("order/confirm-receipt")
        Observable<ResponseBean<SiginOrderBean>> getSignInOrderData(@Query("order_id") String order_id);

        /**
         * 0004---订单详情修改地址
         */
        @FormUrlEncoded
        @POST("order/edit-order-address")
        Observable<ResponseBean<OrderDetailsBean>> getEditAddressOrderData(@Field("order_id") String order_id,
                                                                           @Field("address_id") String address_id);


        /**
         * 0004---获取订单详情数据
         */
        @GET("order/get-order-info")
        Observable<ResponseBean<OrderDetailsBean>> getOederDetailsData(@Query("order_id") String getOrder_id);

        /**
         * 0004---历史足迹
         */
        @GET("list/get-browse-list")
        Observable<ResponseBean<BrowseListBean>> getBrowseListData(@Query("page") int page,
                                                                   @Query("limit") int limit);

        /**
         * 0004---收藏列表
         */
        @GET("shop/get-collect-list")
        Observable<ResponseBean<CollectionListBean>> getCollectionListData(@Query("page") int page,
                                                                           @Query("limit") int limit);

        /**
         * 0004---品牌列表
         */
        @GET("list/get-brand-list")
        Observable<ResponseBean<BrandListBean>> getBrandList(@Query("type") String type);

        /**
         * 0004---品牌商品列表--新
         */
        @GET("list/get-brand-goods-list")
        Observable<ResponseBean<BrandDetailsBean>> getBrandDetailsList(@Query("page") String page,
                                                                       @Query("limit") String limit,
                                                                       @Query("brand_id") String getBrand_id);



        /**
         * 0004---根据品牌筛选商品
         */
        @GET("list/get-brand-goods-list")
        Observable<ResponseBean<CommodityListBean>> getCommodityList(@Query("page") int page,
                                                                     @Query("limit") int limit,
                                                                     @Query("brand_id") String getBrand_id,
                                                                     @Query("flood") String getFlood,
                                                                     @Query("pieces") String getPieces,
                                                                     @Query("type") String type);

        /**
         * 关于我们
         */
        @GET("list/get-about")
        Observable<ResponseBean<List<CompanyBean>>> getCompanyListData();



        /**
         * 0004--- 搜索页面，搜索商品列表
         */
        @GET("type/get-screen-list")
        Observable<ResponseBean<SearchListListBean>> getSearchListData(@Query("page") int page,
                                                                       @Query("limit") int limit,
                                                                       @Query("goods_name") String getBrand_id,
                                                                       @Query("sort_key") String sort_key,
                                                                       @Query("sort_value") String sort_value);


        /**
         * 0004--- 获取品牌商品列表头部
         */
        @GET("list/get-brand-goods-type")
        Observable<ResponseBean<List<CommodityTitleBean>>> getCommodityTitle(@Query("brand_id") String getBrand_id,
                                                                             @Query("act_id") String actId,
                                                                             @Query("type") String type);


        /**
         * 0004--- 优惠套装列表
         */
        @GET("list/get-combo-list")
        Observable<ResponseBean<List<SetMealBean>>> getMealListData(@Query("goods_id") String goods_id);


        /**
         * 0004--- 会员期间购买的订单
         */
        @GET("member/get-member-order")
        Observable<ResponseBean<SavingOrderBean>> getSavingOrdersList(@Query("page") int page,
                                                                      @Query("limit") int limit);

        /**
         * 0004--- 积分商城
         */
        @GET("point/get-point-index")
        Observable<ResponseBean<PointsMallBean>> getPointsMall();


        /**
         * 0004--- 兑换优惠券
         */
        @FormUrlEncoded
        @POST("point/convert-discount")
        Observable<ResponseBean<ExchangeBean>> getExchange(@Field("id") String id);

        /**
         * 0004--- 积分列表
         */
        @GET("point/get-order-point")
        Observable<ResponseBean<PointsMallListBean>> getPointsMallList(@Query("page") int page,
                                                                       @Query("limit") int limit);

        /**
         * 0004---获取验证码
         */
        @GET("login/get-sms")
        Observable<ResponseBean<Object>> getVerificationCode(@Query("mobile") String mobile);


        /**
         * 0004---登录
         */
        @FormUrlEncoded
        @POST("login/login")
        Observable<ResponseBean<UserInfoBean>> getCheckCode(@Field("mobile") String mobile,
                                                            @Field("code") String code);

        /**
         * 0004---验证验证码
         */
        @FormUrlEncoded
        @POST("login/check-code")
        Observable<ResponseBean<Object>> getVerifyPhoneNumber(@Field("mobile") String mobile,
                                                              @Field("code") String code);

        /**
         * 0004---修改手机号
         */
        @FormUrlEncoded
        @POST("my/edit-mobile")
        Observable<ResponseBean<UserInfoBean>> getModifyPhoneNumber(@Field("mobile") String mobile,
                                                                    @Field("code") String code);


        /**
         * 0004---微信登录
         */
        @FormUrlEncoded
        @POST("login/wechat-app-login")
        Observable<ResponseBean<UserInfoBean>> getWechatLogin(@Field("openid") String openId,
                                                              @Field("user_name") String nickName,
                                                              @Field("img_url") String headUrl);

        /**
         * 0004---获取物流信息
         */
        @GET("order/get-order-logistics")
        Observable<ResponseBean<OrderLogisticsBean>> getOrderLogistics(@Query("order_id") String order_id);

        /**
         * 0004---获取退款物流信息
         */
        @GET("order/get-refund-waybill-info")
        Observable<ResponseBean<WayBillLogisticsBean>> getWayBillLogistics(@Query("id") String order_id);


        /**
         * 0004---获取实名认证列表
         */
        @GET("my/get-real-list")
        Observable<ResponseBean<List<RealNameListBean>>> getRealNameListData();

        /**
         * 0004---编辑实名认证默认状态
         */
        @FormUrlEncoded
        @POST("my/edit-real")
        Observable<ResponseBean<List<RealNameListBean>>> getEditRealName(@Field("id") String id,
                                                                         @Field("is_default") String is_default);

        /**
         * 0004---删除实名认证
         */
        @FormUrlEncoded
        @POST("my/del-real")
        Observable<ResponseBean<List<RealNameListBean>>> getDeletRealName(@Field("id") String id);

        /**
         * 0004---下载实名认证详情
         */
        @GET("my/get-real-info")
        Observable<ResponseBean<RealNameDetailsBean>> getRealNameDetails(@Query("id") String id);

        /**
         * 排行榜title 数据
         */
        @GET("list/get-rank-list")
        Observable<ResponseBean<List<RankingTabBean>>> getRankingTabData();


        /**
         * 0004---新增实名
         */
        @FormUrlEncoded
        @POST("my/add-real")
        Observable<ResponseBean<List<RealNameListBean>>> getAddRealName(@Field("name") String name,
                                                                        @Field("id_card") String idcard,
                                                                        @Field("is_default") String is_default);

        /**
         * 0004---编辑实名
         */
        @FormUrlEncoded
        @POST("my/edit-real")
        Observable<ResponseBean<List<RealNameListBean>>> getRealNameEdit(@Field("name") String name,
                                                                         @Field("id_card") String idcard,
                                                                         @Field("is_default") String is_default,
                                                                         @Field("id") String getId);


        /**
         * 0004---获取订单数量及用户信息
         */
        @GET("my/get-my-info")
        Observable<ResponseBean<PersonalInformationBean>> getPersonalInformation();


        /**
         * 0004---获取消息列表
         */
        @GET("my/get-message-list")
        Observable<ResponseBean<NoticeListBean>> getMessageList(@Query("page") int page,
                                                                @Query("limit") int limit);

        /**
         * 0004---获取个人信息
         */
        @GET("my/get-user-info")
        Observable<ResponseBean<UserInfo>> getUserInfoData();

        /**
         * 0004---获取评论列表
         */
        @GET("shop/get-comment")
        Observable<ResponseBean<EvaluateListBean>> getEvaluateList(@Query("page") int page,
                                                                   @Query("limit") int limit,
                                                                   @Query("goods_id") String goods_id);

        /**
         * 0004---获取分类商品
         */
        @GET("list/get-type-goods")
        Observable<ResponseBean<TypeGoodsBean>> getTypeGoods(@Query("page") int page,
                                                             @Query("limit") int limit,
                                                             @Query("attr_id") String id);

        /**
         * 0004---物流公司列表
         */
        @GET("list/get-waybill-list")
        Observable<ResponseBean<List<WaybillListBean>>> getLogisticsList();


        /**
         * 0004---获取热榜列表数据
         */
        @GET("list/ranking-list")
        Observable<ResponseBean<List<ReBangListBean>>> getTypeReBangList(@Query("type") String type);


        /**
         * 0004---绑定微信
         */
        @GET("my/bind-wechat")
        Observable<ResponseBean<Object>> getBindingWechat(@Query("type") String type,
                                                          @Query("wx_app_openid") String openId,
                                                          @Query("wx_nickname") String nickName,
                                                          @Query("image") String headUrl);


        /**
         * 0004---广告商品列表
         */
        @GET("list/get-goods-list")
        Observable<ResponseBean<HomeGoodsListBean>> getGoodsList(@Query("page") int page,
                                                                 @Query("limit") int limit,
                                                                 @Query("id") String id);


        /**
         * 0004---获取首页分类共有数据
         */
        @GET("list/get-munu-goods-list")
        Observable<ResponseBean<HomePublicListBean>> getDataList(@Query("page") int page,
                                                                 @Query("limit") int limit,
                                                                 @Query("suppliersId") String suppliersId,
                                                                 @Query("isColor") String isColor,
                                                                 @Query("tossPeriod") String tossPeriod,
                                                                 @Query("goods_name") String goods_name,
                                                                 @Query("sort_key") String sort_key,
                                                                 @Query("sort_value") String sort_value,
                                                                 @Query("attr_id") String attrId,
                                                                 @Query("id") String getId);

        /**
         * 0004---获取首页分类共有数据
         */
        @GET("list/get-certify-list")
        Observable<ResponseBean<List<CertifyListBean>>> getCertifyList();




        /**
         * 0004---一键登录
         */
        @FormUrlEncoded
        @POST("login/get-mobile-login")
        Observable<ResponseBean<UserInfoBean>> getMobileLogin(@Field("access_token") String access_token);


        /**
         * 0004---活动详情页面
         */
        @GET("list/get-active-info")
        Observable<ResponseBean<ProductDetailsBean>> getProductDetailsData(@Query("active_id") String id);

        /**
         * 0004---问一问列表
         */
        @GET("question/get-question-list")
        Observable<ResponseBean<ProblemBean>> getProblemList(@Query("goods_id") String goods_id,
                                                             @Query("page") int page,
                                                             @Query("limit") int limit);

        /**
         * 0004---提问题
         */
        @FormUrlEncoded
        @POST("question/ask-question")
        Observable<ResponseBean<Object>> getQuestionData(@Field("goods_id") String goods_id,
                                                         @Field("search_attr") String search_attr,
                                                         @Field("question") String question);


        /**
         * 0004---回答问题列表
         */
        @GET("question/get-answer-list")
        Observable<ResponseBean<ProblemDetailsBean>> getProblemDetailsList(@Query("goods_id") String goods_id,
                                                                           @Query("id") String id,
                                                                           @Query("page") int page,
                                                                           @Query("limit") int limit);

        /**
         * 0004---回答问题
         */
        @FormUrlEncoded
        @POST("question/answer")
        Observable<ResponseBean<Object>> getAnswerData(@Field("question_id") String question_id,
                                                       @Field("content") String content);

        /**
         * 0004---优惠商品列表
         */
        @GET("list/get-goods-active-info")
        Observable<ResponseBean<PreferentialBean>> getPreferentialList(@Query("id") String id);


        /**
         * 0004---回答问题
         */
        @FormUrlEncoded
        @POST("question/edit-praise")
        Observable<ResponseBean<Object>> getEditPraise(@Field("id") String iD,
                                                       @Field("type") String type);


        /**
         * 1004--发表评价-上传图片
         */
        @Multipart
        @POST("public/img")
        Observable<ResponseBean<UpdateImgBean>> postUploadPhotos(@Part MultipartBody.Part img);

        /**
         * 1004--发表评价-上传二维码
         */
        @Multipart
        @POST("public/read-code")
        Observable<ResponseBean<QrCodeImgBean>> postUploadQrcode(@Part MultipartBody.Part img);


        /**
         * 1004--发表评价
         */
        @GET("order/set-appraise")
        Observable<ResponseBean<Object>> multigraph(@Query("image") String imgFiles,
                                                    @Query("order_id") String order_id,
                                                    @Query("rec_id") String rec_id,
                                                    @Query("content") String trim,
                                                    @Query("total_score") String total_score,
                                                    @Query("describe_score") String describe_score,
                                                    @Query("logistics_score") String logistics_score,
                                                    @Query("server_score") String server_score,
                                                    @Query("is_show") String is_show);

        /**
         * 1004--修改个人信息
         */
        @FormUrlEncoded
        @POST("my/set-user-info")
        Observable<ResponseBean<UserInfo>> postPersonalInformation(@Field("img_url") String imgUrl,
                                                                   @Field("user_name") String nicheng,
                                                                   @Field("sex") String sex);


        /**
         * 1004--会员详情
         */
        @GET("member/get-member-config")
        Observable<ResponseBean<MemberBean>> getMemberDetailData();

        /**
         * 1004--购买会员--微信
         */
        @FormUrlEncoded
        @POST("member/buy-member")
        Observable<ResponseBean<WeCatPayBean>> postPayMember(@Field("type") String type,
                                                             @Field("id") String memberId);

        /**
         * 1004--购买会员--支付宝
         */
        @FormUrlEncoded
        @POST("member/buy-member")
        Observable<ResponseBean<ALiPayBean>> postPayMember_2(@Field("type") String type,
                                                             @Field("id") String memberId);


    }
}