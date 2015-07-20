package fr.millezimtech.lib.multiheaderrecyclerview.Library.Decorator;

/**
 * Created by anthonyfaucogney on 16/07/2015.
 */

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public MarginDecoration(Context context, int spacing) {
        margin = spacing / 2;//= context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    public MarginDecoration(Context context) {
        margin = 20;//= context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }
    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin, margin, margin, margin);
    }
}