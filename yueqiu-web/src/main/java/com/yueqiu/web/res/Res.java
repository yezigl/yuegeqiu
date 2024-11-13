/**
 * Copyright 2013 Sohu.com Inc. All Rights Reserved.
 */
package com.yueqiu.web.res;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import jakarta.servlet.ServletResponse;

import org.springframework.http.MediaType;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * description here
 * 
 * @author lukeli
 * @version 1.0 2013-11-22
 */
public class Res implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonInclude(Include.NON_NULL)
    protected Integer code;

    @JsonInclude(Include.NON_NULL)
    protected String msg;
    
    public Res() {
        
    }
    
    public Res(Status status) {
        this.code = status.code();
        this.msg = status.msg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(Status status, Object... args) {
        this.code = status.code();
        this.msg = String.format(status.msg(), args);
    }

    public void output(ServletResponse response, Res res) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(res));
        out.flush();
        out.close();
    }
}
