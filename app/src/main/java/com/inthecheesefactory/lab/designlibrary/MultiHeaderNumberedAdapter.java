package com.inthecheesefactory.lab.designlibrary;

/**
 * Created by anthonyfaucogney on 17/07/2015.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MultiHeaderNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    //    private final View header;
    private List<List<String>> labelsList = null;
    private int count = 0;
    List<ItemList> itemsList;
    List<Integer> headerPositions;

    public MultiHeaderNumberedAdapter(List<ItemList> itemList) {
        if (itemList == null) {
            throw new IllegalArgumentException("header may not be null");
        }

        this.itemsList = itemList;
        labelsList = new ArrayList<>(itemList.size());
        headerPositions = new ArrayList<>(itemList.size());
        for (ItemList item : itemsList) {
            headerPositions.add(count);
            List<String> labels = new ArrayList<>(item.count);
            for (int i = 0; i < item.count; ++i) {
                labels.add(String.valueOf(i));
            }
            labelsList.add(labels);
            count += labels.size() + 1;
        }
    }

    public boolean isHeader(int position) {
        return headerPositions.contains(position);
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (isHeader(position))
            return new TextViewHolder(getHeader(position));

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        if (isHeader(position)) {
            return;
        }
        final int listIndex = getList(position);
        final int listHeaderIndex = headerPositions.get(listIndex);
        final int headerNumber = listIndex + 1;

        int location = position - listHeaderIndex - 1;
        final String label = labelsList.get(listIndex).get(location);  // Subtract 1 for header

        holder.textViewTitle.setText("Title " + label);
        holder.textViewContent.setText(label);
        holder.textViewTitle.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Toast.makeText(
                                holder.textViewTitle.getContext(), label, Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }

    @Override
    public int getItemViewType(int position) {
        return position;
//        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public int getList(int position) {
        int r = headerPositions.size() - 1;
        int j = 0;
        for (int i : headerPositions) {
            if (position < i) {
                r = j - 1;
                break;
            }
            j++;
        }
        return r;
    }

    public View getHeader(int position) {
        int list = getList(position);
        if (list != -1) {
            Log.v("Hello", "Position: " + position + " - id: " + list);
            if (itemsList.get(list) != null) {
                Log.v("Hello", position + "-" + count);
                return itemsList.get(list).header;
            }
        }
        return null;
    }

    public static class ItemList {
        int count;
        String name;
        View header;
    }

    public static List<ItemList> getRandomItemList(int listsCount, Context context, ViewGroup parent) {
        List<ItemList> list = new ArrayList<>(listsCount);

        for (int i = 0; i < listsCount; i++) {

            ItemList item = new ItemList();
            item.name = "Name " + i;
            item.count = (int) (Math.random() * 20);
            if ((int) (Math.random() * 2) == 1) {
                item.count = (int) (Math.random() * 20);
                item.header = LayoutInflater.from(context).inflate(R.layout.header_picture, parent, false);
            }
            else {
                item.count = 0;
                item.header = LayoutInflater.from(context).inflate(R.layout.header_label, parent, false);
                ((TextView) item.header.findViewById(R.id.text)).setText(item.name);
            }
            list.add(item);
        }
        return list;

    }
}