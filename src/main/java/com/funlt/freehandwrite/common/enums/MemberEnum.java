package com.funlt.freehandwrite.common.enums;

public enum MemberEnum {

    //正常
    NORMAL(0,"normal"),
    //禁用
    FORBIDDEN(-1,"forbidden"),
    //未激活
    INACTIVE(-2,"inactive");

    Integer code;
    String desc;

    MemberEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
