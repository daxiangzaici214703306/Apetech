package com.hsns.base.bean;

import java.io.Serializable;
import java.util.List;

public class CoinRankInfo extends BaseBean<CoinRankInfo.Data> {

    public class Data implements Serializable {
        private int curpage;
        private List<Datas> datas;
        private int offset;
        private boolean over;
        private int pagecount;
        private int size;
        private int total;

        public void setCurpage(int curpage) {
            this.curpage = curpage;
        }

        public int getCurpage() {
            return curpage;
        }

        public boolean isOver() {
            return over;
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

        public void setPagecount(int pagecount) {
            this.pagecount = pagecount;
        }

        public int getPagecount() {
            return pagecount;
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
                    "curpage=" + curpage +
                    ", datas=" + datas +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pagecount=" + pagecount +
                    ", size=" + size +
                    ", total=" + total +
                    '}';
        }
    }


    public class Datas implements Serializable {
        private int coinCount;
        private int level;
        private String nickname;
        private String rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Datas{" +
                    "coinCount=" + coinCount +
                    ", level=" + level +
                    ", nickname='" + nickname + '\'' +
                    ", rank='" + rank + '\'' +
                    ", userId=" + userId +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
