package com.yuan.myword.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageQuery implements Serializable {
    //页码
    private int pageNum;

    //每页记录数
    private int pageSize;
}
