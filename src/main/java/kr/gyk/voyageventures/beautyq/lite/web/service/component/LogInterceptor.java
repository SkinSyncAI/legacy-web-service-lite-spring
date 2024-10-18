package kr.gyk.voyageventures.beautyq.lite.web.service.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long reqTime = System.currentTimeMillis();
        request.setAttribute("reqTime", reqTime);
        log.info("Begin {}:{}", request.getRequestedSessionId(), reqTime);
        log.info("{} {} -> {}", request.getMethod(), request.getRequestURI(), handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long reqTime = (long)request.getAttribute("reqTime");
        long resTime = System.currentTimeMillis();
        log.info("End {}:{}->{} [{}ms]", request.getRequestedSessionId(), reqTime, resTime, resTime - reqTime);
    }

}