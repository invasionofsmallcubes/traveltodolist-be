package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate

class MongoTaskRepository(private val mongoTemplate: MongoTemplate, private val tripRepository: TripRepository) : TaskRepository {
    private val predefinedTasks = mapOf(
            "fun" to listOf(Task("kayak", "Kayak desc", false),
                    Task("something-fun", "Something Fun", false)),
            "work" to listOf(Task("laptop", "Laptop desc", false),
                    Task("pen", "Pen Desk", false)),
            "default" to listOf(Task("1", "baggage", false),
                    Task("2", "shampoo", false),
                    Task("3", "passport", false),
                    Task("4", "something", false)
            ))

    override fun buildTasks(tripId: String) {
        val trip = tripRepository.find(tripId)
        val taskList = mutableListOf<Task>()
        trip?.options?.forEach { option -> taskList.addAll(predefinedTasks[option.toLowerCase()]!!) }
        taskList.addAll(predefinedTasks["default"]!!)
        mongoTemplate.save(TaskDTO(tripId, taskList))
    }

    override fun getTasks(tripId: String): List<Task> {
        return mongoTemplate.findById(tripId, TaskDTO::class.java)!!.listOfTasks
    }

    override fun delete(tripId: String, taskId: String) {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        tasks.listOfTasks.removeIf { task -> task.id == taskId }
        mongoTemplate.save(tasks)
    }

    override fun add(tripId: String, description: TaskDescription): Task {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        val newTask = Task(description.slug(), description.description, false)
        tasks.listOfTasks.add(newTask)
        mongoTemplate.save(tasks)
        return newTask
    }

    override fun done(tripId: String, taskId: String) {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        val task = tasks.listOfTasks.first { task -> task.id == taskId }
        task.done = true
        mongoTemplate.save(tasks)
    }

    override fun undone(tripId: String, taskId: String) {
        val tasks = mongoTemplate.findById(tripId, TaskDTO::class.java)!!
        val task = tasks.listOfTasks.first { task -> task.id == taskId }
        task.done = false
        mongoTemplate.save(tasks)
    }
}