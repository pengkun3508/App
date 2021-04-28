package com.vinnlook.www.surface.fragment.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.R;
import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.core.adapter.rv.state.DefaultHolder;
import com.dm.lib.core.adapter.rv.state.Type;
import com.dm.lib.core.listener.OnClickListenerWithoutLogin;
import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.event.MainHomeActivityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cuizhen
 * @date 2018/5/26-上午10:41
 */
public abstract class BaseStateAdapter6<E, H extends BaseHolder<E>> extends RecyclerView.Adapter<BaseHolder<E>> {
    private static final String TAG = BaseStateAdapter.class.getSimpleName();

    private List<E> dataList = null;
    private boolean isNoMoreData = false;
//    private TextView goShopping;
//    private TextView text_home_btn;
//    private TextView text_classify_btn;

    private OnClickListener onItemClickListener = null;
    private SparseArray<OnClickListener> onViewClickListeners = null;
    private RecyclerView recyclerView = null;

    public boolean isNoMoreData() {
        return isNoMoreData;
    }

    public void setNoMoreData(boolean isNoMoreData) {
        if (this.isNoMoreData == isNoMoreData) {
            return;
        }
        this.isNoMoreData = isNoMoreData;
        int lastIndex = getItemCount() - 1;
        if (dataList != null && !dataList.isEmpty() && lastIndex >= 1) {
            if (isNoMoreData) {
                notifyItemInserted(lastIndex);
            } else {
                notifyItemRemoved(lastIndex);
            }
        }
        requestLayout();
    }

    /**
     * 获取数据集合
     *
     * @return List<E>
     */
    public final List<E> getData() {
        return dataList;
    }

    /**
     * 设置新数据
     *
     * @param data List<E>
     */
    public final void setData(List<E> data) {
        this.dataList = data;
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        notifyDataSetChanged();
        requestLayout();
    }

    /**
     * 获取对应位置的数据
     *
     * @param position 位置
     * @return E
     */
    public final E getData(int position) {
        if (dataList == null) {
            return null;
        }
        if (position < 0) {
            return null;
        }
        if (position >= dataList.size()) {
            return null;
        }
        return dataList.get(position);
    }

    /**
     * 添加一个数据集合
     *
     * @param data List<E>
     */
    public final void addData(List<E> data) {
        if (dataList == null || dataList.size() == 0) {
            setData(data);
        } else {
            if (data != null && data.size() > 0) {
                int index = dataList.size();
                dataList.addAll(data);
                notifyItemRangeInserted(index, data.size());
                requestLayout();
            }
        }
    }

    /**
     * 添加一个数据
     *
     * @param index int
     * @param data  E
     */
    public final void addData(int index, E data) {
        if (dataList == null || dataList.size() == 0) {
            if (dataList == null) {
                dataList = new ArrayList<>();
            }
            if (data != null) {
                dataList.add(data);
            }
            notifyDataSetChanged();
        } else {
            if (data != null) {
                if (index < 0) {
                    index = 0;
                }
                int size = dataList.size();
                if (index > size) {
                    index = size;
                }
                dataList.add(index, data);
                notifyItemInserted(index);
            }
        }
        requestLayout();
    }

    /**
     * 添加一个数据
     *
     * @param data E
     */
    public final void addData(E data) {
        int index = 0;
        if (dataList != null) {
            index = dataList.size();
        }
        addData(index, data);
    }

