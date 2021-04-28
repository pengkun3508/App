package com.vinnlook.www.surface.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;

public class ImageHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ImageView imageView1;
    public TextView price;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.image);
        this.imageView1 = (ImageView) view.findViewById(R.id.image1);
        this.price = (TextView) view.findViewById(R.id.price);
    }
}