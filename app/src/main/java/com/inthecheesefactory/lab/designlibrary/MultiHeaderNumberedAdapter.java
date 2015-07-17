package com.inthecheesefactory.lab.designlibrary;

/**
 * Created by anthonyfaucogney on 17/07/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiHeaderNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {

    //    private final View headerView;
//    private List<List<String>> labelsList = null;
    private int count = 0;
    List<ItemList> itemsList;
    List<Integer> headerPositions;
    Activity activity;

    public MultiHeaderNumberedAdapter(List<ItemList> itemsList, Activity activity) {
        if (itemsList == null) {
            throw new IllegalArgumentException("headerView may not be null");
        }
        this.activity = activity;
        this.itemsList = itemsList;
//        labelsList = new ArrayList<>(itemsList.size());
        headerPositions = new ArrayList<>(itemsList.size());
        for (ItemList item : this.itemsList) {
            headerPositions.add(count);
//            List<String> labels = new ArrayList<>(item.count);
//            for (int i = 0; i < item.count; ++i) {
//                labels.add(String.valueOf(i));
//            }
//            labelsList.add(labels);
            count += item.getCount() + 1;
        }
    }

    public boolean isHeader(int position) {
        return headerPositions.contains(position);
    }

    public AttributObject getItem(int position) {
        final int listIndex = getListIndex(position);
        final int listHeaderIndex = headerPositions.get(listIndex);

        int location = position - listHeaderIndex - 1;
        return this.itemsList.get(listIndex).getObject(location);
//        final String label = labelsList.get(listIndex).get(location);  // Subtract 1 for headerView
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
        final int listIndex = getListIndex(position);
        final int listHeaderIndex = headerPositions.get(listIndex);

//        int location = position - listHeaderIndex - 1;
//        final String label = labelsList.get(listIndex).get(location);  // Subtract 1 for headerView

        AttributObject currentAttribut = getItem(position);
        if (currentAttribut != null)

            holder.render(currentAttribut, this.activity);
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
        return count;
    }

    public int getListIndex(int position) {
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
        int list = getListIndex(position);
        if (list != -1) {
            Log.v("Hello", "Position: " + position + " - id: " + list);
            if (itemsList.get(list) != null) {
                Log.v("Hello", position + "-" + count);
                return itemsList.get(list).headerView;
            }
        }
        return null;
    }

    public static class ItemList {
        int count;
        String name;
        View headerView;

        public JSONObject getContent() {
            return content;
        }

        public void setContent(JSONObject content) {
            this.content = content;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public View getHeaderView() {
            return headerView;
        }

        public void setHeaderView(View headerView) {
            this.headerView = headerView;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        JSONObject content;
        SparseArray<AttributObject> contentArray;

        public ItemList(String name, JSONObject content, View headerView) {
            this.headerView = headerView;
            this.name = name;
            this.count = content.length();
            this.content = content;
            contentArray = new SparseArray<>(this.count);
            Iterator it = content.keys();
//

            int w = 0;
            while (it.hasNext()) {
                String key = (String) it.next();
                AttributObject att = null;
                try {
                    String value = ((JSONObject) content.get(key)).getString("value");
                    att = new AttributObject(new JSONObject(ImmutableMap.of(key, value)));
                    if (((JSONObject) content.get(key)).getString("comment") != null)
                        att.setExtraComment(((JSONObject) content.get(key)).getString("comment"));


                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    contentArray.append(w, att);
                    w++;
                }

            }
        }

        public AttributObject getObject(int position) {
            return contentArray.get(position);
        }
    }

    public static List<ItemList> getRandomItemList(int listsCount, Context context, ViewGroup parent) {
        List<ItemList> list = new ArrayList<>(listsCount);

        for (int i = 0; i < listsCount; i++) {

            JSONObject obj = new JSONObject();


            View headerView;
            String name = "Firstname " + i;
            int count = (int) (Math.random() * 20);

            for (int j = 0; j < count; j++) {
                try {

                    JSONObject currentValue = new JSONObject(ImmutableMap.of("value", String.valueOf((int) (Math.random() * 2000))));

                    String currentName = "name " + j;
                    obj.accumulate(currentName, currentValue);
                    if ((int) (Math.random() * 2) == 1) {
                        JSONObject comment = new JSONObject(ImmutableMap.of("comment", "bla"));
                        obj.put(currentName, merge(currentValue, comment));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if ((int) (Math.random() * 2) == 1) {
                headerView = LayoutInflater.from(context).inflate(R.layout.header_picture, parent, false);
            } else {
                headerView = LayoutInflater.from(context).inflate(R.layout.header_label, parent, false);
                ((TextView) headerView.findViewById(R.id.text)).setText(name);
            }
            ItemList item = new ItemList(name, obj, headerView);
            list.add(item);
        }
        return list;

    }

    public static class AttributObject {
        String name;
        String value;
        String extraComment;

        public AttributObject(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getExtraComment() {
            return extraComment;
        }

        public void setExtraComment(String extraComment) {
            this.extraComment = extraComment;
        }

        public AttributObject(JSONObject obj) {

            if (obj.length() != 1)
                throw new Error("Bad Obj Size");
            Iterator it = obj.keys();

            this.name = obj.keys().next();
            try {
                this.value = (String) obj.get(this.name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static JSONObject merge(JSONObject obj1, JSONObject obj2) throws JSONException {
        JSONObject merged = new JSONObject();
        JSONObject[] objs = new JSONObject[]{obj1, obj2};
        for (JSONObject obj : objs) {
            Iterator it = obj.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                merged.put(key, obj.get(key));
            }
        }
        return merged;
    }
}