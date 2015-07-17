package com.inthecheesefactory.lab.designlibrary;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by anthonyfaucogney on 16/07/2015.
 */
public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTitle, textViewContent;
    public ImageView extraComment;

    public TextViewHolder(View itemView) {
        super(itemView);
        textViewTitle = (TextView) itemView.findViewById(R.id.tv_attributTitle);
        textViewContent = (TextView) itemView.findViewById(R.id.tv_attributeValue);
        extraComment = (ImageView) itemView.findViewById(R.id.iv_extraComment);

    }

    public void render(final MultiHeaderNumberedAdapter.AttributObject item, final Activity activity) {
        textViewTitle.setText(item.getName());
        textViewContent.setText(item.getValue());
        textViewTitle.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Toast.makeText(
                                textViewTitle.getContext(), item.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }

        );
        if (item.getExtraComment() != null) {
            extraComment.setVisibility(View.VISIBLE);
            extraComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(activity)
                            .title("Commentaire pour : " + item.getName())
                            .content(item.getExtraComment())
                            .positiveText("J'ai lu")
                            .show();
                }
            });
        }
    }
}