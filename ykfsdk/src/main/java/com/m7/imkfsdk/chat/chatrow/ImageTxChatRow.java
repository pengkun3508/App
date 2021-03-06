package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.ImageViewLookActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.ImageViewHolder;
import com.m7.imkfsdk.utils.DensityUtil;
import com.moor.imkf.model.entity.FromToMessage;

/**
 * Created by longwei on 2016/3/10.
 */
public class ImageTxChatRow extends BaseChatRow {


    public ImageTxChatRow(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, FromToMessage detail, int position) {
        final ImageViewHolder holder = (ImageViewHolder) baseHolder;
        final FromToMessage message = detail;
        if (message != null) {
            /**
             * Glide 3.7版本写法
             */
//            Glide.with(context).load(message.filePath)
//                    .asBitmap()
//                    .placeholder(R.drawable.pic_thumb_bg)
//                    .error(R.drawable.image_download_fail_icon)
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            final Bitmap bitmap_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.kf_chatto_bg_normal);
//                            Bitmap newBitmap = ImageUtils.getRoundCornerImage(bitmap_bg, resource);
//                            holder.getImageView().setImageBitmap(newBitmap);
//                        }
//                    });
            /**
             * Glide 4.9版本写法
             */

//            Glide.with(context).asBitmap()
//                    .load(message.filePath)
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.pic_thumb_bg)
//                            .error(R.drawable.image_download_fail_icon)
//                            .fallback(R.drawable.image_download_fail_icon)
//                    )
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            final Bitmap bitmap_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.kf_chatto_bg_normal);
//                            Bitmap newBitmap = ImageUtils.getRoundCornerImage(bitmap_bg, resource);
//                            holder.getImageView().setImageBitmap(newBitmap);
////                            holder.getImageView().setImageBitmap(resource);
//                        }
//
//                        @Override
//                        public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                        }
//                    });


//            Glide.with(context)
//                    .asBitmap()
//                    .load(message.filePath)
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            final Bitmap bitmap_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.kf_chatto_bg_normal_1);
//
//                            Bitmap newBitmap = ImageUtils.getRoundCornerImage(bitmap_bg, resource);
//                            holder.getImageView().setImageBitmap(newBitmap);
////                            holder.getImageView().setImageBitmap(resource);
//                        }
//                    });


            Glide.with(context)
                    .asBitmap()
                    .load(message.filePath)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.kf_pic_thumb_bg)
                            .error(R.drawable.kf_image_download_fail_icon)
                    )
                    .into(holder.getImageView());


            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.getImageView().getLayoutParams();
            layoutParams.width = DensityUtil.dip2px(180);
            layoutParams.height = DensityUtil.dip2px(180);
            holder.getImageView().setLayoutParams(layoutParams);


            holder.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageViewLookActivity.class);
                    intent.putExtra("fromwho", 1);//0代表对方发送的
                    intent.putExtra("imagePath", message.filePath);
                    context.startActivity(intent);
                }
            });
            View.OnClickListener listener = ((ChatActivity) context).getChatAdapter().getOnClickListener();
            getMsgStateResId(position, holder, message, listener);
        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_image_tx, null);
            ImageViewHolder holder = new ImageViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, false));
        }

        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.IMAGE_ROW_TRANSMIT.ordinal();
    }
}
