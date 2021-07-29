package com.example.todolist;

import org.omg.CORBA.FieldNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
        //sortList(lists);
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

//    static ArrayList sortList(ArrayList lists){
//        Collections.sort(lists);
//        return lists;
//    }

    @RequestMapping("/list/{id}")
    public String loadDetail(@PathVariable("id") long id, Model model){
        for (ToDo todo: lists){
            if(id == todo.getId()){
                model.addAttribute("todo", todo);
            }
        }
        return "list";
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

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") long id, Model model){
        for (ToDo todo: lists){
            if(id == todo.getId()){
                //int todoIndex = lists.indexOf(todo);
                lists.remove(todo);
                break;
            }
        }
        return "delete";
    }



}
