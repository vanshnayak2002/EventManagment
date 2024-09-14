package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> Todos = new ArrayList<>();
	
	private static int TodosCount = 0;
	
	static {
		
		Todos.add(new Todo(++TodosCount, "in28minutes","Get AWS Certified", 
							LocalDate.now().plusYears(10), false ));
		Todos.add(new Todo(++TodosCount, "in28minutes","Learn DevOps", 
				LocalDate.now().plusYears(11), false ));
		Todos.add(new Todo(++TodosCount, "in28minutes","Learn Full Stack Development", 
				LocalDate.now().plusYears(12), false ));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				Todo -> Todo.getUsername().equalsIgnoreCase(username);
		return Todos.stream().filter(predicate).toList();
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo Todo = new Todo(++TodosCount,username,description,targetDate,done);
		Todos.add(Todo);
		return Todo;
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = Todo -> Todo.getId() == id;
		Todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = Todo -> Todo.getId() == id;
		Todo Todo = Todos.stream().filter(predicate).findFirst().get();
		return Todo;
	}

	public void updateTodo(Todo Todo) {
		deleteById(Todo.getId());
		Todos.add(Todo);
	}
}