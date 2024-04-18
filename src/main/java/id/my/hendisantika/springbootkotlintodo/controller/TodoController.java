package id.my.hendisantika.springbootkotlintodo.controller;

import id.my.hendisantika.springbootkotlintodo.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:15
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/")
    public String todos(Model model) {
        model.addAttribute("todos", todoRepository.findAll(new PageRequest(0, 50)).getContent());
        return "todos";
    }
}
