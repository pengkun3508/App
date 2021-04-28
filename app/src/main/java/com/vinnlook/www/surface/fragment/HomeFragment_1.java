package com.vinnlook.www.surface.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.MsggingBoxActivity;
import com.vinnlook.www.surface.activity.SearchActivity;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.bean.QrCodeImgBean;
import com.vinnlook.www.surface.fragment.adapter.MyJourneyVPAdapter;
import com.vinnlook.www.surface.mvp.presenter.HomeFragment_1Presenter;
import com.vinnlook.www.surface.mvp.view.HomeFragment_1View;
import com.vinnlook.www.surface.scan.android.CaptureActivity;
import com.vinnlook.www.utils.UserUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @Description:
 * @Time:2021/3/26$
 * @Author:pk$
 */
public class HomeFragment_1 extends BaseFragment<HomeFragment_1Presenter> implements HomeFragment_1View {


    @BindView(R.id.tl_1)
    SlidingTabLayout tl1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"商城", "品牌展示", "百万补贴", "拼团Go"};

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_1;
    }

    @Override
    protected HomeFragment_1Presenter initPresenter() {
        return new HomeFragment_1Presenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        mFragments.add(new HomeTab1Fragment());//商城
        mFragments.add(new HomeTab2Fragment());//品牌展示
        mFragments.add(new HomeTab3Fragment());//百万补贴
        mFragments.add(new HomeTab4Fragment());//拼团Go
        MyJourneyVPAdapter vp = new MyJourneyVPAdapter(getChildFragmentManager(), mFragments);
        viewPager.setAdapter(vp);
        tl1.setViewPager(viewPager, mTitles);
        viewPager.setCurrentItem(0);

    }

    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//下载更新

    }


    @OnClick({R.id.iv_scan, R.id.iv_search, R.id.iv_msg})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean onClickWithoutLogin(View v) {
        switch (v.getId()) {
            case R.id.iv_scan://扫一扫
                //动态权限申请
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                break;
            case R.id.iv_search://搜索
                SearchActivity.startSelf(getContext());
                break;
            case R.id.iv_msg://消息
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    MsggingBoxActivity.startSelf(getContext());//消息盒子
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return false;

        }
        return false;
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(getActivity(), "您拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                String getDataString = data.getStringExtra("getDataString");
                //返回的BitMap图像
//                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Log.e("扫描成功", "==content==" + content);
                Log.e("扫描成功", "==getDataString==11111===" + getDataString);
//                Log.e("扫描成功", "==bitmap==" + bitmap);
                if (content != null) {
                    Uri scanUri = Uri.parse(content);
                    if (scanUri != null) {
                        if (scanUri.getQueryParameter("good_id")!=null){
                            String good_id = scanUri.getQueryParameter("good_id");//7
                            String search_attr = scanUri.getQueryParameter("search_attr");//7
                            Log.e("扫描成功", "==good_id==" + good_id);
                            Log.e("扫描成功", "==search_attr==" + search_attr);
                            MoveAbooutActivity_3.startSelf(getActivity(), good_id, search_attr);
                        }else{
                            WebActivity.startSelf(getActivity(),content);
                        }

                    }
                }
            }
        }
    }

    @Override
    public void onClickCheckLogin(View v) {

    }

    @Override
    public void getAppUpdateSuccess(int code, SignBean data) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }


    /**
     * 上传二维码
     *
     * @param code
     * @param data
     */
    @Override
    public void uploadPhotosSuccess(int code, QrCodeImgBean data) {
        Log.e("==data==", "=data==" + data);

    }

    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }
}
