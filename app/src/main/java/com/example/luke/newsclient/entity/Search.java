package com.example.luke.newsclient.entity;

import java.util.List;

public class Search {

    /**
     * title : 斗鱼CEO空降钱小佳直播间,弹幕实锤401晦气?网友:斗鱼第一带哥
     * display_url : https://baijiahao.baidu.com/s?id=1630339722423364644&wfr=spider&for=pc
     * url : https://baijiahao.baidu.com/s?id=1630339722423364644&wfr=spider&for=pc
     * nid : 15473049847656448390
     * site : 戏说电子竞技
     * sourcets : 1554813827000
     * ts : 1554813827000
     * type : searchnews
     * imageurls : [{"url":"http://t8.baidu.com/it/u=2622422956,919775023&fm=190&app=57&f=JPEG?w=640&h=881&s=2C32169F460674E82CE1C5DA03005033","width":141,"height":99,"url_webp":"https://timg01.bdimg.com/timg?news&quality=80&size=f141_99&imgtype=1&wh_rate=0&sec=0&di=56fa4cc8a7beb26d5e8c01c426e7707c&er=1&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2622422956%2C919775023%26fm%3D190%26app%3D57%26f%3DJPEG%3Fw%3D640%26h%3D881%26s%3D2C32169F460674E82CE1C5DA03005033"}]
     * content : []
     * abs : 关注斗鱼直播的都知道,因为前不久刚刚结束的斗鱼粉丝节,现在表面上的斗鱼二哥钱小佳和斗鱼老三旭旭宝宝已经产...
     * layout : image1
     * display_type : 2
     */

    private String title;
    private String display_url;
    private String url;
    private String nid;
    private String site;
    private long sourcets;
    private long ts;
    private String type;
    private String abs;
    private String layout;
    private int display_type;
    private List<ImageurlsBean> imageurls;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public long getSourcets() {
        return sourcets;
    }

    public void setSourcets(long sourcets) {
        this.sourcets = sourcets;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public int getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(int display_type) {
        this.display_type = display_type;
    }

    public List<ImageurlsBean> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<ImageurlsBean> imageurls) {
        this.imageurls = imageurls;
    }


    public static class ImageurlsBean {
        /**
         * url : http://t8.baidu.com/it/u=2622422956,919775023&fm=190&app=57&f=JPEG?w=640&h=881&s=2C32169F460674E82CE1C5DA03005033
         * width : 141
         * height : 99
         * url_webp : https://timg01.bdimg.com/timg?news&quality=80&size=f141_99&imgtype=1&wh_rate=0&sec=0&di=56fa4cc8a7beb26d5e8c01c426e7707c&er=1&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2622422956%2C919775023%26fm%3D190%26app%3D57%26f%3DJPEG%3Fw%3D640%26h%3D881%26s%3D2C32169F460674E82CE1C5DA03005033
         */

        private String url;
        private int width;
        private int height;
        private String url_webp;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl_webp() {
            return url_webp;
        }

        public void setUrl_webp(String url_webp) {
            this.url_webp = url_webp;
        }
    }
}
