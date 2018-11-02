package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.MongoTemplate

class TripRepository(private val mongoTemplate: MongoTemplate) {
    fun save(trip: Trip) {
        mongoTemplate.save(trip)
    }
}
