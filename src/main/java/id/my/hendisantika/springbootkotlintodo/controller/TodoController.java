package id.my.hendisantika.springbootkotlintodo.controller;

import id.my.hendisantika.springbootkotlintodo.model.Todo;
import id.my.hendisantika.springbootkotlintodo.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import static id.my.hendisantika.springbootkotlintodo.util.LambdaExceptionUtil.rethrowConsumer;

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

    @PostMapping(value = "/todos")
    public String createTodo(@Valid TodoDto todoDto) {
        todoRepository.save(todoDto.toTodo());
        return "redirect:/";
    }

    @GetMapping(value = "/todos.csv")
    @Transactional(readOnly = true)
    public void exportTodosCSV(HttpServletResponse response) {
        response.addHeader("Content-Type", "application/csv");
        response.addHeader("Content-Disposition", "attachment; filename=todos.csv");
        response.setCharacterEncoding("UTF-8");
        try (Stream<Todo> todoStream = todoRepository.streamAll()) {
            PrintWriter out = response.getWriter();
            todoStream.forEach(rethrowConsumer(todo -> {
                String line = todoToCSV(todo);
                out.write(line);
                out.write("\n");
                entityManager.detach(todo);
            }));
            out.flush();
        } catch (IOException e) {
            log.info("Exception occurred " + e.getMessage(), e);
            throw new RuntimeException("Exception occurred while exporting results", e);
        }
    }
}
