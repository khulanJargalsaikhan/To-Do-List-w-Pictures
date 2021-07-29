package com.example.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {
    public static long idCounter = 0;

    ArrayList<ToDo> lists = new ArrayList<>();

    @GetMapping("/add")
    public String loadAdd(Model model){
        ToDo newTodo = new ToDo();
        idSetter(newTodo);
        model.addAttribute("todo", newTodo);
        return "add";
    }

    @PostMapping("/lists")
    public String loadList(@ModelAttribute ToDo todo, Model model){
        lists.add(todo);
        model.addAttribute("lists", lists);
        return "lists";
    }

    @RequestMapping("/lists")
    public String loadAllList(Model model){
        model.addAttribute("lists", lists);
        return "lists";
    }

    static void idSetter(ToDo todo){
        idCounter += 1;
        todo.setId(idCounter);
    }

    @RequestMapping("/update/{id}")
    public String updateTodolist(@PathVariable("id") long id, Model model){
        for (ToDo todo: lists){
            if(id == todo.getId()){
                model.addAttribute("todo", todo);
            }
        }
        return "add";
    }
}
