package com.projects.taks.model

data class Task(val id: String, var title: String, var description: String, var state: State, var category: Category) {

}


enum class State {
    COMPLETE,
    INCOMPLETE
}

enum class Category {
    ADMIN,
    DESIGN,
    FUNCTIONAL,

}