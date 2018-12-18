package com.invasionofsmallcubes.traveldodolist

import java.util.concurrent.atomic.AtomicInteger

class InMemoryTripRepository : TripRepository {
    private val counter = AtomicInteger(0)

    private val database = mutableMapOf<String, Trip>()
    override fun save(trip: Trip): String {
        val index = counter.incrementAndGet().toString()
        trip.id = index
        database[index] = trip
        return index
    }

    override fun find(id: String) : Trip? {
        return database[id]
    }

    override fun findByOwner(owner: String): List<Trip> {
        return database.
                filter { mapEntry -> mapEntry.value.owner == owner}.
                map { entry -> entry.value }
    }
}