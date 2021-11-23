package com.hsns.base.bean;

import java.io.Serializable;
import java.util.List;

public class PersonCoinInfo extends BaseBean<PersonCoinInfo.Data> {

    public class Data implements Serializable {

        private int curPage;
        private List<Datas> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public boolean isOver() {
            return over;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setDatas(List<Datas> datas) {
            this.datas = datas;
        }

        public List<Datas> getDatas() {
            return datas;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return over;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "curPage=" + curPage +
                    ", datas=" + datas +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pageCount=" + pageCount +
                    ", size=" + size +
                    ", total=" + total +
                    '}';
        }
    }


    public class Datas implements Serializable {

        private int coinCount;
        private long date;
        private String desc;
        private long id;
        private String reason;
        private int type;
        private long userId;
        private String userName;

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public long getDate() {
            return date;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }

        @Override
        public String toString() {
            return "Datas{" +
                    "coinCount=" + coinCount +
                    ", date=" + date +
                    ", desc='" + desc + '\'' +
                    ", id=" + id +
                    ", reason='" + reason + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }


}
