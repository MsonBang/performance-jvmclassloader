package com.mxb.learn.learnclassloader;

/**
 * @author miaoxuebing
 * @Description: TODO[测试实体类]
 * @date 2020/7/6 10:22 下午
 */
public class User {

    private String userName;
    private String age;

    public User() {
    }

    public User(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
