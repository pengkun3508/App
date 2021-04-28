package com.dm.lib.core.adapter.rv.simple;

import android.content.Context;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dm.lib.core.listener.OnClickListenerWithoutLogin;
import com.dm.lib.utils.DisplayInfoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：RecyclerView.Adapter的封装
 *
 * @author Cuizhen
 * @date 2018/10/22
 */
public abstract class BaseSimpleAdapter<E> extends RecyclerView.Adapter<BaseSimpleAdapter.BaseHolder> {

    private List<E> dataList = null;
    private OnViewClickListener onItemClickListener = null;
    private SparseArray<OnViewClickListener> onViewClickListeners = null;
    private RecyclerView recyclerView = null;

    /**
     * 获取数据集合
     *
     * @return List<E>
     */
    public final List<E> getData() {
        return dataList;
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
     * 移除对应范围的数据
     *
     * @param position 起始位置
     * @param count    个数
     */
    public final void removeData(int position, int count) {
        if (position >= 0 && position + count <= dataList.size()) {
            for (int i = 0; i < count; i++) {
                dataList.remove(position);
            }
            notifyItemRangeRemoved(position, count);
            requestLayout();
        }
    }

    /**
     * 清空数据
     */
    public final void clearData() {
        if (dataList == null) {
            dataList = new ArrayList<>();
            notifyDataSetChanged();
        } else if (dataList.size() == 0) {
            notifyDataSetChanged();
        } else {
            dataList.clear();
            notifyItemRangeRemoved(0, dataList.size());
        }
        requestLayout();
    }

    private void requestLayout() {
        if (recyclerView != null && recyclerView.hasFixedSize()) {
            recyclerView.requestLayout();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     * 对itemView添加点击事件
     * 推荐使用{@link #addOnClickListener(OnViewClickListener, int...)}
     *
     * @param onItemClickListener OnClickListener
     */
    public final void setOnItemClickListener(@NonNull OnViewClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 给一个view添加点击事件
     * 推荐使用{@link #addOnClickListener(OnViewClickListener, int...)}
     *
     * @param viewId              添加点击事件的view的id
     * @param onViewClickListener OnClickListener
     */
    public final void addOnViewClickListener(@IdRes int viewId, @NonNull OnViewClickListener onViewClickListener) {
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
     * @param viewIds         如不添加这个参数则对itemView添加点击事件
     */
    public final void addOnClickListener(@NonNull OnViewClickListener onClickListener, @IdRes int... viewIds) {
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

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        BaseHolder holder = getHolder(itemView);
        holder.setOnItemClickListener(onItemClickListener);
        holder.setOnViewClickListener(onViewClickListeners);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        bindData(holder, dataList.get(holder.getAdapterPosition()));
    }

    @LayoutRes
    protected abstract int getLayoutId(int viewType);

    protected BaseHolder getHolder(View itemView) {
        return new BaseHolder(itemView);
    }

    protected abstract void bindData(@NonNull BaseHolder holder, @NonNull E data);

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public static class BaseHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views = null;
        private final View itemView;

        public BaseHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public final <V extends View> V getView(@IdRes int viewId) {
            if (views == null) {
                views = new SparseArray<>();
            }
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                if (view == null) {
                    views.put(viewId, itemView);
                } else {
                    views.put(viewId, view);
                }
            }
            return (V) view;
        }

        public Context getContext(){
            return itemView.getContext();
        }

        public View getItemView(){
            return itemView;
        }

        final void setOnItemClickListener(final OnViewClickListener onItemClickListener) {
            if (onItemClickListener != null) {
                itemView.setOnClickListener(new OnClickListenerWithoutLogin() {
                    @Override
                    public void onClickWithoutLogin(View v) {
                        onItemClickListener.onClick(BaseHolder.this, itemView, getAdapterPosition());
                    }
                });
            }
        }

        final void setOnViewClickListener(SparseArray<OnViewClickListener> onViewClickListeners) {
            if (onViewClickListeners != null) {
                for (int i = 0; i < onViewClickListeners.size(); i++) {
                    int viewId = onViewClickListeners.keyAt(i);
                    final OnViewClickListener onClickListener = onViewClickListeners.valueAt(i);
                    final View view = getView(viewId);
                    if (view != null) {
                        view.setOnClickListener(new OnClickListenerWithoutLogin() {
                            @Override
                            public void onClickWithoutLogin(View v) {
                                onClickListener.onClick(BaseHolder.this, v, getAdapterPosition());
                            }
                        });
                    }
                }
            }
        }
    }

    public interface OnViewClickListener {
        void onClick(BaseHolder holder, View view, int position);
    }
}
