package com.example.springbootsampleproject.filters;

import com.example.springbootsampleproject.dtos.responses.GlobalErrorResponseInfo;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (RuntimeException ex){
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,response,ex);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response,Throwable ex){
        response.setStatus(status.value());
        response.setContentType("application/json");
        GlobalErrorResponseInfo errorResponse = new GlobalErrorResponseInfo();
        errorResponse.setMsg(ex.getMessage());
        try{
            String json = new Gson().toJson(errorResponse);
            System.out.println(json);
            response.getWriter().write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
