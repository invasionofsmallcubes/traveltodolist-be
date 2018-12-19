package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class MongoTripRepository(private val mongoTemplate: MongoTemplate) : TripRepository {
    override fun findByOwner(owner: String): List<Trip> {
        val query = Query()
        query.addCriteria(Criteria.where("owner").`is`(owner))
        return mongoTemplate.find(query, Trip::class.java)
    }

    override fun find(id: String): Trip? {
        return mongoTemplate.findById(id, Trip::class.java)
    }

    override fun save(trip: Trip): String {
        val insertedTrip = mongoTemplate.insert(trip)
        return insertedTrip.id!!
    }
}
