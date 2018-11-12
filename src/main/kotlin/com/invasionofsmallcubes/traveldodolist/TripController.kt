package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/trip")
class TripController(@Autowired private val tripRepository: TripRepository) {

    @PostMapping
    fun createTrip(trip: Trip): String {
        return tripRepository.save(trip)
    }

}