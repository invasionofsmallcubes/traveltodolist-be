package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@Controller
@RequestMapping("/trips")
class TripController(@Autowired private val tripRepository: TripRepository,
                     @Autowired private val taskRepository: TaskRepository) {

    @PostMapping
    @ResponseBody
    fun createTrip(@RequestBody trip: Trip): String {
        val id = tripRepository.save(trip)
        taskRepository.buildTasks(id)
        return id
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getTrip(@PathVariable("id") id: String): Trip {
        return tripRepository.find(id)!!
    }

    @GetMapping("/{id}/tasks")
    @ResponseBody
    fun getTasks(@PathVariable("id") id: String): List<Task> {
        return taskRepository.getTasks(id)
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    fun deleteTask(@PathVariable("id") id: String, @PathVariable("taskId") taskId: String): ResponseEntity<Void> {
        taskRepository.delete(id, taskId)
        return ResponseEntity(HttpStatus.OK)
    }
}