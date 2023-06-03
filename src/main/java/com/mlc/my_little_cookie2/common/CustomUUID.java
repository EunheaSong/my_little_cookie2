package com.mlc.my_little_cookie2.common;

import java.util.UUID;

/*
    pk전략 :

 */
public class CustomUUID {

    public static String uuid(){
        String uuid = UUID.randomUUID().toString().substring(0,9);
        return uuid + UUID.randomUUID().toString().substring(0,9);
    }
}
