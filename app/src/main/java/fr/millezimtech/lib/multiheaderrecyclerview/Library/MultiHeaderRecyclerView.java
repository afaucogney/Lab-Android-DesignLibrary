package fr.millezimtech.lib.multiheaderrecyclerview.Library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import fr.millezimtech.lib.multiheaderrecyclerview.Library.Adapter.MultiHeaderNumberedAdapter;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Decorator.MarginDecoration;

/**
 * Created by anthonyfaucogney on 20/07/2015.
 */
public class MultiHeaderRecyclerView extends RecyclerView {

    private int mBackgroundColor, mItemBackgroundColor, mTextColor;
    private float mLabelTextSize, mItemTitleTextSize, mItemValuelTextSize,mItemSpacing;
    private Drawable mCommentIcon;
    GridLayoutManager mLayoutManager;

    public MultiHeaderRecyclerView(Context context) {
        this(context, null);
    }

    public MultiHeaderRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiHeaderRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mLayoutManager = new GridLayoutManager(context, 2);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setHasFixedSize(true);
        addItemDecoration(new MarginDecoration(context));
        setLayoutManager(mLayoutManager);


//        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MultiHeaderRecyclerView);
//
//        mBackgroundColor = attributes.getColor(R.styleable.MultiHeaderRecyclerView_background_color, getResources().getColor(R.color.multiheader_background_color));
//        mItemBackgroundColor = attributes.getColor(R.styleable.MultiHeaderRecyclerView_item_background_color, getResources().getColor(R.color.multiheader_item_background_color));
//        mTextColor = attributes.getColor(R.styleable.MultiHeaderRecyclerView_text_color, getResources().getColor(R.color.multiheader_text_color));
//        mLabelTextSize = attributes.getDimension(R.styleable.MultiHeaderRecyclerView_label_text_size, getResources().getDimension(R.dimen.multiheader_label_text_size));
//        mItemTitleTextSize = attributes.getDimension(R.styleable.MultiHeaderRecyclerView_item_title_text_size, getResources().getDimension(R.dimen.multiheader_item_title_text_size));
//        mItemValuelTextSize = attributes.getDimension(R.styleable.MultiHeaderRecyclerView_item_value_text_size, getResources().getDimension(R.dimen.multiheader_item_value_text_size));
//        mCommentIcon= attributes.getDrawable(R.styleable.MultiHeaderRecyclerView_item_comment_icon);
//        mItemSpacing = attributes.getDimension(R.styleable.MultiHeaderRecyclerView_item_spacing, getResources().getDimension(R.dimen.multiheader_item_spacing));
//
//        attributes.recycle();



    }

    @Override
    final public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(final MultiHeaderNumberedAdapter headerAdapter) {
        super.setAdapter(headerAdapter);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return headerAdapter.isHeader(position) ? mLayoutManager.getSpanCount() : 1;
            }
        });


    }
}
