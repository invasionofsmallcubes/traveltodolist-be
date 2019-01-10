package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "predefinedtasks")
data class PredefinedTaskDTO(@Id val taskId: String, val description: String, val tasks: List<Task>)

//    private val predefinedTasks = mapOf(
//            "fun" to listOf(Task("kayak", "Kayak desc", false),
//                    Task("something-fun", "Something Fun", false)),
//            "work" to listOf(Task("laptop", "Laptop desc", false),
//                    Task("pen", "Pen Desk", false)),
//            "default" to listOf(Task("1", "baggage", false),
//                    Task("2", "shampoo", false),
//                    Task("3", "passport", false),
//                    Task("4", "something", false)
//            ))