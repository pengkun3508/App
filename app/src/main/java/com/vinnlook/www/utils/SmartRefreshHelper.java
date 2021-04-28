package com.vinnlook.www.utils;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.common.Config;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 刷新的辅助类
 *
 * @author Cuizhen
 * @date 2018/7/6-下午5:06
 */
public class SmartRefreshHelper<E> {
    private static final int FIRST_PAGE = Config.LIST_FIRST_PAGE_INDEX;

    private final RefreshLayout refreshLayout;
    private final BaseStateAdapter<E, ? extends BaseHolder<E>> adapter;
    private RefreshCallback callback = null;

    private int currentPage = FIRST_PAGE;
    private int perPageCount = 0;
    private boolean refreshEnable = true;
    private boolean loadMoreEnable = true;

    private SmartRefreshHelper(RefreshLayout layout, BaseStateAdapter<E, ?> adapter) {
        refreshLayout = layout;
        this.adapter = adapter;

        refreshLayout.setEnableAutoLoadMore(Config.REFRESH_AUTO_LOAD_MORE_ENABLE);
        refreshLayout.setEnableOverScrollBounce(Config.REFRESH_OVER_SCROLL_BOUNCE_ENABLE);
    }

    public static <E> SmartRefreshHelper<E> with(RefreshLayout layout, BaseStateAdapter<E, ? extends BaseHolder<E>> adapter) {
        return new SmartRefreshHelper<>(layout, adapter);
    }

    public SmartRefreshHelper<E> setPerPageCount(int perPageCount) {
        this.perPageCount = perPageCount;
        return this;
    }

    public SmartRefreshHelper<E> setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
        return this;
    }

    public SmartRefreshHelper<E> setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
        return this;
    }

    public SmartRefreshHelper<E> init(RefreshCallback refreshCallback) {
        this.callback = refreshCallback;
        refreshLayout.setEnableRefresh(refreshEnable);
        if (refreshEnable) {
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refreshLayout.finishRefresh(Config.REFRESH_AND_LOAD_MORE_DELAY, false);
                    requestFirstPage(false);
                }
            });
        }
        refreshLayout.setEnableLoadMore(loadMoreEnable);
        if (loadMoreEnable) {
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    refreshLayout.finishLoadMore(Config.REFRESH_AND_LOAD_MORE_DELAY);
                    currentPage++;
                    if (callback != null) {
                        callback.doRequestData(currentPage);
                    }
                }
            });
        }
        return this;
    }

    public SmartRefreshHelper<E> requestFirstPage(boolean loading){
        if (loading) {
            adapter.loadData();
        }
        currentPage = FIRST_PAGE;
        if (callback != null) {
            callback.doRequestData(currentPage);
        }
        return this;
    }

    public void onSuccess(int code, List<E> data) {
        synchronized (adapter) {
            if (currentPage <= FIRST_PAGE) {
                adapter.setData(data);
                if (perPageCount > 0) {
                    if (data != null && data.size() < perPageCount) {
                        adapter.setNoMoreData(true);
                    } else {
                        adapter.setNoMoreData(false);
                    }
                }
            } else {
                if (data == null || data.size() == 0) {
                    currentPage--;
                    if (perPageCount > 0) {
                        adapter.setNoMoreData(true);
                    }
                } else {
                    adapter.addData(data);
                    if (perPageCount > 0) {
                        if (data.size() < perPageCount) {
                            adapter.setNoMoreData(true);
                        } else {
                            adapter.setNoMoreData(false);
                        }
                    }
                }
            }
            refreshLayout.finishRefresh(true);
            refreshLayout.finishLoadMore(true);
        }
    }

    public void onFailed() {
        synchronized (adapter) {
            if (currentPage <= FIRST_PAGE) {
                adapter.clearData();
                if (perPageCount > 0) {
                    adapter.setNoMoreData(false);
                }
            } else {
                currentPage--;
                if (perPageCount > 0) {
                    adapter.setNoMoreData(true);
                }
            }
            refreshLayout.finishRefresh(false);
            refreshLayout.finishLoadMore(false);
        }
    }

    public interface RefreshCallback {
        void doRequestData(int page);
    }
}
