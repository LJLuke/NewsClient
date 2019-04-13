package com.example.luke.newsclient.entity;

public class News {

    /**
     * uniquekey : 3969a6cb9874f395849091c13a069178
     * title : 岳云鹏迎来33岁生日，本人亲自发文提醒，二爷的祝福成全场焦点
     * date : 2019-04-02 19:47
     * category : 头条
     * author_name : 光明网
     * url : http://mini.eastday.com/mobile/190402194722488.html
     * thumbnail_pic_s : http://02imgmini.eastday.com/mobile/20190402/20190402194722_c59978e6ee11d5b3af4fc550eaf2e6f9_1_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://02imgmini.eastday.com/mobile/20190402/20190402194722_c59978e6ee11d5b3af4fc550eaf2e6f9_3_mwpm_03200403.jpg
     * thumbnail_pic_s03 : http://02imgmini.eastday.com/mobile/20190402/20190402194722_c59978e6ee11d5b3af4fc550eaf2e6f9_6_mwpm_03200403.jpg
     */

    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }
}
