package com.invasionofsmallcubes.traveldodolist

interface TaskRepository {
    fun buildTasks(tripId: String)
    fun getTasks(id: String): List<Task>
    fun delete(id: String, taskId: String)
}