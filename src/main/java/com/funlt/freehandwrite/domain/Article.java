package com.funlt.freehandwrite.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author leotoa
 */
public class Article {

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 类别
     */
    private String categoryId;
    /**
     * 标签
     */
    private List<String> tagIds;

    private String content;

    private Integer readCount;

    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