    /**
     * 移除对应位置的数据
     *
     * @param position 位置
     */
    public final void removeData(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            notifyItemRemoved(position);
            requestLayout();
        }
    }

    /**
     * 清空数据并显示为空布局
     */
    public final void clearData() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        } else {
            dataList.clear();
        }
        notifyDataSetChanged();
        requestLayout();
    }

    /**
     * 置空数据并显示为加载布局
     */
    public final void loadData() {
        this.dataList = null;
        notifyDataSetChanged();
        requestLayout();
    }

    private void requestLayout() {
        if (recyclerView != null && recyclerView.hasFixedSize()) {
            recyclerView.requestLayout();
        }
    }

    /**
     * 对itemView添加点击事件
     * 推荐使用{@link #addOnClickListener(OnClickListener, int...)}
     *
     * @param onItemClickListener OnClickListener
     */
    @Deprecated
    public final void setOnItemClickListener(@NonNull OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 给一个view添加点击事件
     * 推荐使用{@link #addOnClickListener(OnClickListener, int...)}
     *
     * @param viewId              添加点击事件的view的id
     * @param onViewClickListener OnClickListener
     */
    @Deprecated
    public final void addOnViewClickListener(@IdRes int viewId, @NonNull OnClickListener onViewClickListener) {
        if (onViewClickListeners == null) {
            onViewClickListeners = new SparseArray<>();
        }
        onViewClickListeners.put(viewId, onViewClickListener);
    }

    /**
     * 对itemView或者多个子view添加点击事件
     * viewIds为空或者其中的viewId为一个小于等于0的值则是对itemView添加点击事件
     * 在回调中可以这样判断使用：
     * <p>
     * ----switch (view.getId()) {
     * --------default: // itemView的点击事件
     * ------------break;
     * --------case R.id.a:
     * ------------break;
     * --------case R.id.b:
     * ------------break;
     * ----}
     *
     * @param onClickListener OnClickListener
     * @param viewIds         如不添加这个参数则对itemView添加点击事件，效果同{@link #setOnItemClickListener(OnClickListener)}
     */
    public final void addOnClickListener(@NonNull OnClickListener onClickListener, @IdRes int... viewIds) {
        if (viewIds == null || viewIds.length == 0) {
            onItemClickListener = onClickListener;
            return;
        }
        if (onViewClickListeners == null) {
            onViewClickListeners = new SparseArray<>();
        }
        for (int viewId : viewIds) {
            if (viewId > 0) {
                onViewClickListeners.put(viewId, onClickListener);
            } else {
                onItemClickListener = onClickListener;
            }
        }
    }


    private SimpleCallback<Void> mOnEmptyViewClickListener = null;

    public final void setOnEmptyViewClickListener(@NonNull SimpleCallback<Void> clickListener) {
        mOnEmptyViewClickListener = clickListener;
    }

    /**
     * 你可以重写这个方法实现不同位置显示不同的布局
     *
     * @param position 位置
     * @return 返回一个类型常量标识，当然你可以去继承{@link Type}接口定义这些常量以方便管理
     */
    @IntRange(from = 0)
    protected int getHolderType(int position) {
        return Type.NORMAL;
    }

//    protected View setThisContext() {
//        return goShopping;
//    }

    /**
     * 依据holder类型的常量去创建一个ViewHolder
     *
     * @param parent     ViewGroup
     * @param holderType 类型常量标识，你需要重写这个方法返回{@link #getHolderType(int)}
     * @return H 你应该去继承{@link BaseHolder<E>}定义你的ViewHolder
     */
    protected abstract H getViewHolder(@NonNull ViewGroup parent, int holderType);

    protected View inflate(@NonNull ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    protected BaseHolder<E> getLoadingViewHolder(@NonNull ViewGroup parent) {
        int loadLayoutId = getLoadLayoutId();
        if (loadLayoutId <= 0) {
            loadLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflate(parent, loadLayoutId));
    }

    protected BaseHolder<E> getEmptyViewHolder(@NonNull ViewGroup parent) {
        int emptyLayoutId = getEmptyLayoutId();
        if (emptyLayoutId <= 0) {
            emptyLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflate(parent, emptyLayoutId));
    }

    protected BaseHolder<E> getNoMoreViewHolder(@NonNull ViewGroup parent) {
        int noMoreLayoutId = getNoMoreLayoutId();
        if (noMoreLayoutId <= 0) {
            noMoreLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflate(parent, noMoreLayoutId));
    }

    /**
     * 定义加载中的布局文件
     *
     * @return 布局文件id
     */
    @LayoutRes
    protected int getLoadLayoutId() {
        return R.layout.rv_item_state_loading;
    }


    /**
     * 定义空布局文件
     *
     * @return 空布局id
     */
    @LayoutRes
    protected int getEmptyLayoutId() {
        return R.layout.rv_item_state_empty_6;
    }


    /**
     * 定义没有更多布局文件
     *
     * @return 没有更多布局id
     */
    @LayoutRes
    protected int getNoMoreLayoutId() {
        return R.layout.rv_item_state_no_more;
    }


    protected void bindEmptyData(@NonNull BaseHolder<E> holder) {
        if (holder.itemView instanceof ViewStub) {
            return;
        }
        if (mOnEmptyViewClickListener != null) {
            LinearLayout llEmpty = holder.getView(R.id.ll_rv_item_state_empty1);
            if (llEmpty != null) {
                llEmpty.setOnClickListener(new OnClickListenerWithoutLogin() {
                    @Override
                    public void onClickWithoutLogin(View v) {
                        mOnEmptyViewClickListener.onResult(null);
                    }
                });
            }
        }
//        ImageView ivEmpty = holder.getView(R.id.iv_rv_item_state_empty1);
//        if (ivEmpty != null) {
//            int res = getDefaultEmptyIcon();
//            if (res <= 0) {
//                ivEmpty.setVisibility(View.GONE);
//            } else {
//                ivEmpty.setVisibility(View.VISIBLE);
//                ivEmpty.setImageResource(res);
//            }
//        }
//        TextView tvEmpty = holder.getView(R.id.tv_rv_item_state_empty1);
//        goShopping = holder.getView(R.id.tv_rv_item_state_empty_goshopping1);
//        text_home_btn = holder.getView(R.id.text_home_btn);//前往首页
//        text_classify_btn = holder.getView(R.id.text_classify_btn);//去搜索
//        text_home_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new MainHomeActivityEvent("1").post();
//            }
//        });
//        text_classify_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new MainHomeActivityEvent("5").post();
//            }
//        });
//        goShopping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new MainHomeActivityEvent("1").post();
//            }
//        });
//        if (tvEmpty != null) {
//            String text = "购物车是空的，\n去挑选些喜欢的商品吧！";
////            String text2 = "去购物";
//            if (TextUtils.isEmpty(text)) {
//                tvEmpty.setVisibility(View.GONE);
//            } else {
//                tvEmpty.setVisibility(View.VISIBLE);
//                tvEmpty.setText(text);
//            }
//
////            if (TextUtils.isEmpty(text2)) {
////                goShopping.setVisibility(View.GONE);
////            } else {
////                goShopping.setVisibility(View.VISIBLE);
////                goShopping.setText(text2);
////            }
//        }

    }

    private static void getShoppingBtn(View view) {

    }


    protected void bindNoMoreData(@NonNull BaseHolder<E> holder) {
        if (holder.itemView instanceof ViewStub) {
            return;
        }
        TextView tvNoMore = holder.getView(R.id.tv_rv_item_state_no_more);
        if (tvNoMore != null) {
            String text = getDefaultNoMoreText();
            if (TextUtils.isEmpty(text)) {
                tvNoMore.setVisibility(View.GONE);
            } else {
                tvNoMore.setVisibility(View.VISIBLE);
                tvNoMore.setText(text);
            }
        }
    }

    protected void bindLoadingData(@NonNull BaseHolder<E> holder) {
        if (holder.itemView instanceof ViewStub) {
            return;
        }
        TextView tvLoading = holder.getView(R.id.tv_rv_item_state_loading);
        if (tvLoading != null) {
            String text = getDefaultLoadingText();
            if (TextUtils.isEmpty(text)) {
                tvLoading.setVisibility(View.GONE);
            } else {
                tvLoading.setVisibility(View.VISIBLE);
                tvLoading.setText(text);
            }
        }
    }

    protected int getDefaultEmptyIcon() {
        return R.mipmap.shopping_icon;
    }


    protected String getDefaultEmptyText() {
        return ResUtils.getString(R.string.rv_item_state_empty);
    }

    protected String getDefaultNoMoreText() {
        return ResUtils.getString(R.string.rv_item_state_no_more);
    }

    protected String getDefaultLoadingText() {
        return "";
//        return ResUtils.getString(R.string.rv_item_state_loading);
    }

    @NonNull
    @Override
    public final BaseHolder<E> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case State.LOADING:
                return getLoadingViewHolder(parent);
            case State.EMPTY:
                return getEmptyViewHolder(parent);
            case State.NO_MORE:
                return getNoMoreViewHolder(parent);
            default:
                H holder = getViewHolder(parent, viewType);
                holder.setOnItemClickListener(onItemClickListener);
                holder.setOnViewClickListener(onViewClickListeners);
                return holder;
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseHolder<E> holder, int position) {
        switch (holder.getItemViewType()) {
            case State.LOADING:
                holder.bindData(null);
                bindLoadingData(holder);
                break;
            case State.EMPTY:
                holder.bindData(null);
                bindEmptyData(holder);
                break;
            case State.NO_MORE:
                holder.bindData(null);
                bindNoMoreData(holder);
                break;
            default:
                try {
                    holder.bindData(dataList.get(holder.getAdapterPosition()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("报错了", "====" + e);
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList == null) {
            return State.LOADING;
        } else if (dataList.size() == 0) {
            return State.EMPTY;
        } else {
            if (position >= dataList.size()) {
                return State.NO_MORE;
            } else {
                return getHolderType(position);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 1;
        } else if (dataList.size() == 0) {
            return 1;
        } else {
            if (isNoMoreData) {
                return dataList.size() + 1;
            } else {
                return dataList.size();
            }
        }
    }

    /**
     * 用于在网格布局时重设加载中、空布局和无更多布局的宽度
     * 不支持流布局的设置
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                final int spanCount = gridLayoutManager.getSpanCount();
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int count;
                        switch (getItemViewType(position)) {
                            default:
                                count = 1;
                                break;
                            case State.LOADING:
                            case State.EMPTY:
                            case State.NO_MORE:
                                count = spanCount;
                                break;
                        }
                        return count;
                    }
                });
            }
        }
    }

}
