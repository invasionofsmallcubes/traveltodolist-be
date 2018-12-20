package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document

class MongoTaskRepository(private val mongoTemplate: MongoTemplate, private val tripRepository: TripRepository) : TaskRepository {

    private val predefinedTasks = mapOf(
            "fun" to listOf(Task("kayak", "Kayak desc"),
                    Task("something-fun", "Something Fun")),
            "work" to listOf(Task("laptop", "Laptop desc"),
                    Task("pen", "Pen Desk")),
            "default" to listOf(Task("1", "baggage"),
                    Task("2", "shampoo"),
                    Task("3", "passport"),
                    Task("4", "something")
            ))

    @Document(collection = "tasks")
    private class TaskDTO(@Id val tripId: String, val listOfTasks: MutableList<Task>)

    override fun buildTasks(tripId: String) {
        val trip = tripRepository.find(tripId)
        val taskList = mutableListOf<Task>()
        trip?.options?.forEach { option -> taskList.addAll(predefinedTasks[option]!!) }
        taskList.addAll(predefinedTasks["default"]!!)
        mongoTemplate.save(TaskDTO(tripId, taskList))
    }

    override fun getTasks(tripId: String): List<Task> {
        return mongoTemplate.findById(tripId, TaskDTO::class.java)!!.listOfTasks
    }

    override fun delete(tripId: String, taskId: String) {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        tasks.listOfTasks.removeIf { task -> task.id != taskId }
        mongoTemplate.save(tasks)
    }

    override fun add(tripId: String, description: TaskDescription): Task {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        val newTask = Task(description.slug(), description.description)
        tasks.listOfTasks.add(newTask)
        mongoTemplate.save(tasks)
        return newTask
    }
}