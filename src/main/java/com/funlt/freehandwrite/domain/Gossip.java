package com.funlt.freehandwrite.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author leotoa
 */
@Data
public class Gossip {

    private String id;
    /**
     * 说说内容
     */
    private String word;
    /**
     * 状态
     */
    private int status;
    /**
     * 是否删除
     */
    private int deleted;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
}
