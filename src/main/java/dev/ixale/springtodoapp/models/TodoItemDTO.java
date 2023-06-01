package dev.ixale.springtodoapp.models;

import java.time.Instant;

public record TodoItemDTO(
        String description,
        TaskStatus status,
        TaskPriority priority,
        Instant createdAt,
        Instant updatedAt
) {
    public static TodoItemDTO from(TodoItem todoItem) {
        return new TodoItemDTO(
                todoItem.getDescription(),
                todoItem.getStatus(),
                todoItem.getPriority(),
                todoItem.getCreatedAt(),
                todoItem.getUpdatedAt()
        );
    }

    public TodoItem toTodoItem() {
        return new TodoItem(
                null,
                this.description,
                this.status,
                this.priority,
                this.createdAt,
                this.updatedAt
        );
    }
}
