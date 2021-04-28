package com.vinnlook.www.surface.mvp.view;

import com.dm.lib.core.mvp.MvpView;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.VersionBean;

import java.util.List;

public interface AddressView extends MvpView {

    void getAppUpdateSuccess(int code, VersionBean version);

    void getAppUpdateFail(int code, String msg);

    void getAddressDataSuccess(int code, List<AddressListBean> data);

    void getAddressDataFail(int code, String msg);


    void getEditAddressDataSuccess(int code, List<AddressListBean> data);

    void getEditAddressDataFail(int code, String msg);

    void getDeleteAddressDataSuccess(int code, List<AddressListBean> data);

    void getDeleteAddressDataFail(int code, String msg);
}
