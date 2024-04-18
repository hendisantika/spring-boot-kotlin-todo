package id.my.hendisantika.springbootkotlintodo.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:12
 * To change this template use File | Settings | File Templates.
 */
public class RequestStatisticsInterceptor implements AsyncHandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestStatisticsInterceptor.class);
    private final ThreadLocal<Long> time = new ThreadLocal<>();
    @Autowired
    private HibernateStatisticsInterceptor statisticsInterceptor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        time.set(System.currentTimeMillis());
        statisticsInterceptor.startCounter();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long queryCount = statisticsInterceptor.getQueryCount();
        if (modelAndView != null) {
            modelAndView.addObject("_queryCount", queryCount);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long duration = System.currentTimeMillis() - time.get();
        Long queryCount = statisticsInterceptor.getQueryCount();
        statisticsInterceptor.clearCounter();
        time.remove();
        log.info("[Time: {} ms] [Queries: {}] {} {}", duration, queryCount, request.getMethod(), request.getRequestURI());
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //concurrent handling cannot be supported here
        statisticsInterceptor.clearCounter();
        time.remove();
    }
}
