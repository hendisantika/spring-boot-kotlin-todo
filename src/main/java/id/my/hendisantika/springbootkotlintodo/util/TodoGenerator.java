package id.my.hendisantika.springbootkotlintodo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:14
 * To change this template use File | Settings | File Templates.
 */
public class TodoGenerator {
    private static final Logger log = LoggerFactory.getLogger(RequestStatisticsInterceptor.class);
    private static final SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    private static final String[] how = {"Casually", "Quickly", "Briefly", "It's important to", "Reluctantly", "Remember to",
            "Unfortunately it's necessary to", "Eagerly", "Happily", "Sadly, I will", "It's necessary", "Oh why must I"};
    private static final String[] what = {"go shopping", "buy groceries", "clean the house", "take vacation", "walk pets",
            "vacuum", "finish project", "go party", "pay rent", "go exercise", "have a lunch", "write blog", "study"};
    private static final String[] with = {"my friends", "John", "Mary", "my dad", "my mom", "my brother", "my sister", "my dog",
            "my neighbors", "my coworkers", "some random people", "feeling of great joy"};

    private TodoGenerator() {
    }
}
