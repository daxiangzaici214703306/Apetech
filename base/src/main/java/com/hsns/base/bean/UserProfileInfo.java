package com.hsns.base.bean;

import java.io.Serializable;
import java.util.List;

public class UserProfileInfo extends BaseBean<UserProfileInfo.Data> {

    public class CoinInfo implements Serializable {
        private int coinCount;

        private int level;

        private String nickname;

        private String rank;

        private long userId;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        private String username;

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getCoinCount() {
            return this.coinCount;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return this.rank;
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return this.username;
        }

        @Override
        public String toString() {
            return "CoinInfo{" +
                    "coinCount=" + coinCount +
                    ", level=" + level +
                    ", nickname='" + nickname + '\'' +
                    ", rank='" + rank + '\'' +
                    ", userId=" + userId +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public class UserInfo implements Serializable {
        private boolean admin;

        private List<Object> chapterTops;

        private int coinCount;

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }

        private List<Integer> collectIds;

        public List<Object> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<Object> chapterTops) {
            this.chapterTops = chapterTops;
        }

        private String email;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        private String icon;

        private long id;

        private String nickname;

        private String password;

        private String publicName;

        private String token;

        private int type;

        private String username;

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public boolean getAdmin() {
            return this.admin;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getCoinCount() {
            return this.coinCount;
        }

        public boolean isAdmin() {
            return admin;
        }


        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return this.email;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return this.icon;
        }


        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getPublicName() {
            return this.publicName;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return this.username;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "admin=" + admin +
//                    ", chapterTops=" + chapterTops +
                    ", coinCount=" + coinCount +
//                    ", collectIds=" + collectIds +
                    ", email='" + email + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", nickname='" + nickname + '\'' +
                    ", password='" + password + '\'' +
                    ", publicName='" + publicName + '\'' +
                    ", token='" + token + '\'' +
                    ", type=" + type +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public class Data implements Serializable {
        private CoinInfo coinInfo;

        private UserInfo userInfo;

        public void setCoinInfo(CoinInfo coinInfo) {
            this.coinInfo = coinInfo;
        }

        public CoinInfo getCoinInfo() {
            return this.coinInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public UserInfo getUserInfo() {
            return this.userInfo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "coinInfo=" + coinInfo.toString() +
                    ", userInfo=" + userInfo.toString() +
                    '}';
        }
    }


}
