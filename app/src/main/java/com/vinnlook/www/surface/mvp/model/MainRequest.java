package com.vinnlook.www.surface.mvp.model;

import android.util.Log;

import com.vinnlook.www.http.BaseRequest;
import com.vinnlook.www.http.ProjectApi;
import com.vinnlook.www.http.RequestBackListener;
import com.vinnlook.www.http.ResponseBean;
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

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/4
 */
public class MainRequest extends BaseRequest {

    /**
     * 获取系统时间
     */
    public static Disposable getTime(final RequestBackListener<SignBean> listener) {
        return request(ProjectApi.api().getSign(), listener);
    }

//    /**
//     * 下载更新版本
//     *
//     * @param listener
//     * @return
//     */
//    public static Disposable getAppUpdate(final RequestBackListener<SignBean> listener) {
//        return requestWithSign(new RequestCallback<SignBean>() {
//            @Override
//            public Observable<ResponseBean<SignBean>> request(boolean isAppForceUpdate) {
//                return ProjectApi.api().getSign();
//            }
//        }, listener);
//    }

    /**
     * 下载更新版本
     *
     * @param listener
     * @return
     */
    public static Disposable getAppUpdate(final RequestBackListener<SignBean> listener) {
        return request(ProjectApi.api().getSign(), listener);
    }

