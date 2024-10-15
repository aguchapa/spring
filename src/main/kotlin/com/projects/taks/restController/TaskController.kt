package com.projects.taks.restController


import com.projects.taks.exception.TaskNotFoundException
import com.projects.taks.model.Task
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    private val tasks = mutableListOf<Task>()

    @GetMapping
    fun getAllTask(): MutableList<Task> = tasks

    @PostMapping
    fun addTask(@RequestBody task: Task): ResponseEntity<Task> {
        tasks.add(task)

        return ResponseEntity(task, HttpStatus.CREATED)
    }

    @GetMapping("/category/{category}")
    fun getTaskByCategory(@PathVariable category: String): List<Task> {

        val list = tasks.filter { it.category.name.equals(category, true) }
        if (list.isEmpty()) throw TaskNotFoundException("$category no encontrada")

        return list
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: String, @RequestBody updatedTask: Task
    ): Task {
        val task = tasks.find { it.id == id } ?: throw TaskNotFoundException("Task con id $id no encontrada")
        task.let {
            it.title = updatedTask.title
            it.description = updatedTask.description
            it.state = updatedTask.state
            it.category = updatedTask.category
        }
        return task
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: String) {
        if (!tasks.removeIf { it.id == id }) throw TaskNotFoundException("Task con id $id no encontrada")
    }
}