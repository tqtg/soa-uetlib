package soa.assignment.uetlib.model;

import org.json.JSONObject;

/**
 * Created by TuanTQ on 9/26/15.
 */
public class Category {

    private String id;
    private String name;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(JSONObject category) {
        try {
            this.id = category.getString("id");
            this.name = category.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
