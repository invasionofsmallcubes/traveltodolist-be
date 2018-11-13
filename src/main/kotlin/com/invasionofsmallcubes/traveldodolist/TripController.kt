package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/trips")
class TripController(@Autowired private val tripRepository: TripRepository) {

    @PostMapping
    @ResponseBody fun createTrip(@RequestBody trip: Trip): String {
        return tripRepository.save(trip)
    }

    @GetMapping("/{id}")
    fun getTrip(@PathVariable("id") id: String) : Trip {
        return tripRepository.find(id)!!
    }

}