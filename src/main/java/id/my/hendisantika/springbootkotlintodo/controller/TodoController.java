package id.my.hendisantika.springbootkotlintodo.controller;

import id.my.hendisantika.springbootkotlintodo.model.Todo;
import id.my.hendisantika.springbootkotlintodo.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping(value = "/todos/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/todos/{id}/completed")
    public String deleteTodo(@PathVariable("id") Todo todo) {
        todo.setCompleted(true);
        todoRepository.save(todo);
        return "redirect:/";
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public String createTodo(@Valid TodoDto todoDto) {
        todoRepository.save(todoDto.toTodo());
        return "redirect:/";
    }

}
