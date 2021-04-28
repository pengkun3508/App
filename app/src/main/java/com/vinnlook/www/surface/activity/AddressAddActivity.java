package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.AddressEvent;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.AddressJsonBean;
import com.vinnlook.www.surface.mvp.model.bean.AddressDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.AddressAddPresenter;
import com.vinnlook.www.surface.mvp.view.AddressAddView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.vinnlook.www.widgat.pickview.GetJsonDataUtil;
import com.vinnlook.www.widgat.pickview.JsonBean_1;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Description:新增/编辑地址
 * @Time:2020/4/1 15:16
 * @Author:pk
 */
public class AddressAddActivity extends BaseActivity<AddressAddPresenter> implements AddressAddView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    AddressListBean bean;
    @BindView(R.id.add_ok)
    RoundTextView addOk;
    @BindView(R.id.iv_check_circle)
    ImageView ivCheckCircle;
    @BindView(R.id.iv_check_circle_ly)
    RelativeLayout ivCheckCircleLy;


    private Thread thread;
    private static boolean isLoaded = false;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private ArrayList<AddressJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    String pId = "";
    String cId = "";
    String dId = "";

    String getIs_default = "0";
    private static String getAddress_id;

    List<AddressJsonBean> addressJsonBean = new ArrayList<>();


