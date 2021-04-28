package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.IFrameViewHolder;
import com.m7.imkfsdk.view.ChatListView;
import com.moor.imkf.model.entity.FromToMessage;

import java.util.Set;

/**
 * Created by longwei on 2016/3/10.
 */
public class IframeRxChatRow extends BaseChatRow {

    public IframeRxChatRow(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, FromToMessage detail, int position) {
        IFrameViewHolder holder = (IFrameViewHolder) baseHolder;
        final FromToMessage message = detail;
        final ChatListView listview = ((ChatActivity) context).getChatListView();
        if (message != null) {

            if (message.withDrawStatus) {
                holder.getWithdrawTextView().setVisibility(View.VISIBLE);
                holder.getContainer().setVisibility(View.GONE);
            } else {
                holder.getWithdrawTextView().setVisibility(View.GONE);
                holder.getContainer().setVisibility(View.VISIBLE);

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                    holder.getWebView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
//                holder.getWebView().getSettings().setJavaScriptEnabled(true);

                holder.getWebView().getSettings().setDomStorageEnabled(true);
                holder.getWebView().getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
                String appCachePath = context.getApplicationContext().getCacheDir().getAbsolutePath();
                holder.getWebView().getSettings().setAppCachePath(appCachePath);
//                holder.getWebView().getSettings().setAllowFileAccess(true);
                holder.getWebView().getSettings().setAppCacheEnabled(true);
                holder.getWebView().getSettings().setSavePassword(false);

                holder.getWebView().setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        Log.e("点击消息===", "==ci====webView=========" + url);
                        return true;
                    }

//                    @Override
//                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                        handler.proceed();
//                    }
                });


                holder.getWebView().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {


                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            if (listview != null) {
                                listview.requestDisallowInterceptTouchEvent(false);
                            }
                        } else {
                            if (listview != null) {
                                listview.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                        return false;

                    }
                });

                holder.getWebView().getLayoutParams().width = message.iframeWidth;
                holder.getWebView().getLayoutParams().height = message.iframeHeight;
                holder.getWebView().loadUrl(message.message);





//                Uri uri = Uri.parse(ci.url);
//                Set<String> args = uri.getQueryParameterNames();
//                String good_id = uri.getQueryParameter("good_id");
//                String search_attr = uri.getQueryParameter("search_attr");
//
//                Log.e("点击消息===","==ci====args========="+args);
//                Log.e("点击消息===","==ci====good_id========="+good_id);
//                Log.e("点击消息===","==ci====search_attr========="+search_attr);
//
//                Intent intent1 = new Intent();
//                intent1.setAction( "cc.android.myaction.leo");
//                intent1.addCategory(Intent.CATEGORY_DEFAULT);
//                intent1.putExtra("good_id",good_id);
//                intent1.putExtra("search_attr",search_attr);
//                context.startActivity(intent1);


            }
        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_iframe_rx, null);
            IFrameViewHolder holder = new IFrameViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, true));
        }
        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.IFRAME_ROW_RECEIVED.ordinal();
    }
}
