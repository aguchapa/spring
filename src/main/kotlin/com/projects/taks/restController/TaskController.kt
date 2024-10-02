package com.projects.taks.restController


import com.projects.taks.model.Category
import com.projects.taks.model.State
import com.projects.taks.model.Task
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    private val tasks = mutableListOf<Task>()

    @GetMapping
    fun getAllTask(): List<Task> {
        println(tasks)
        return tasks

    }
    @PostMapping
    fun addTask(@RequestBody task: Task) {
        tasks.add(task)
    }

    @GetMapping("/category/{category}")
    fun getTaskByCategory(@PathVariable  category: String): List<Task> {
        return tasks.filter { it.category.name.equals(category, true) }
        }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: String, @RequestBody updatedTask:
    UpdateTask) {
        val task = tasks.find { it.id == id }
        task?.let {
            it.title = updatedTask.title
            it.description = updatedTask.description
            it.state = State.valueOf(updatedTask.state)
            it.category= Category.valueOf(updatedTask.category)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: String) {
        tasks.removeIf { it.id == id }
    }

    data class UpdateTask(val title: String, val description: String, val state: String, val category: String)



}