package com.example.luke.newsclient.entity.GanHuo;

public class GhCategory {

    /**
     * _id : 5827dca9421aa911e32d87ce
     * created_at : 2016-11-13T11:23:21.376Z
     * icon : http://ww4.sinaimg.cn/large/610dc034jw1f9sfo52iwoj202s02sq2r.jpg
     * id : blogspot
     * title : Android Developers Blog
     */

    private String _id;
    private String created_at;
    private String icon;
    private String id;
    private String title;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
