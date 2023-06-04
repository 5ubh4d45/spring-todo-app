package dev.ixale.springtodoapp.controllers;

import dev.ixale.springtodoapp.models.TaskPriority;
import dev.ixale.springtodoapp.models.TaskStatus;
import dev.ixale.springtodoapp.models.TodoItem;
import dev.ixale.springtodoapp.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todo-items")
public class TodoItemRestController {
    private final TodoItemService todoItemService;

    public TodoItemRestController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoItem>> getAll() {
        return ResponseEntity.ok(this.todoItemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getById(@PathVariable Long id) {
        return ResponseEntity.of(this.todoItemService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<TodoItem> save(@Valid @RequestBody TodoItem todoItem) {
        return ResponseEntity.of(this.todoItemService.save(todoItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable Long id, @Valid @RequestBody TodoItem todoItem) {
        todoItem.setId(id);
        return ResponseEntity.of(this.todoItemService.save(todoItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoItem> deleteById(@PathVariable Long id) {
        return ResponseEntity.of(this.todoItemService.deleteById(id));
    }
}
