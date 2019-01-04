package com.invasionofsmallcubes.traveldodolist

interface TaskRepository {
    fun buildTasks(tripId: String)
    fun getTasks(tripId: String): List<Task>
    fun delete(tripId: String, taskId: String)
    fun add(tripId: String, description: TaskDescription) : Task
    fun done(tripId: String, taskId: String)
    fun undone(tripId: String, taskId: String)
}