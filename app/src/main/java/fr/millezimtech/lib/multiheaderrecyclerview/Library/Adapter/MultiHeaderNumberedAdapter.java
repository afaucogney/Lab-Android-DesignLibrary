package fr.millezimtech.lib.multiheaderrecyclerview.Library.Adapter;

/**
 * Created by anthonyfaucogney on 17/07/2015.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.millezimtech.lib.designlibrary.R;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Model.AttributeObject;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Model.ItemList;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.ViewHolder.TextViewHolder;

public class MultiHeaderNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {

    private int itemsCount = 0;
    List<ItemList> itemsList;
    List<Integer> headersPositions;
    Activity activity;

    public MultiHeaderNumberedAdapter(List<ItemList> itemsList, Activity activity) {
        if (itemsList == null) {
            throw new IllegalArgumentException("headerView may not be null");
        }
        this.activity = activity;
        this.itemsList = itemsList;
        headersPositions = new ArrayList<>(itemsList.size());
        for (ItemList item : this.itemsList) {
            headersPositions.add(itemsCount);
            itemsCount += item.getCount() + 1;
        }
    }

    public boolean isHeader(int position) {
        return headersPositions.contains(position);
    }

    public AttributeObject getItem(int position) {
        final int listIndex = getListIndex(position);
        final int listHeaderIndex = headersPositions.get(listIndex);

        int location = position - listHeaderIndex - 1;
        return this.itemsList.get(listIndex).getObject(location);
//        final String label = labelsList.get(listIndex).get(location);  // Subtract 1 for headerView
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (isHeader(position))
            return new TextViewHolder(getHeaderView(position));
        return new TextViewHolder(getAttributeView(parent));
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        if (isHeader(position)) {
            return;
        }
        final int listIndex = getListIndex(position);
        final int listHeaderIndex = headersPositions.get(listIndex);

//        int location = position - listHeaderIndex - 1;
//        final String label = labelsList.get(listIndex).get(location);  // Subtract 1 for headerView

        AttributeObject currentAttribute = getItem(position);
        if (currentAttribute != null)
            holder.render(currentAttribute, this.activity);
        else
            Log.v("Hello", String.valueOf(position));


    }

    @Override
    public int getItemViewType(int position) {
        return position;
//        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public int getListIndex(int position) {
        int r = headersPositions.size() - 1;
        int j = 0;
        for (int i : headersPositions) {
            if (position < i) {
                r = j - 1;
                break;
            }
            j++;
        }
        return r;
    }

    public View getHeaderView(int position) {
        int list = getListIndex(position);
        if (list != -1) {
            Log.v("Hello", "Position: " + position + " - id: " + list);
            if (itemsList.get(list) != null) {
                Log.v("Hello", position + "-" + itemsCount);
                return itemsList.get(list).getHeaderView();
            }
        }
        return null;
    }

    private View getAttributeView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item, parent, false);


    }


}