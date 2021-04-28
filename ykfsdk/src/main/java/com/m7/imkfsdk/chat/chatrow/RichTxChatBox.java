package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.RichViewHolder;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.model.parser.HttpParser;

import java.util.Set;

/**
 * Created by pangw on 2018/6/26.
 */

public class RichTxChatBox extends BaseChatRow {
    public RichTxChatBox(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, final FromToMessage detail, int position) {
        RichViewHolder holder = (RichViewHolder) baseHolder;
        if(detail != null) {
                final CardInfo ci = HttpParser.getCardInfo(detail.cardInfo,0);
                holder.getWithdrawTextView().setVisibility(View.GONE);
                holder.getContainer().setVisibility(View.VISIBLE);

                holder.getTitle().setText(ci.title);
                holder.getContent().setText(ci.concent);
                holder.getName().setText(ci.name);

                if(ci.icon.equals("")){
                    holder.getImageView().setVisibility(View.GONE);
                }else{
                    holder.getImageView().setVisibility(View.VISIBLE);
                }

//                Glide.with(context).load(ci.icon)
//                        .centerCrop()
////                        .crossFade()
//                        .placeholder(R.drawable.kf_pic_thumb_bg)
//                        .error(R.drawable.kf_image_download_fail_icon)
//                        .into(holder.getImageView());
            Glide.with(context)
                    .load(ci.icon)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.kf_pic_thumb_bg)
                            .error(R.drawable.kf_image_download_fail_icon)
                    )
                    .into(holder.getImageView());


                holder.getKf_chat_rich_lin().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Log.e("点击消息===","==ci====url========="+ci.url);
//                            Intent intent = new Intent();
//                            intent.setAction("android.intent.action.VIEW");
//                            Uri content_url = Uri.parse(ci.url);
//                            intent.setData(content_url);
//                            context.startActivity(intent);

                            Uri uri = Uri.parse(ci.url);
                            Set<String> args = uri.getQueryParameterNames();
                            String good_id = uri.getQueryParameter("good_id");
                            String search_attr = uri.getQueryParameter("search_attr");

                            Log.e("点击消息===","==ci====args========="+args);
                            Log.e("点击消息===","==ci====good_id========="+good_id);
                            Log.e("点击消息===","==ci====search_attr========="+search_attr);

                            Intent intent1 = new Intent();
                            intent1.setAction( "cc.android.myaction.leo");
                            intent1.addCategory(Intent.CATEGORY_DEFAULT);
                            intent1.putExtra("good_id",good_id);
                            intent1.putExtra("search_attr",search_attr);
                            context.startActivity(intent1);

                        }catch (Exception e){

                        }
                    }
                });
            View.OnClickListener listener = ((ChatActivity) context).getChatAdapter().getOnClickListener();
            getMsgStateResId(position, holder, detail, listener);
        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_rich_tx, null);
            RichViewHolder holder = new RichViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, true));
        }
        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.RICHTEXT_ROW_TRANSMIT.ordinal();
    }
}
