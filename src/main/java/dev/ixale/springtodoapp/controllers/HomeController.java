package dev.ixale.springtodoapp.controllers;

import dev.ixale.springtodoapp.services.TodoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final TodoItemService todoItemService;

    public HomeController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", this.todoItemService.getAll());
        return modelAndView;
    }
}
