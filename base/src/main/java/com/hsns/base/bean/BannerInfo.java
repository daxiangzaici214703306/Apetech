package com.hsns.base.bean;

import java.io.Serializable;
import java.util.List;

public class BannerInfo extends BaseBean<List<BannerInfo.Data>> {
    public class Data implements Serializable {
        private String desc;

        private int id;

        private String imagePath;

        private int isVisible;

        private int order;

        private String title;

        private int type;

        private String url;

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getIsVisible() {
            return this.isVisible;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return this.order;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
