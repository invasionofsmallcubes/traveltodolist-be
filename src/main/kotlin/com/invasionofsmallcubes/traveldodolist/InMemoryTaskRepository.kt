package com.invasionofsmallcubes.traveldodolist

import java.util.concurrent.atomic.AtomicInteger

class InMemoryTaskRepository : TaskRepository {

    private val database = mutableMapOf<String, MutableList<Task>>()
    private val idCounter = AtomicInteger(4)

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

    override fun add(tripId: String, taskDescription: TaskDescription): Task {
        val tasks = database[tripId]!!
        val task = Task(idCounter.incrementAndGet().toString(), taskDescription.description)
        tasks.add(task)
        return task
    }
}