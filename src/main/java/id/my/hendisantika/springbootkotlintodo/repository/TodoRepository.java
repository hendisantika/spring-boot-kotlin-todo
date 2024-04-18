package id.my.hendisantika.springbootkotlintodo.repository;

import id.my.hendisantika.springbootkotlintodo.model.Todo;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;

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
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query(value = "select t from Todo t")
    Stream<Todo> streamAll();

    /**
     * We need this method variant instead of one that returns Page because methods that return Page
     * always execute count query, which is slow when using pagination in batch to export large tables.
     */
    @Transactional(readOnly = true)
    Slice<Todo> findAllBy(Pageable page);

}
