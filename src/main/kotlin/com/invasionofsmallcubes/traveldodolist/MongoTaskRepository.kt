package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate

class MongoTaskRepository(private val mongoTemplate: MongoTemplate, private val tripRepository: TripRepository, val predefinedTaskRepository: PredefinedTaskRepository) : TaskRepository {
    override fun buildTasks(tripId: String) {
        val trip = tripRepository.find(tripId)
        val taskList = mutableListOf<Task>()
        trip?.options?.forEach { option -> taskList.addAll(predefinedTaskRepository.findById(option.toLowerCase())) }
        taskList.addAll(predefinedTaskRepository.findById("default"))
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