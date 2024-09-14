package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class TodoResource {
    
    private TodoService TodoService;
    
    public TodoResource(TodoService TodoService) {
        this.TodoService = TodoService;
    }
    
    @GetMapping("/users/{username}/Todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return TodoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/Todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
            @PathVariable int id) {
        return TodoService.findById(id);
    }

    @DeleteMapping("/users/{username}/Todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable int id) {
        TodoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/users/{username}/Todos/{id}")
    public Todo updateTodo(@PathVariable String username,
            @PathVariable int id, @RequestBody Todo Todo) {
        TodoService.updateTodo(Todo);
        return Todo;
    }

    @PostMapping("/users/{username}/Todos")
    public Todo createTodo(@PathVariable String username,
             @RequestBody Todo Todo) {
        Todo createdTodo = TodoService.addTodo(username, Todo.getDescription(), 
                Todo.getTargetDate(),Todo.isDone() );
        
        return createdTodo;
    }

}