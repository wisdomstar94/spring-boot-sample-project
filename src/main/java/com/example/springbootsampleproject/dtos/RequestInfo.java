package com.example.springbootsampleproject.dtos;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Setter
@Getter
public class RequestInfo {
    String ip;
    String header;
    String body;
    String query;
    String method;
    String url;
    String uri;
    //    LocalDateTime timestamp;
    String datetime;

    public RequestInfo(HttpServletRequest request) {
        this.ip = this.getClientIp(request);
        this.header = this.getHeaderString(request);
//        this.body = this.getBodyString(request);
        this.query = request.getQueryString();
        this.method = request.getMethod();
        this.url = request.getRequestURL().toString();
        this.uri = request.getRequestURI();
        this.datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = request.getRemoteAddr();
        return ip;
    }

    public String getHeaderString(HttpServletRequest request) {
        List<HeaderItem> headerItems = this.getHeaderObject(request);;
        String json = new Gson().toJson(headerItems);
        return json;
    }

    public List<HeaderItem> getHeaderObject(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        List<HeaderItem> headerItems = new ArrayList<HeaderItem>();
        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            String value = request.getHeader(key);
            HeaderItem headerItem = new HeaderItem();
            headerItem.setKey(key);
            headerItem.setValue(value);
            headerItems.add(headerItem);
        }
        return headerItems;
    }
}
