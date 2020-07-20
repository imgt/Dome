package com.lg.dome.mvp.model.bean;

/**
 * Created by lqj on 2020/7/20.
 */
public class User {
        private String password;
        private String username;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "User{" +
                    "password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
}
