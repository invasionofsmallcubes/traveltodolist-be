package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class MongoPredefinedTaskRepository(private val mongoTemplate: MongoTemplate) : PredefinedTaskRepository {

    override fun findById(id: String): List<Task> {
        val predefinedTaskDTO = mongoTemplate.findById(id, PredefinedTaskDTO::class.java)!!
        return predefinedTaskDTO.listOfTasks
    }

    override fun all(): List<PredefinedTaskDTO> {
        val query = Query()
        query.addCriteria(Criteria.where("id").ne("default"))
        return mongoTemplate.find(query, PredefinedTaskDTO::class.java)
    }
}