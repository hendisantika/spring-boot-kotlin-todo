package id.my.hendisantika.springbootkotlintodo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

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
}
