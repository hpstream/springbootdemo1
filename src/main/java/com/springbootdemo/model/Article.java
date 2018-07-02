package com.springbootdemo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Article {

    private String id;

    private String title;

    private  String subtitle;

    private String des;

    private String content;

    private  Boolean isTop;

    private  String createTime;

    private  String updateTime;
}
