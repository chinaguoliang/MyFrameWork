package com.framework.chen.myframework.Entity;

import java.util.List;

/**
 * Created by chen on 17/3/26.
 */

public class VideoInfo {
    String msg;
    boolean success;
    List<VideoInfoItem> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<VideoInfoItem> getObj() {
        return obj;
    }

    public void setObj(List<VideoInfoItem> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", obj=" + obj +
                '}';
    }
}
