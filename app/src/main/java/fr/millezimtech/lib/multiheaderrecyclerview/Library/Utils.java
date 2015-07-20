package fr.millezimtech.lib.multiheaderrecyclerview.Library;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by anthonyfaucogney on 20/07/2015.
 */
public class Utils {

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
