package com.inthecheesefactory.lab.designlibrary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anthonyfaucogney on 16/07/2015.
 */
public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTitle, textViewContent;

    public TextViewHolder(View itemView) {
        super(itemView);
        textViewTitle = (TextView) itemView.findViewById(R.id.textLabel);
        textViewContent = (TextView) itemView.findViewById(R.id.textContent);
    }
}