package example.model;


import com.mysql.cj.jdbc.Blob;

public class VsemPoShapkeModel {
    private int color;
    private int id;
    private int picture_type;
    private Blob textile;
    private String textile_name;
    private int thickness;


    public VsemPoShapkeModel(int id, String textile_name, Blob textile, int thickness, int color, int picture_type) {
        this.color = color;
        this.id = id;
        this.picture_type = picture_type;
        this.textile = textile;
        this.textile_name = textile_name;
        this.thickness = thickness;
    }

    public VsemPoShapkeModel() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPicture_type() {
        return picture_type;
    }

    public void setPicture_type(int picture_type) {
        this.picture_type = picture_type;
    }

    public Blob getTextile() {
        return textile;
    }

    public void setTextile(Blob textile) {
        this.textile = textile;
    }

    public String getTextile_name() {
        return textile_name;
    }

    public void setTextile_name(String textile_name) {
        this.textile_name = textile_name;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
}
