package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks")
data class TaskDTO(@Id val tripId: String, val listOfTasks: MutableList<Task>)