package dev.ixale.springtodoapp.services;

import dev.ixale.springtodoapp.models.TodoItem;
import dev.ixale.springtodoapp.repositories.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }


    /**
    * Get all TodoItems
    * @return Iterable<TodoItem>
    */
    public List<TodoItem> getAll() {
        return this.todoItemRepository.findAll();
    }

    public Optional<TodoItem> getById(Long id) {
        return this.todoItemRepository.findById(id);
    }

    public Optional<TodoItem> save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(Instant.now());
        } else {
            todoItemRepository.findById(todoItem.getId()).ifPresentOrElse(
                    (item) -> {
                        todoItem.setCreatedAt(item.getCreatedAt());
                    },
                    () -> {
                        todoItem.setCreatedAt(Instant.now());
                    }
            );
        }
        todoItem.setUpdatedAt(Instant.now());

        return Optional.of(this.todoItemRepository.save(todoItem));
    }

    public Optional<TodoItem> deleteById(Long id) {
        Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        if (todoItem.isEmpty()) return Optional.empty();

        this.todoItemRepository.deleteById(id);
        return todoItem;
    }

    public Optional<List<TodoItem>> deleteAll() {
        Optional<List<TodoItem>> todoItems = Optional.of(this.todoItemRepository.findAll());
        if (todoItems.get().isEmpty()) return Optional.empty();

        this.todoItemRepository.deleteAll();
        return todoItems;
    }
}
