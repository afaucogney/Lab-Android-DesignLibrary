package fr.millezimtech.lib.multiheaderrecyclerview.Library.Model;

import android.util.SparseArray;
import android.view.View;

import com.google.common.collect.ImmutableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by anthonyfaucogney on 20/07/2015.
 */

public class ItemList {
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
    SparseArray<AttributeObject> contentArray;

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
            AttributeObject att = null;
            try {
                String value = ((JSONObject) content.get(key)).getString("value");
                att = new AttributeObject(new JSONObject(ImmutableMap.of(key, value)));
                if (((JSONObject) content.get(key)).has("comment"))
                    att.setExtraComment(((JSONObject) content.get(key)).getString("comment"));


            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                contentArray.append(w, att);
                w++;
            }

        }
    }

    public AttributeObject getObject(int position) {
        return contentArray.get(position);
    }
}