//    private AddressAdapter adapter ;


    public static void startSelf(Context context, AddressListBean bean, String getAddress_ids) {
        Intent intent = new Intent(context, AddressAddActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
        getAddress_id = getAddress_ids;


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_add;
    }

    @Override
    protected AddressAddPresenter initPresenter() {
        return new AddressAddPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
//        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        bean = (AddressListBean) getIntent().getSerializableExtra("bean");
        if (bean == null) {
            actionBar.setTitle("新增地址");
        } else {
            actionBar.setTitle("编辑地址");
        }

        actionBar.getTvRight().setVisibility(View.GONE);

        //默认选择
        ivCheckCircleLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getIs_default.equals("1")) {
                    getIs_default = "0";
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                } else {
                    getIs_default = "1";
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                }
            }
        });

        /**
         * 保存
         */
        addOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AddressAddActivity.this, "点击保存！！！", Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(etConsignee.getText())) {//收货人姓名
                    if (!TextUtils.isEmpty(etPhone.getText())) {//联系电话
                        if (!TextUtils.isEmpty(tvAddress.getText())) {//所在区域
                            if (!TextUtils.isEmpty(etAddress.getText())) {//详细地址
                                if (bean == null) {
                                    //新增地址
                                    presenter.getAddAdress(pId, cId, dId, etConsignee.getText().toString(),
                                            etPhone.getText().toString(), etAddress.getText().toString(), getIs_default);//上传数据
                                } else {
                                    //编辑地址
                                    presenter.getEditAdress(pId, cId, dId, etConsignee.getText().toString(),
                                            etPhone.getText().toString(), etAddress.getText().toString(), getIs_default, getAddress_id);//上传数据
                                }
                            } else {
                                Toast.makeText(AddressAddActivity.this, "详细地址不能为空", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(AddressAddActivity.this, "所在区域不能为空", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(AddressAddActivity.this, "联系电话不能为空", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AddressAddActivity.this, "收货人姓名不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });

        /**
         * 添加省市区
         */
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("新增收货地址", "==isLoaded==" + isLoaded);
                Log.e("新增收货地址", "==addressJsonBean==" + addressJsonBean);
//                if (isLoaded) {
//                    showPickerView();
//                } else {
//                    new GlobalMsgEvent()
//                            .setMsg("未解析完成")
//                            .post();
//                }

                if (addressJsonBean != null && !addressJsonBean.equals("")) {
                    showPickerView();
                } else {
                    presenter.getAddressJson();
                }

            }
        });

    }


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View view) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + " " + opt2tx + " " + opt3tx;


                pId = options1Items.get(options1).getId();
//                cId = options1Items.get(options1).getC_list().get(options2).getId();
                cId = options1Items.get(options1).getList().get(options2).getId();
                dId = options1Items.get(options1).getList().get(options2).getList().get(options3).getId();

                tvAddress.setText(tx);

//                Toast.makeText(context, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(getResources().getColor(R.color.gray_light))
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .isDialog(true)
                .build();

        Dialog mDialog = pvOptions.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);

                WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                lp.x = 0; // 新位置X坐标
                lp.y = -20; // 新位置Y坐标
                lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度

//                root.measure(0, 0);
//                lp.height = root.getMeasuredHeight();

                lp.height = getResources().getDisplayMetrics().heightPixels;
                lp.alpha = 9f; // 透明度
                dialogWindow.setAttributes(lp);

//                lp.height = lp.height - DeviceDataUtils.getNavigationBarHeight(activity);
//                lp.alpha = 9f; // 透明度
//                dialogWindow.setAttributes(lp);
            }
        }
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void loadData() {
        presenter.getAddressJson();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //时间请求成功
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {


    }

    //时间请求失败
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:地址详情
     * @Time:2020/4/30 13:16
     * @Author:pk
     */
    @Override
    public void getAddressDetailsSuccess(int code, AddressDetailsBean data) {

        etConsignee.setText(data.getAddress_name());
        etPhone.setText(data.getMobile());
        tvAddress.setText(data.getAddress_refer());
        etAddress.setText(data.getAddress());
        getIs_default = data.getIs_default();

        if (data.getIs_default().equals("1")) {//默认
            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
        } else {
            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
        }
        pId = data.getProvince();
        cId = data.getCity();
        dId = data.getDistrict();

    }


    @Override
    public void getAddressDetailsFail(int code, String msg) {

    }

    //编辑地址成功
    @Override
    public void getEditAddressDataSuccess(int code, List<AddressListBean> data) {

        Log.e("编辑地址成功", "========编辑地址成功=========");
        new AddressEvent(data).post();
        finish();
//        adapter.setData(data);

    }

    //编辑地址失败
    @Override
    public void getEditAddressDataFail(int code, String msg) {

    }

    //新增地址成功
    @Override
    public void getAddAddressDataSuccess(int code, List<AddressListBean> data) {
        new AddressEvent(data).post();
        finish();
    }

    @Override
    public void getAddAddressDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //请求JSON地址成功
    @Override
    public void getAddressJsonSuccess(int code, List<AddressJsonBean> data) {
        addressJsonBean = data;
        initJsonData(data);
        if (bean == null) {//新增地址
            return;
        } else {//编辑地址
            presenter.getAddressDetails(getAddress_id);//下载地址详情数据
        }

    }

    //请求JSON地址失败
    @Override
    public void getAddressJsonFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

//                        Toast.makeText(context, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
//                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(context, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(context, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    private void initJsonData(List<AddressJsonBean> data) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        Log.e("jsonBean", "==initJsonData==");
        String JsonData = new GetJsonDataUtil().getJson(this, "area.json");//获取assets目录下的json文件数据
        Log.e("jsonBean", "==JsonData==" + JsonData);
        ArrayList<JsonBean_1> jsonBeans = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = (ArrayList<AddressJsonBean>) data;
        Log.e("jsonBean", "==jsonBean==" + jsonBeans);
//        options1Items = jsonBean;
        Log.e("jsonBean", "==jsonBean===options1Items===" + options1Items);
        for (int i = 0; i < options1Items.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < options1Items.get(i).getList().size(); c++) {//遍历该省份的所有城市
                String cityName = options1Items.get(i).getList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                if (jsonBean.get(i).getC_list().get(c).getD_list() == null
//                        || jsonBean.get(i).getC_list().get(c).getD_list().size() == 0) {
//                    city_AreaList.add("");
//                } else {
//                    city_AreaList.addAll(jsonBean.get(i).getC_list().get(c).getD_list());
//                }
                Log.e("city_AreaList", "=-city_AreaList-=" + options1Items.get(i).getList().get(c).getList());
                if (options1Items.get(i).getList().get(c).getList() != null) {

                    for (int j = 0; j < options1Items.get(i).getList().get(c).getList().size(); j++) {
                        city_AreaList.add(options1Items.get(i).getList().get(c).getList().get(j).getName());
                    }


                    province_AreaList.add(city_AreaList);//添加该省所有地区数据
                }
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean_1> parseData(String result) {//Gson 解析
        ArrayList<JsonBean_1> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean_1 entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean_1.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
}
