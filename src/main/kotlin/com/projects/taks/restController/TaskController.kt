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

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: String): Task =
        tasks.find { it.id.equals(id, true) } ?: throw TaskNotFoundException("Task con id $id no encontrada")

    @GetMapping("/filter")
    fun getTaskByCategory(@RequestParam category: String): List<Task> =
        tasks.filter { it.category.name.equals(category, true) }
            .ifEmpty { throw TaskNotFoundException("tasks con categoria $category no encontrada") }


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