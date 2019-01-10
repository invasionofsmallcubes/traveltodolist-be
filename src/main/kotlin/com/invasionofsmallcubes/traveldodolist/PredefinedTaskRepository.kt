package com.invasionofsmallcubes.traveldodolist

interface PredefinedTaskRepository {
    fun all(): List<PredefinedTaskDTO>
    fun findById(id: String): List<Task>
}