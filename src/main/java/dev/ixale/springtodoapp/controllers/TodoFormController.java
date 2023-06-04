package dev.ixale.springtodoapp.controllers;


import dev.ixale.springtodoapp.models.TodoItem;
import dev.ixale.springtodoapp.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoFormController {
    private final TodoItemService todoItemService;

    public TodoFormController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/create-todo")
    public ModelAndView showTodoForm(TodoItem todoItem) {
        ModelAndView modelAndView = new ModelAndView("new-todo-item");
        modelAndView.addObject("todoItem", todoItem);
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-todo-item";
        }
//        TodoItem item = new TodoItem();
//        item.setDescription(todoItem.getDescription());
        this.todoItemService.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("todo/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = this.todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo item id: " + id));
        model.addAttribute("todoItem", todoItem);
        return "edit-todo-item";
    }

    @PostMapping("todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "edit-todo-item";
        }
        this.todoItemService.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("todo/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = this.todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo item id: " + id));
        this.todoItemService.deleteById(id);
        return "redirect:/";
    }
}
