package fr.millezimtech.lib.multiheaderrecyclerview.Sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.millezimtech.lib.designlibrary.R;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Model.ItemList;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Utils;

/**
 * Created by anthonyfaucogney on 20/07/2015.
 */
public class ApplicationUtils {

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
                        obj.put(currentName, Utils.merge(currentValue, comment));

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

}
