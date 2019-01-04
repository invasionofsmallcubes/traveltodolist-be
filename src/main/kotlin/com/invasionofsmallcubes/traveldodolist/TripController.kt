package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*
import org.springframework.http.HttpHeaders

@Controller
@RequestMapping("/trips")
class TripController(@Autowired private val tripRepository: TripRepository,
                     @Autowired private val taskRepository: TaskRepository) {

    @PostMapping
    @ResponseBody
    fun createTrip(@RequestBody tripRequest: TripRequest, @CookieValue("owner") owner: String?): String {
        if (owner != null) {
            val trip = Trip(departureDate = tripRequest.departureDate, arrivalAirport = tripRequest.arrivalAirport
                    , departureAirport = tripRequest.departureAirport, arrivalDate = tripRequest.arrivalDate, owner = owner,
                    options = tripRequest.options)
            val id = tripRepository.save(trip)
            taskRepository.buildTasks(id)
            return id
        } else throw RuntimeException()
    }

    @GetMapping("/")
    @ResponseBody
    fun getTrips(@CookieValue("owner") owner: String?): ResponseEntity<List<Trip>> {
        return if (owner != null) {
            println("owner found $owner")
            ResponseEntity.status(HttpStatus.OK).body(tripRepository.findByOwner(owner))
        } else {

            println("owner not found")

            val newOwner = UUID.randomUUID().toString()
            val responseHeaders = HttpHeaders()
            responseHeaders.set("Set-Cookie",
                    "owner=$newOwner; Expires=Wed, 21 Oct 2025 07:28:00 GMT; HttpOnly; Secure; Path=/; Domain=traveltodolist.herokuapp.com;")

            ResponseEntity.ok().headers(responseHeaders).body(tripRepository.findByOwner(newOwner))

        }
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

    @PostMapping("/{id}/tasks/")
    @ResponseBody
    fun addTask(@PathVariable("id") id: String, @RequestBody description: TaskDescription): Task {
        return taskRepository.add(id, description)
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    fun deleteTask(@PathVariable("id") id: String, @PathVariable("taskId") taskId: String): ResponseEntity<Void> {
        taskRepository.delete(id, taskId)
        return ResponseEntity(HttpStatus.OK)
    }

    @PutMapping("/{id}/tasks/{taskId}")
    fun doneTask(@PathVariable("id") id: String, @PathVariable("taskId") taskId: String, @RequestBody taskEdit: TaskEdit): ResponseEntity<Void> {
        if (taskEdit.done) {
            taskRepository.done(id, taskId)
        } else {
            taskRepository.undone(id, taskId)
        }
        return ResponseEntity(HttpStatus.OK)
    }
}

data class TaskEdit(val done: Boolean)

data class TaskDescription(val description: String) {
    fun slug() = description.toLowerCase().replace(" ", "-")
}
