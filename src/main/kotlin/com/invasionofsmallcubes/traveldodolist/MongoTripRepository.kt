package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate

class MongoTripRepository(private val mongoTemplate: MongoTemplate) : TripRepository {
    override fun find(id: String): Trip? {
        return mongoTemplate.findById(id, Trip::class.java)
    }

    override fun save(trip: Trip): String {
        mongoTemplate.save(trip)
        return trip.id!!
    }
}
