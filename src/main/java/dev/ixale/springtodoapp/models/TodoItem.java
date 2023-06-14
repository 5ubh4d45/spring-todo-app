package dev.ixale.springtodoapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


/**
 * <b>TodoItem model</b>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "todo_items")
public class TodoItem{
    @Id
    @Min(message = "Id must be greater than zero!", value = 1L)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Description is required.")
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.LOW;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TodoItem todoItem)) return false;
        return this.toString().equals(todoItem.toString());
    }

    @Override
    public String toString() {
        return "{id: " + this.id
                + ", description: " + this.description
                + ", status: " + this.status
                + ", priority: " + this.priority
                + ", createdAt: " + this.createdAt
                + ", updatedAt: " + this.updatedAt + "}";
    }
}
