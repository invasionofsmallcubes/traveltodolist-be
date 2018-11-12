package com.invasionofsmallcubes.traveldodolist

interface TripRepository {
    fun save(trip: Trip) : String
    fun find(id: String): Trip?
}