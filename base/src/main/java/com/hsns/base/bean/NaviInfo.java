package com.hsns.base.bean;

import java.io.Serializable;
import java.util.List;

public class NaviInfo extends BaseBean<List<NaviInfo.Data>> implements Serializable {
    public class Data implements Serializable{
        private List<Root> articles;

        private int cid;

        private String name;

        public void setArticles(List<Root> articles) {
            this.articles = articles;
        }

        public List<Root> getArticles() {
            return this.articles;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getCid() {
            return this.cid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "articles=" + articles.toString() +
                    ", cid=" + cid +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    public class Root implements Serializable {
        private boolean alreadyInHomePage;

        private String apkLink;

        private int audit;

        private String author;

        private boolean canEdit;

        private int chapterId;

        private String chapterName;

        private boolean collect;

        private int courseId;

        private String desc;

        private String descMd;

        private String envelopePic;

        private boolean fresh;

        private String host;

        private int id;

        private String link;

        private String niceDate;

        private String niceShareDate;

        private String origin;

        private String prefix;

        private String projectLink;

        private long publishTime;

        private int realSuperChapterId;

        private int selfVisible;

        private String shareDate;

        private String shareUser;

        private int superChapterId;

        private String superChapterName;

        private List<Object> tags;

        private String title;

        private int type;

        private int userId;

        private int visible;

        private int zan;

        public void setAlreadyInHomePage(boolean alreadyInHomePage) {
            this.alreadyInHomePage = alreadyInHomePage;
        }

        public boolean getAlreadyInHomePage() {
            return this.alreadyInHomePage;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getApkLink() {
            return this.apkLink;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public int getAudit() {
            return this.audit;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public boolean getCanEdit() {
            return this.canEdit;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public int getChapterId() {
            return this.chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getChapterName() {
            return this.chapterName;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public boolean getCollect() {
            return this.collect;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
            return this.courseId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getDescMd() {
            return this.descMd;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public String getEnvelopePic() {
            return this.envelopePic;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public boolean getFresh() {
            return this.fresh;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getHost() {
            return this.host;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return this.link;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceDate() {
            return this.niceDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getNiceShareDate() {
            return this.niceShareDate;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOrigin() {
            return this.origin;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public String getProjectLink() {
            return this.projectLink;
        }

        public boolean isAlreadyInHomePage() {
            return alreadyInHomePage;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public boolean isCollect() {
            return collect;
        }

        public boolean isFresh() {
            return fresh;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public int getRealSuperChapterId() {
            return this.realSuperChapterId;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public int getSelfVisible() {
            return this.selfVisible;
        }

        public void setShareDate(String shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareDate() {
            return this.shareDate;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public String getShareUser() {
            return this.shareUser;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public int getSuperChapterId() {
            return this.superChapterId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getSuperChapterName() {
            return this.superChapterName;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public List<Object> getTags() {
            return this.tags;
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

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return this.visible;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getZan() {
            return this.zan;
        }

        @Override
        public String toString() {
            return "Root{" +
                    "alreadyInHomePage=" + alreadyInHomePage +
                    ", apkLink='" + apkLink + '\'' +
                    ", audit=" + audit +
                    ", author='" + author + '\'' +
                    ", canEdit=" + canEdit +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", descMd='" + descMd + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", host='" + host + '\'' +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", niceShareDate='" + niceShareDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime=" + publishTime +
                    ", realSuperChapterId=" + realSuperChapterId +
                    ", selfVisible=" + selfVisible +
                    ", shareDate='" + shareDate + '\'' +
                    ", shareUser='" + shareUser + '\'' +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    '}';
        }
    }


}
