package fr.millezimtech.lib.multiheaderrecyclerview.Library.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by anthonyfaucogney on 20/07/2015.
 */

public class AttributeObject {
    String name;
    String value;
    String extraComment;

    public AttributeObject(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public AttributeObject(JSONObject obj) {

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

    public String getExtraComment() {
        return extraComment;
    }

    public void setExtraComment(String extraComment) {
        this.extraComment = extraComment;
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