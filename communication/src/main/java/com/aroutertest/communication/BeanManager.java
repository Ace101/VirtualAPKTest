package com.aroutertest.communication;

import com.aroutertest.communication.entitys.Bean;
import com.aroutertest.communication.entitys.UserInfo;

/**
 * @author zhangshihao
 * @date 2018/12/12 0012
 */
public class BeanManager {

    private static Bean bean;
    private static UserInfo info;

    public static void init(Bean bean) {
        BeanManager.bean = bean;
    }

    public static Bean getInstance() {
        return bean;
    }

    public static void initUserInfo(UserInfo info) {
        BeanManager.info = info;
    }

    public static UserInfo getUserInfo() {
        return info;
    }
}
