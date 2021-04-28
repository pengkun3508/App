package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.AddressJsonBean;
import com.vinnlook.www.surface.mvp.model.bean.AddressDetailsBean;

import java.util.List;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019-12-24
 */
public interface AddressAddView extends MvpView {

    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getAddressDetailsSuccess(int code, AddressDetailsBean data);

    void getAddressDetailsFail(int code, String msg);


    void getEditAddressDataSuccess(int code, List<AddressListBean> data);

    void getEditAddressDataFail(int code, String msg);


    void getAddAddressDataSuccess(int code, List<AddressListBean> data);

    void getAddAddressDataFail(int code, String msg);

    void getAddressJsonSuccess(int code, List<AddressJsonBean> data);

    void getAddressJsonFail(int code, String msg);
}
