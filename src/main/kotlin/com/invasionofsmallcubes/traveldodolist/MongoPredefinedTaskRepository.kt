package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate

class MongoPredefinedTaskRepository(private val mongoTemplate: MongoTemplate) : PredefinedTaskRepository {

    override fun findById(id: String): List<Task> {
        val predefinedTaskDTO = mongoTemplate.findById(id, PredefinedTaskDTO::class.java)!!
        return predefinedTaskDTO.listOfTasks
    }

    override fun all(): List<PredefinedTaskDTO> {
        return mongoTemplate.findAll(PredefinedTaskDTO::class.java)
    }
}