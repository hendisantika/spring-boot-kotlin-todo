package id.my.hendisantika.springbootkotlintodo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;
    private String description;
    private boolean completed;

    public Todo() {
        dateCreated = new Date();
        completed = false;
    }

    public Todo(String description) {
        this();
        this.description = description;
    }
}
