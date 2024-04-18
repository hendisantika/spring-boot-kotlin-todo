package id.my.hendisantika.springbootkotlintodo.repository;

import id.my.hendisantika.springbootkotlintodo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:07
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
