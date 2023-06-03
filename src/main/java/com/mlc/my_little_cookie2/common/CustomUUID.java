package com.mlc.my_little_cookie2.common;

import java.util.UUID;

public class CustomUUID {

    public String uuid(){
        return UUID.randomUUID().toString().substring(0,15);
    }
}
