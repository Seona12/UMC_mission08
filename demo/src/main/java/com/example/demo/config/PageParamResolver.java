// src/main/java/com/example/demo/config/PageParamResolver.java
package com.example.demo.config;

import com.example.demo.web.annotation.PageParam;
import com.example.demo.web.exception.PageException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageParam.class)
                && int.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        String s = webRequest.getParameter("page");
        if (s == null) {
            throw new PageException("페이지 파라미터(page)가 누락되었습니다.");
        }
        int p;
        try {
            p = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new PageException("페이지는 정수여야 합니다.");
        }
        if (p < 1) {
            throw new PageException("페이지는 1 이상이어야 합니다.");
        }
        return p - 1;
    }
}
