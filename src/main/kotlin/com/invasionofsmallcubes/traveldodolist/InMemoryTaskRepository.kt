package com.invasionofsmallcubes.traveldodolist

class InMemoryTaskRepository : TaskRepository {

    private val database = mutableMapOf<String, MutableList<Task>>()

    override fun buildTasks(tripId: String) {
        database[tripId] = mutableListOf(
                Task("1", "baggage"),
                Task("2", "shampoo"),
                Task("3", "passport"),
                Task("4", "something"))
    }

    override fun delete(tripId: String, taskId: String) {
        database[tripId]?.removeIf { task -> task.id == taskId }
    }

    override fun getTasks(tripId: String) = database[tripId]!!
}