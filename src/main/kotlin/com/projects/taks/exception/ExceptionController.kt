package com.projects.taks.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

class ExceptionController {

    @ControllerAdvice
    class ExceptionController {

        @ExceptionHandler(TaskNotFoundException::class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        fun handleBookNotFound(ex: TaskNotFoundException): ResponseEntity<String> {
            return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }

    }

}