    /**
     * 获取首页数据
     * 这是没有参数的请求
     *
     * @param listener
     * @return
     */
    public static Disposable getHomeindex(final RequestBackListener<HomeDataBean> listener) {
        return requestWithSign(new RequestCallback<HomeDataBean>() {
            @Override
            public Observable<ResponseBean<HomeDataBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getHomeData();
            }
        }, listener);
    }


    /**
     * 获取首页商城--新
     * 这是没有参数的请求
     *
     * @param listener
     * @return
     */
    public static Disposable getHomeTab1Data(final RequestBackListener<HomeTab1Bean> listener) {
        return requestWithSign(new RequestCallback<HomeTab1Bean>() {
            @Override
            public Observable<ResponseBean<HomeTab1Bean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getHomeTab1Data();
            }
        }, listener);
    }


    /**
     * 获取首页品牌列表--新
     * 这是没有参数的请求
     *
     * @param listener
     * @return
     */
    public static Disposable getBrendList(final RequestBackListener<HomeTab2Bean> listener) {
        return requestWithSign(new RequestCallback<HomeTab2Bean>() {
            @Override
            public Observable<ResponseBean<HomeTab2Bean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBrendList();
            }
        }, listener);
    }





    /**
     * 获取搜索列表数据
     *
     * @param listener
     * @return
     */
    public static Disposable getSearchData(final RequestBackListener<SearchListBean> listener) {
        return requestWithSign(new RequestCallback<SearchListBean>() {
            @Override
            public Observable<ResponseBean<SearchListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSearchData();
            }
        }, listener);
    }


    /**
     * 获取商品详情数据
     *
     * @param listener
     * @return
     */
    public static Disposable getMoveindex(String goods_id, String search_attr, final RequestBackListener<MoveDataBean> listener) {
        return requestWithSign(new RequestCallback<MoveDataBean>() {
            @Override
            public Observable<ResponseBean<MoveDataBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getMoveData(goods_id, search_attr);
            }
        }, listener);
    }

    /**
     * 消息列表
     *
     * @param listener
     * @return
     */
    public static Disposable getTypeListData(final RequestBackListener<List<MsggingTypeBean>> listener) {
        return requestWithSign(new RequestCallback<List<MsggingTypeBean>>() {
            @Override
            public Observable<ResponseBean<List<MsggingTypeBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getTypeListData();
            }
        }, listener);
    }

    /**
     * 消息列表
     *
     * @param listener
     * @return
     */
    public static Disposable getClearUnread(final RequestBackListener<List<MsggingTypeBean>> listener) {
        return requestWithSign(new RequestCallback<List<MsggingTypeBean>>() {
            @Override
            public Observable<ResponseBean<List<MsggingTypeBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getClearUnread("");
            }
        }, listener);
    }


    /**
     * 二级消息列表
     *
     * @param listener
     * @return
     */
    public static Disposable getPushListData(int page, int limit, String getType, final RequestBackListener<MsggingListBean> listener) {
        return requestWithSign(new RequestCallback<MsggingListBean>() {
            @Override
            public Observable<ResponseBean<MsggingListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPushListData(page, limit, getType);
            }
        }, listener);
    }


    /**
     * 提交退款申请
     *
     * @param listener
     * @return
     */
    public static Disposable getAddRefundApply(String order_id, String refund_list, String type, String status,
                                               String conten, String img, String getIs_all_refund, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddRefundApply(order_id, refund_list, type, status, conten, img, getIs_all_refund);
            }
        }, listener);
    }

    /**
     * 下载退款详情数据
     *
     * @param listener
     * @return
     */
    public static Disposable getRefundInfo(String order_id, String sb, final RequestBackListener<RefundInfoBean> listener) {
        return requestWithSign(new RequestCallback<RefundInfoBean>() {
            @Override
            public Observable<ResponseBean<RefundInfoBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRefundInfo(order_id, sb);
            }
        }, listener);
    }

    /**
     * 退款列表
     *
     * @param listener
     * @return
     */
    public static Disposable getApplyList(int page, int limit, final RequestBackListener<ApplyListBean> listener) {
        return requestWithSign(new RequestCallback<ApplyListBean>() {
            @Override
            public Observable<ResponseBean<ApplyListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getApplyList(page, limit);
            }
        }, listener);
    }


    /**
     * 查看退款详情
     *
     * @param listener
     * @return
     */
    public static Disposable getApplyDetailsData(String order_id, String id, final RequestBackListener<ApplyDetailsBean> listener) {
        return requestWithSign(new RequestCallback<ApplyDetailsBean>() {
            @Override
            public Observable<ResponseBean<ApplyDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getApplyDetailsData(order_id, id);
            }
        }, listener);
    }

    /**
     * 撤销退款请求
     *
     * @param listener
     * @return
     */
    public static Disposable getApplyCancel(String order_id, String id, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getApplyCancel(order_id, id);
            }
        }, listener);
    }

    /**
     * 获取限时列表数据
     *
     * @param listener
     * @return
     */
    public static Disposable getLimitedindex(int page, int limite, final RequestBackListener<LimitedBean> listener) {
        return requestWithSign(new RequestCallback<LimitedBean>() {
            @Override
            public Observable<ResponseBean<LimitedBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getLimitedData(page, limite);
            }
        }, listener);
    }

    /**
     * 获取百万补贴列表数据
     *
     * @param listener
     * @return
     */
    public static Disposable getBaiWanList(int page, int limite, final RequestBackListener<LimitedBean> listener) {
        return requestWithSign(new RequestCallback<LimitedBean>() {
            @Override
            public Observable<ResponseBean<LimitedBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBaiWanList(page, limite);
            }
        }, listener);
    }



    /**
     * 获取活动列表
     *
     * @param listener
     * @return
     */
    public static Disposable getHuoDongList(final RequestBackListener<List<HuoDongBean>> listener) {
        return requestWithSign(new RequestCallback<List<HuoDongBean>>() {
            @Override
            public Observable<ResponseBean<List<HuoDongBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getHuoDongList();
            }
        }, listener);
    }

    /**
     * 获取活动列表--2
     *
     * @param listener
     * @return
     */
    public static Disposable getActivityList(final RequestBackListener<List<HuoDong2Bean>> listener) {
        return requestWithSign(new RequestCallback<List<HuoDong2Bean>>() {
            @Override
            public Observable<ResponseBean<List<HuoDong2Bean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getActivityList();
            }
        }, listener);
    }


    /**
     * 获取分类筛选的数据
     *
     * @param listener
     * @return
     */
    public static Disposable getClassifyindex(String goods_name, int page, int limit, int type, String sort_key, String sort_value, String str, final RequestBackListener<ClassifyBean> listener) {
        return requestWithSign(new RequestCallback<ClassifyBean>() {
            @Override
            public Observable<ResponseBean<ClassifyBean>> request(boolean isAppForceUpdate) {
                Log.e("getClassifyindex", "==获取分类筛选的数据==" + str);
                return ProjectApi.api().getClassifyData(goods_name, page, limit, type, sort_key, sort_value, str);
            }
        }, listener);
    }

    /**
     * 检索
     *
     * @param listener
     * @return
     */
    public static Disposable getSearchList(String toString, final RequestBackListener<List<String>> listener) {
        return requestWithSign(new RequestCallback<List<String>>() {
            @Override
            public Observable<ResponseBean<List<String>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSearchList(toString);
            }
        }, listener);
    }

    /**
     * 获取筛选项
     *
     * @param listener
     * @return
     */
    public static Disposable getTypeList(final RequestBackListener<List<ClassifyTypeBean>> listener) {
        return requestWithSign(new RequestCallback<List<ClassifyTypeBean>>() {
            @Override
            public Observable<ResponseBean<List<ClassifyTypeBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getTypeList();
            }
        }, listener);
    }

    /**
     * 获取地址列表数据
     *
     * @param listener
     * @return
     */
    public static Disposable getAddressindex(final RequestBackListener<List<AddressListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressListBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddressData();
            }
        }, listener);
    }

    /**
     * 获取详细地址数据
     *
     * @param listener
     * @return
     */
    public static Disposable getAddressDetails(String getAddress_id, final RequestBackListener<AddressDetailsBean> listener) {
        return requestWithSign(new RequestCallback<AddressDetailsBean>() {
            @Override
            public Observable<ResponseBean<AddressDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddressDetails(getAddress_id);
            }
        }, listener);
    }

    /**
     * 提交退款物流信息
     *
     * @param listener
     * @return
     */
    public static Disposable postWaybillData(String order_id, String id, String waybill_company, String waybill, String waybill_image, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().postWaybillData(order_id, id, waybill_company, waybill, waybill_image);
            }
        }, listener);
    }


    /**
     * 编辑地址
     *
     * @return
     */
    public static Disposable getEditressindex(String pId, String cId, String dId, String name, String phone, String address, String getIs_default,
                                              String getAddress_id, final RequestBackListener<List<AddressListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressListBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEditressindex(pId, cId, dId, name, phone, address, getIs_default, getAddress_id);
            }
        }, listener);
    }

    /**
     * 编辑地址
     *
     * @return
     */
    public static Disposable getEditressindex_1(String getIs_default, String getAddress_id, final RequestBackListener<List<AddressListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressListBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEditressindex_1(getIs_default, getAddress_id);
            }
        }, listener);
    }

    /**
     * 新增地址
     *
     * @return
     */
    public static Disposable getAddAddressindex(String pId, String cId, String dId, String name, String phone, String address, String getIs_default,
                                                final RequestBackListener<List<AddressListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressListBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddAddressindex(pId, cId, dId, name, phone, address, getIs_default);
            }
        }, listener);
    }

    /**
     * 新增地址
     *
     * @return
     */
    public static Disposable getAddressJson(final RequestBackListener<List<AddressJsonBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressJsonBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressJsonBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddressJson();
            }
        }, listener);
    }



    /**
     * 删除地址
     *
     * @return
     */
    public static Disposable getDeleteAddressindex(String address_id,
                                                   final RequestBackListener<List<AddressListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AddressListBean>>() {
            @Override
            public Observable<ResponseBean<List<AddressListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDeleteAddressindex(address_id);
            }
        }, listener);
    }


    /**
     * 意见反馈
     *
     * @return
     */
    public static Disposable postFeedBack(String str, String imgUrl, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().postFeedBack(str, imgUrl);
            }
        }, listener);
    }


    /**
     * 收藏商品
     *
     * @return
     */
    public static Disposable getMoveCollectionShop(String goods_id, String mark,
                                                   final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getMoveCollectionShop(goods_id, mark);
            }
        }, listener);
    }


    /**
     * 添加购物车
     *
     * @return
     */
    public static Disposable getAddShopCar(String goods_id, String product_id, String num,
                                           final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddShopCar(goods_id, product_id, num);
            }
        }, listener);
    }

    /**
     * 购物车列表
     *
     * @return
     */
    public static Disposable getShopListData(int type, final RequestBackListener<ShopCartListBean> listener) {
        return requestWithSign(new RequestCallback<ShopCartListBean>() {
            @Override
            public Observable<ResponseBean<ShopCartListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getShopListData(type);
            }
        }, listener);
    }

    /**
     * 新购物车列表
     *
     * @return
     */
    public static Disposable getShopListData_1(final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getShopListData_1();
            }
        }, listener);
    }

    /**
     * 新全选
     *
     * @return
     */
    public static Disposable getSelectAllShopping(int type, int isAll, int is_check, final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSelectAllShopping(type, isAll, is_check);
            }
        }, listener);
    }

    /**
     * 新单选
     *
     * @return
     */
    public static Disposable getDanSelectShopping(String rec_id, int isCheck, final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDanSelectShopping(rec_id, isCheck);
            }
        }, listener);
    }

    /**
     * 新修改数量
     *
     * @return
     */
    public static Disposable getNumberData(String num, String rec_id, final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getNumberData(num, rec_id);
            }
        }, listener);
    }


    /**
     * 购物车加减数量
     *
     * @return
     */
    public static Disposable getAddAndReduce(int mark, String number, String getRec_id,
                                             final RequestBackListener<ShopCartListBean> listener) {
        return requestWithSign(new RequestCallback<ShopCartListBean>() {
            @Override
            public Observable<ResponseBean<ShopCartListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddAndReduce(mark, number, getRec_id);
            }
        }, listener);
    }

    /**
     * 删除购物车数据
     *
     * @return
     */
    public static Disposable getDeleteData(String getRec_id, String is_all,
                                           final RequestBackListener<ShopCartListBean> listener) {
        return requestWithSign(new RequestCallback<ShopCartListBean>() {
            @Override
            public Observable<ResponseBean<ShopCartListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDeleteData(getRec_id, is_all);
            }
        }, listener);
    }


    /**
     * 新删除购物车数据
     *
     * @return
     */
    public static Disposable getDeleteData_1(String getRec_id, String is_all,
                                             final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDeleteData_1(getRec_id, is_all);
            }
        }, listener);
    }

    /**
     * 修改商品规格
     *
     * @return
     */
    public static Disposable getModifyType(int mark, String getRec_id, String num, String product_id,
                                           final RequestBackListener<ModifyTypeBean> listener) {
        return requestWithSign(new RequestCallback<ModifyTypeBean>() {
            @Override
            public Observable<ResponseBean<ModifyTypeBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getModifyType(mark, getRec_id, num, product_id);
            }
        }, listener);
    }

    /**
     * 新--修改商品规格
     *
     * @return
     */
    public static Disposable getModifyType_1(String getRec_id, String num, String product_id,
                                             final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getModifyType_1(getRec_id, num, product_id);
            }
        }, listener);
    }


    /**
     * 选择商品类型
     *
     * @return
     */
    public static Disposable getTypeShopData(String goods_id, final RequestBackListener<MoveDataBean> listener) {
        return requestWithSign(new RequestCallback<MoveDataBean>() {
            @Override
            public Observable<ResponseBean<MoveDataBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getTypeShopData(goods_id);
            }
        }, listener);
    }


    /**
     * 移除失效商品
     *
     * @return
     */
    public static Disposable getRemoveData(final RequestBackListener<List<ShopCartListBean_1>> listener) {
        return requestWithSign(new RequestCallback<List<ShopCartListBean_1>>() {
            @Override
            public Observable<ResponseBean<List<ShopCartListBean_1>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRemoveData("");
            }
        }, listener);
    }


    /**
     * 套餐加入购物车
     *
     * @return
     */
    public static Disposable addShoppingCarData(String act_id, StringBuilder productIdSB, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().addShoppingCarData(act_id, productIdSB);
            }
        }, listener);
    }


    /**
     * 全选改变状态
     *
     * @return
     */
    public static Disposable getSelectShopping(int mark, String rec_id, int is_check, int is_all,
                                               final RequestBackListener<ShopCartListBean> listener) {
        return requestWithSign(new RequestCallback<ShopCartListBean>() {
            @Override
            public Observable<ResponseBean<ShopCartListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSelectShopping(mark, rec_id, is_check, is_all);
            }
        }, listener);
    }


    /**
     * 确认订单
     *
     * @return
     */
    public static Disposable getConfirmOrderData(String recId, String goods_id, String product_id, String num, String wayBillId, String integralNum, String address_id,
                                                 String id, String ht_sendid, String zy_sendid, String proIdSb, final RequestBackListener<ConfirmOrderBean> listener) {
        return requestWithSign(new RequestCallback<ConfirmOrderBean>() {
            @Override
            public Observable<ResponseBean<ConfirmOrderBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, integralNum, address_id, id, ht_sendid, zy_sendid, proIdSb);
            }
        }, listener);
    }


    /**
     * 首页--海淘--自营专区
     *
     * @return
     */
    public static Disposable getHaiListData(String type, final RequestBackListener<HaiTaoClassBean> listener) {
        return requestWithSign(new RequestCallback<HaiTaoClassBean>() {
            @Override
            public Observable<ResponseBean<HaiTaoClassBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getHaiListData(type);
            }
        }, listener);
    }


    /**
     * 使用优惠券后
     *
     * @return
     */
    public static Disposable getConfirmOrderData1(String recId, String goods_id, String product_id, String num, String type_id,
                                                  String address_id, final RequestBackListener<ConfirmOrderBean> listener) {
        return requestWithSign(new RequestCallback<ConfirmOrderBean>() {
            @Override
            public Observable<ResponseBean<ConfirmOrderBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getConfirmOrderData1(recId, goods_id, product_id, num, type_id, address_id);
            }
        }, listener);
    }


    /**
     * 新-优惠券列表接口
     *
     * @return
     */
    public static Disposable getNewCounponList(String type, final RequestBackListener<List<NewNotCouponListBean>> listener) {
        return requestWithSign(new RequestCallback<List<NewNotCouponListBean>>() {
            @Override
            public Observable<ResponseBean<List<NewNotCouponListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getNewCounponList(type);
            }
        }, listener);
    }


    /**
     * 确认订单--更改赠品
     *
     * @return
     */
    public static Disposable getConfirmTypeData(String recId, String goods_id, String product_id, String num, String ht_sendid, String zy_sendid, String proIdSb, final RequestBackListener<ConfirmOrderBean> listener) {
        return requestWithSign(new RequestCallback<ConfirmOrderBean>() {
            @Override
            public Observable<ResponseBean<ConfirmOrderBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getConfirmTypeData(recId, goods_id, product_id, num, ht_sendid, zy_sendid, proIdSb);
            }
        }, listener);
    }


    /**
     * 提交订单--微信支付
     *
     * @return
     */
    public static Disposable postSubmitOrder(String recIds, String goods_ids, String product_ids, String nums, String real_ids, String address_ids, String types, String confirmMessages,
                                             String confirmMessage2, String order_ids, String bonus_id, String waybill_id, String zYSb, String hTSb,
                                             final RequestBackListener<WeCatPayBean> listener) {
        return requestWithSign(new RequestCallback<WeCatPayBean>() {
            @Override
            public Observable<ResponseBean<WeCatPayBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().postSubmitOrder(recIds, goods_ids, product_ids, nums, real_ids, address_ids, types, confirmMessages, confirmMessage2, order_ids, bonus_id, waybill_id, zYSb, hTSb);
            }
        }, listener);
    }

    /**
     * 提交订单--支付宝支付
     *
     * @return
     */
    public static Disposable postALiSubmitOrder(String recIds, String goods_ids, String product_ids, String nums, String real_ids, String address_ids, String types,
                                                String confirmMessages, String confirmMessage2, String order_ids, String bonus_id, String waybill_id, String zYSb, String hTSb,
                                                final RequestBackListener<ALiPayBean> listener) {
        return requestWithSign(new RequestCallback<ALiPayBean>() {
            @Override
            public Observable<ResponseBean<ALiPayBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().postALiSubmitOrder(recIds, goods_ids, product_ids, nums, real_ids, address_ids, types, confirmMessages, confirmMessage2, order_ids, bonus_id, waybill_id, zYSb, hTSb);
            }
        }, listener);
    }


    /**
     * 优惠券未领取列表
     *
     * @return
     */
    public static Disposable getCouponListData(String recIds, String goods_ids, String product_ids, String nums, final RequestBackListener<List<NotCouponListBean>> listener) {
        return requestWithSign(new RequestCallback<List<NotCouponListBean>>() {
            @Override
            public Observable<ResponseBean<List<NotCouponListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCouponListData(recIds, goods_ids, product_ids, nums);
            }
        }, listener);
    }

    /**
     * 优惠券已领取列表
     *
     * @return
     */
    public static Disposable getCouponListData1(String recIds, String goods_ids, String product_ids, String nums, final RequestBackListener<List<AlreadyCouponListBean>> listener) {
        return requestWithSign(new RequestCallback<List<AlreadyCouponListBean>>() {
            @Override
            public Observable<ResponseBean<List<AlreadyCouponListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCouponListData1(recIds, goods_ids, product_ids, nums);
            }
        }, listener);
    }

    /**
     * 领取优惠券
     *
     * @return
     */
    public static Disposable getCollectCoupons(String type_id, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCollectCoupons(type_id);
            }
        }, listener);
    }


    /**
     * 订单列表
     *
     * @return
     */
    public static Disposable getOrderListData(int page, int limit, int type, final RequestBackListener<AllOrderListBean> listener) {
        return requestWithSign(new RequestCallback<AllOrderListBean>() {
            @Override
            public Observable<ResponseBean<AllOrderListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getOrderListData(page, limit, type);
            }
        }, listener);
    }

    /**
     * 删除订单
     *
     * @return
     */
    public static Disposable getCelearOrderData(String order_id, String type, final RequestBackListener<AllOrderListBean> listener) {
        return requestWithSign(new RequestCallback<AllOrderListBean>() {
            @Override
            public Observable<ResponseBean<AllOrderListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCelearOrderData(order_id, type);
            }
        }, listener);
    }

    /**
     * 取消订单
     *
     * @return
     */
    public static Disposable getCelearOrderData_1(String order_id, String type, final RequestBackListener<AllOrderListBean> listener) {
        return requestWithSign(new RequestCallback<AllOrderListBean>() {
            @Override
            public Observable<ResponseBean<AllOrderListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCelearOrderData_1(order_id, type);
            }
        }, listener);
    }


    /**
     * 签收订单
     *
     * @return
     */
    public static Disposable getSignInOrderData(String order_id, final RequestBackListener<SiginOrderBean> listener) {
        return requestWithSign(new RequestCallback<SiginOrderBean>() {
            @Override
            public Observable<ResponseBean<SiginOrderBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSignInOrderData(order_id);
            }
        }, listener);
    }

    /**
     * 订单详情--修改地址
     *
     * @return
     */
    public static Disposable getEditAddressOrderData(String order_id, String address_id, final RequestBackListener<OrderDetailsBean> listener) {
        return requestWithSign(new RequestCallback<OrderDetailsBean>() {
            @Override
            public Observable<ResponseBean<OrderDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEditAddressOrderData(order_id, address_id);
            }
        }, listener);
    }


    /**
     * 订单详情
     *
     * @return
     */
    public static Disposable getOederDetailsData(String getOrder_id, final RequestBackListener<OrderDetailsBean> listener) {
        return requestWithSign(new RequestCallback<OrderDetailsBean>() {
            @Override
            public Observable<ResponseBean<OrderDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getOederDetailsData(getOrder_id);
            }
        }, listener);
    }

    /**
     * 评论页面数据
     *
     * @param listener
     * @return
     */
    public static Disposable getPublicComment(String goods_id, String search_attr, final RequestBackListener<PublishComment> listener) {
        return requestWithSign(new RequestCallback<PublishComment>() {
            @Override
            public Observable<ResponseBean<PublishComment>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPublicComment(goods_id, search_attr);
            }
        }, listener);
    }

    /**
     * 历史足迹
     *
     * @return
     */
    public static Disposable getBrowseListData(int page, int limit, final RequestBackListener<BrowseListBean> listener) {
        return requestWithSign(new RequestCallback<BrowseListBean>() {
            @Override
            public Observable<ResponseBean<BrowseListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBrowseListData(page, limit);
            }
        }, listener);
    }

    /**
     * 收藏列表
     *
     * @return
     */
    public static Disposable getCollectionListData(int page, int limit, final RequestBackListener<CollectionListBean> listener) {
        return requestWithSign(new RequestCallback<CollectionListBean>() {
            @Override
            public Observable<ResponseBean<CollectionListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCollectionListData(page, limit);
            }
        }, listener);
    }

    /**
     * 品牌列表
     *
     * @return
     */
    public static Disposable getBrandList(String type, final RequestBackListener<BrandListBean> listener) {
        return requestWithSign(new RequestCallback<BrandListBean>() {
            @Override
            public Observable<ResponseBean<BrandListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBrandList(type);
            }
        }, listener);
    }

    /**
     * 品牌商品列表--新
     *
     * @return
     */
    public static Disposable getBrandDetailsList(String page,String limit,String getBrand_id, final RequestBackListener<BrandDetailsBean> listener) {
        return requestWithSign(new RequestCallback<BrandDetailsBean>() {
            @Override
            public Observable<ResponseBean<BrandDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBrandDetailsList(page,limit,getBrand_id);
            }
        }, listener);
    }





    /**
     * 品牌列表--商品列表
     *
     * @return
     */
    public static Disposable getCommodityList(int page, int limit, String getBrand_id, String getFlood, String getPieces, String type, final RequestBackListener<CommodityListBean> listener) {
        return requestWithSign(new RequestCallback<CommodityListBean>() {
            @Override
            public Observable<ResponseBean<CommodityListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCommodityList(page, limit, getBrand_id, getFlood, getPieces, type);
            }
        }, listener);
    }

    /**
     * 获取品牌商品列表头部
     *
     * @return
     */
    public static Disposable getCommodityTitle(String getBrand_id, String actId, String type, final RequestBackListener<List<CommodityTitleBean>> listener) {
        return requestWithSign(new RequestCallback<List<CommodityTitleBean>>() {
            @Override
            public Observable<ResponseBean<List<CommodityTitleBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCommodityTitle(getBrand_id, actId, type);
            }
        }, listener);
    }


    /**
     * 搜索页面--搜索商品列表
     *
     * @return
     */
    public static Disposable getSearchListData(int page, int limit, String keyword, String sort_key, String sort_value, final RequestBackListener<SearchListListBean> listener) {
        return requestWithSign(new RequestCallback<SearchListListBean>() {
            @Override
            public Observable<ResponseBean<SearchListListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSearchListData(page, limit, keyword, sort_key, sort_value);
            }
        }, listener);
    }

    /**
     * 优惠套装列表
     *
     * @return
     */
    public static Disposable getMealListData(String goods_id, final RequestBackListener<List<SetMealBean>> listener) {
        return requestWithSign(new RequestCallback<List<SetMealBean>>() {
            @Override
            public Observable<ResponseBean<List<SetMealBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getMealListData(goods_id);
            }
        }, listener);
    }


    /**
     * 会员期间购买的订单
     *
     * @return
     */
    public static Disposable getSavingOrdersList(int page, int limit, final RequestBackListener<SavingOrderBean> listener) {
        return requestWithSign(new RequestCallback<SavingOrderBean>() {
            @Override
            public Observable<ResponseBean<SavingOrderBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getSavingOrdersList(page, limit);
            }
        }, listener);
    }


    /**
     * 积分商城
     *
     * @return
     */
    public static Disposable getPointsMall(final RequestBackListener<PointsMallBean> listener) {
        return requestWithSign(new RequestCallback<PointsMallBean>() {
            @Override
            public Observable<ResponseBean<PointsMallBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPointsMall();
            }
        }, listener);
    }

    /**
     * 兑换优惠券
     *
     * @return
     */
    public static Disposable getExchange(String id, final RequestBackListener<ExchangeBean> listener) {
        return requestWithSign(new RequestCallback<ExchangeBean>() {
            @Override
            public Observable<ResponseBean<ExchangeBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getExchange(id);
            }
        }, listener);
    }


    /**
     * 积分列表
     *
     * @return
     */
    public static Disposable getPointsMallList(int page, int limit, final RequestBackListener<PointsMallListBean> listener) {
        return requestWithSign(new RequestCallback<PointsMallListBean>() {
            @Override
            public Observable<ResponseBean<PointsMallListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPointsMallList(page, limit);
            }
        }, listener);
    }


    /**
     * 获取验证码
     *
     * @return
     */
    public static Disposable getVerificationCode(String mobile, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getVerificationCode(mobile);
            }
        }, listener);
    }


    /**
     * 获取验证码
     *
     * @return
     */
    public static Disposable getCheckCode(String mobile, String code, final RequestBackListener<UserInfoBean> listener) {
        return requestWithSign(new RequestCallback<UserInfoBean>() {
            @Override
            public Observable<ResponseBean<UserInfoBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCheckCode(mobile, code);
            }
        }, listener);
    }


    /**
     * 验证验证码
     *
     * @return
     */
    public static Disposable getVerifyPhoneNumber(String mobile, String code, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getVerifyPhoneNumber(mobile, code);
            }
        }, listener);
    }

    /**
     * 修改手机号
     *
     * @return
     */
    public static Disposable getModifyPhoneNumber(String mobile, String code, final RequestBackListener<UserInfoBean> listener) {
        return requestWithSign(new RequestCallback<UserInfoBean>() {
            @Override
            public Observable<ResponseBean<UserInfoBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getModifyPhoneNumber(mobile, code);
            }
        }, listener);
    }


    /**
     * 微信登录
     *
     * @return
     */
    public static Disposable getWechatLogin(String openId, String nickName, String headUrl, final RequestBackListener<UserInfoBean> listener) {
        return requestWithSign(new RequestCallback<UserInfoBean>() {
            @Override
            public Observable<ResponseBean<UserInfoBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getWechatLogin(openId, nickName, headUrl);
            }
        }, listener);
    }

    /**
     * 获取物流信息
     *
     * @return
     */
    public static Disposable getOrderLogistics(String order_id, final RequestBackListener<OrderLogisticsBean> listener) {
        return requestWithSign(new RequestCallback<OrderLogisticsBean>() {
            @Override
            public Observable<ResponseBean<OrderLogisticsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getOrderLogistics(order_id);
            }
        }, listener);
    }

    /**
     * 获取退款物流信息
     *
     * @return
     */
    public static Disposable getWayBillLogistics(String wayId, final RequestBackListener<WayBillLogisticsBean> listener) {
        return requestWithSign(new RequestCallback<WayBillLogisticsBean>() {
            @Override
            public Observable<ResponseBean<WayBillLogisticsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getWayBillLogistics(wayId);
            }
        }, listener);
    }


    /**
     * 获取实名认证列表
     *
     * @return
     */
    public static Disposable getRealNameListData(final RequestBackListener<List<RealNameListBean>> listener) {
        return requestWithSign(new RequestCallback<List<RealNameListBean>>() {
            @Override
            public Observable<ResponseBean<List<RealNameListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRealNameListData();
            }
        }, listener);
    }


    /**
     * 编辑实名认证为默认
     *
     * @return
     */
    public static Disposable getEditRealName(String id, String is_default, final RequestBackListener<List<RealNameListBean>> listener) {
        return requestWithSign(new RequestCallback<List<RealNameListBean>>() {
            @Override
            public Observable<ResponseBean<List<RealNameListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEditRealName(id, is_default);
            }
        }, listener);
    }

    /**
     * 删除实名认证
     *
     * @return
     */
    public static Disposable getDeletRealName(String id, final RequestBackListener<List<RealNameListBean>> listener) {
        return requestWithSign(new RequestCallback<List<RealNameListBean>>() {
            @Override
            public Observable<ResponseBean<List<RealNameListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDeletRealName(id);
            }
        }, listener);
    }

    /**
     * 下载实名认证为详情
     *
     * @return
     */
    public static Disposable getRealNameDetails(String id, final RequestBackListener<RealNameDetailsBean> listener) {
        return requestWithSign(new RequestCallback<RealNameDetailsBean>() {
            @Override
            public Observable<ResponseBean<RealNameDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRealNameDetails(id);
            }
        }, listener);
    }

    /**
     * 排行榜title 数据
     *
     * @return
     */
    public static Disposable getRankingTabData(final RequestBackListener<List<RankingTabBean>> listener) {
        return requestWithSign(new RequestCallback<List<RankingTabBean>>() {
            @Override
            public Observable<ResponseBean<List<RankingTabBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRankingTabData();
            }
        }, listener);
    }


    /**
     * 新增实名
     *
     * @return
     */
    public static Disposable getAddRealName(String name, String idcard, String is_default, final RequestBackListener<List<RealNameListBean>> listener) {
        return requestWithSign(new RequestCallback<List<RealNameListBean>>() {
            @Override
            public Observable<ResponseBean<List<RealNameListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAddRealName(name, idcard, is_default);
            }
        }, listener);
    }

    /**
     * 修改实名
     *
     * @return
     */
    public static Disposable getRealNameEdit(String name, String idcard, String getIs_default, String getId, final RequestBackListener<List<RealNameListBean>> listener) {
        return requestWithSign(new RequestCallback<List<RealNameListBean>>() {
            @Override
            public Observable<ResponseBean<List<RealNameListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getRealNameEdit(name, idcard, getIs_default, getId);
            }
        }, listener);
    }

    /**
     * 获取个人信息以及订单数量
     *
     * @return
     */
    public static Disposable getPersonalInformation(final RequestBackListener<PersonalInformationBean> listener) {
        return requestWithSign(new RequestCallback<PersonalInformationBean>() {
            @Override
            public Observable<ResponseBean<PersonalInformationBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPersonalInformation();
            }
        }, listener);
    }

    /**
     * 获取消息列表
     *
     * @return
     */
    public static Disposable getMessageList(int page, int limit, final RequestBackListener<NoticeListBean> listener) {
        return requestWithSign(new RequestCallback<NoticeListBean>() {
            @Override
            public Observable<ResponseBean<NoticeListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getMessageList(page, limit);
            }
        }, listener);
    }


    /**
     * 获取个人信息
     *
     * @return
     */
    public static Disposable getUserInfoData(final RequestBackListener<UserInfo> listener) {
        return requestWithSign(new RequestCallback<UserInfo>() {
            @Override
            public Observable<ResponseBean<UserInfo>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getUserInfoData();
            }
        }, listener);
    }


    /**
     * 获取个人信息
     *
     * @return
     */
    public static Disposable getEvaluateList(int page, int limit, String goods_id, final RequestBackListener<EvaluateListBean> listener) {
        return requestWithSign(new RequestCallback<EvaluateListBean>() {
            @Override
            public Observable<ResponseBean<EvaluateListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEvaluateList(page, limit, goods_id);
            }
        }, listener);
    }

    /**
     * @return
     */
    public static Disposable getTypeGoods(int page, int limit, String iD, final RequestBackListener<TypeGoodsBean> listener) {
        return requestWithSign(new RequestCallback<TypeGoodsBean>() {
            @Override
            public Observable<ResponseBean<TypeGoodsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getTypeGoods(page, limit, iD);
            }
        }, listener);
    }


    /**
     * 物流公司列表
     *
     * @return
     */
    public static Disposable getLogisticsList(final RequestBackListener<List<WaybillListBean>> listener) {
        return requestWithSign(new RequestCallback<List<WaybillListBean>>() {
            @Override
            public Observable<ResponseBean<List<WaybillListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getLogisticsList();
            }
        }, listener);
    }


    /**
     * 获取热榜列表数据
     *
     * @return
     */
    public static Disposable getTypeReBangList(String type, final RequestBackListener<List<ReBangListBean>> listener) {
        return requestWithSign(new RequestCallback<List<ReBangListBean>>() {
            @Override
            public Observable<ResponseBean<List<ReBangListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getTypeReBangList(type);
            }
        }, listener);
    }


    /**
     * 获取热榜列表数据
     *
     * @return
     */
    public static Disposable getBindingWechat(String type, String openId, String nickName, String headUrl, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getBindingWechat(type, openId, nickName, headUrl);
            }
        }, listener);
    }


    /**
     * 获取首页分类共有数据
     *
     * @return
     */
    public static Disposable getDataList(int page, int limit, String suppliersId, String isColor, String tossPeriod, String goods_name, String sort_key, String sort_value, String attrId, String getId, final RequestBackListener<HomePublicListBean> listener) {
        return requestWithSign(new RequestCallback<HomePublicListBean>() {
            @Override
            public Observable<ResponseBean<HomePublicListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getDataList(page, limit, suppliersId, isColor, tossPeriod, goods_name, sort_key, sort_value, attrId, getId);
            }
        }, listener);
    }

    /**
     * 获取资质数据
     *
     * @return
     */
    public static Disposable getCertifyList(final RequestBackListener<List<CertifyListBean>> listener) {
        return requestWithSign(new RequestCallback<List<CertifyListBean>>() {
            @Override
            public Observable<ResponseBean<List<CertifyListBean>>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getCertifyList();
            }
        }, listener);
    }



    /**
     * 广告商品列表
     *
     * @return
     */
    public static Disposable getGoodsList(int page, int limit, String iD, final RequestBackListener<HomeGoodsListBean> listener) {
        return requestWithSign(new RequestCallback<HomeGoodsListBean>() {
            @Override
            public Observable<ResponseBean<HomeGoodsListBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getGoodsList(page, limit, iD);
            }
        }, listener);
    }


    /**
     * 获取个人信息
     *
     * @return
     */
    public static Disposable getMobileLogin(String token, final RequestBackListener<UserInfoBean> listener) {
        return requestWithSign(new RequestCallback<UserInfoBean>() {
            @Override
            public Observable<ResponseBean<UserInfoBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getMobileLogin(token);
            }
        }, listener);
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    public static Disposable getProductDetailsData(String id, final RequestBackListener<ProductDetailsBean> listener) {
        return requestWithSign(new RequestCallback<ProductDetailsBean>() {
            @Override
            public Observable<ResponseBean<ProductDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getProductDetailsData(id);
            }
        }, listener);
    }

    /**
     * 获取问一问列表数据
     *
     * @return
     */
    public static Disposable getProblemList(String goods_id, int page, int limit, final RequestBackListener<ProblemBean> listener) {
        return requestWithSign(new RequestCallback<ProblemBean>() {
            @Override
            public Observable<ResponseBean<ProblemBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getProblemList(goods_id, page, limit);
            }
        }, listener);
    }

    /**
     * 提问题
     *
     * @return
     */
    public static Disposable getQuestionData(String goods_id, String search_attr, String question, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getQuestionData(goods_id, search_attr, question);
            }
        }, listener);
    }

    /**
     * 问题详情--回答问题列表
     *
     * @return
     */
    public static Disposable getProblemDetailsList(String goods_id, String id, int page, int limit, final RequestBackListener<ProblemDetailsBean> listener) {
        return requestWithSign(new RequestCallback<ProblemDetailsBean>() {
            @Override
            public Observable<ResponseBean<ProblemDetailsBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getProblemDetailsList(goods_id, id, page, limit);
            }
        }, listener);
    }

    /**
     * 问题详情--回答问题
     *
     * @return
     */
    public static Disposable getAnswerData(String question_id, String content, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getAnswerData(question_id, content);
            }
        }, listener);
    }

    /**
     * 优惠商品列表
     *
     * @return
     */
    public static Disposable getPreferentialList(String id, final RequestBackListener<PreferentialBean> listener) {
        return requestWithSign(new RequestCallback<PreferentialBean>() {
            @Override
            public Observable<ResponseBean<PreferentialBean>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getPreferentialList(id);
            }
        }, listener);
    }


    /**
     * 点赞/取消点赞
     *
     * @return
     */
    public static Disposable getEditPraise(String iD, String type, final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().getEditPraise(iD, type);
            }
        }, listener);
    }


    /**
     * 1004--发表评价-上传图片
     */
    public static Disposable postUploadPhotos(final File imgFiles, final RequestBackListener<UpdateImgBean> listener) {
        return requestWithSign(new RequestCallback<UpdateImgBean>() {
            @Override
            public Observable<ResponseBean<UpdateImgBean>> request(boolean isAppForceUpdate) {
                MultipartBody.Part asda = filesToMultipartBodyParts(imgFiles, "img");
                Log.e("postUploadPhotos", "==asda==" + asda);
                return ProjectApi.api().postUploadPhotos(asda);
            }
        }, listener);

    }

    /**
     * 1004--发表评价-上传二维码
     */
    public static Disposable postUploadQrcode(final File imgFiles, final RequestBackListener<QrCodeImgBean> listener) {
        return requestWithSign(new RequestCallback<QrCodeImgBean>() {
            @Override
            public Observable<ResponseBean<QrCodeImgBean>> request(boolean isAppForceUpdate) {
                MultipartBody.Part asda = filesToMultipartBodyParts(imgFiles, "img");
                Log.e("postUploadQrcode", "==asda==" + asda);
                return ProjectApi.api().postUploadQrcode(asda);
            }
        }, listener);

    }


    /**
     * 1004--发表评论
     */
    public static Disposable multigraph(final String imgUrl, String order_id, String rec_id, String trim, String total_score, String describe_score, String logistics_score, String server_score, String is_show
            , final RequestBackListener<Object> listener) {
        return requestWithSign(new RequestCallback<Object>() {
            @Override
            public Observable<ResponseBean<Object>> request(boolean isAppForceUpdate) {

                return ProjectApi.api().multigraph(imgUrl, order_id, rec_id, trim, total_score, describe_score, logistics_score, server_score, is_show);
            }
        }, listener);
    }


    /**
     * 1004--会员详情
     */
    public static Disposable getMemberDetailData(final RequestBackListener<MemberBean> listener) {
        return requestWithSign(new RequestCallback<MemberBean>() {
            @Override
            public Observable<ResponseBean<MemberBean>> request(boolean isAppForceUpdate) {

                return ProjectApi.api().getMemberDetailData();
            }
        }, listener);
    }

    /**
     * 1004--购买会员--微信
     */
    public static Disposable postPayMember(String type, String memberId, final RequestBackListener<WeCatPayBean> listener) {
        return requestWithSign(new RequestCallback<WeCatPayBean>() {
            @Override
            public Observable<ResponseBean<WeCatPayBean>> request(boolean isAppForceUpdate) {

                return ProjectApi.api().postPayMember(type, memberId);
            }
        }, listener);
    }

    /**
     * 1004--购买会员--支付宝
     */
    public static Disposable postPayMember_2(String type, String memberId, final RequestBackListener<ALiPayBean> listener) {
        return requestWithSign(new RequestCallback<ALiPayBean>() {
            @Override
            public Observable<ResponseBean<ALiPayBean>> request(boolean isAppForceUpdate) {

                return ProjectApi.api().postPayMember_2(type, memberId);
            }
        }, listener);
    }


    /**
     * 1004--修改个人信息
     */
    public static Disposable postPersonalInformation(String imgUrl, String nicheng, String sex, final RequestBackListener<UserInfo> listener) {
        return requestWithSign(new RequestCallback<UserInfo>() {
            @Override
            public Observable<ResponseBean<UserInfo>> request(boolean isAppForceUpdate) {
                return ProjectApi.api().postPersonalInformation(imgUrl, nicheng, sex);


            }
        }, listener);

    }


    private static MultipartBody.Part filesToMultipartBodyParts(File files, String key) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), files);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, files.getName(), requestBody);

//        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
//        for (File file : files) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
//            parts.add(part);
//        }
        return part;
    }


}
