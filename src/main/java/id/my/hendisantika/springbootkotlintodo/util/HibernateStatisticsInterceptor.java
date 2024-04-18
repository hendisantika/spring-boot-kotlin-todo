package id.my.hendisantika.springbootkotlintodo.util;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:08
 * To change this template use File | Settings | File Templates.
 */
public class HibernateStatisticsInterceptor extends EmptyInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HibernateStatisticsInterceptor.class);

    private final ThreadLocal<Long> queryCount = new ThreadLocal<>();

    public void startCounter() {
        queryCount.set(0L);
    }

    public Long getQueryCount() {
        return queryCount.get();
    }

    public void clearCounter() {
        queryCount.remove();
    }

    @Override
    public String onPrepareStatement(String sql) {
        Long count = queryCount.get();
        if (count != null) {
            queryCount.set(count + 1);
        }
        //log.info(sql);
        return super.onPrepareStatement(sql);
    }
}
