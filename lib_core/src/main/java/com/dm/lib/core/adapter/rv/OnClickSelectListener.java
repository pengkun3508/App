package com.dm.lib.core.adapter.rv;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 18/9/19
 */
public interface OnClickSelectListener {

    /**
     * 点击事件回调
     *
     * @param selectnum 选中数量
     * @param position  所在位置
     */
    void onClick(int selectnum, int position);
    void onClick( int position);
}
