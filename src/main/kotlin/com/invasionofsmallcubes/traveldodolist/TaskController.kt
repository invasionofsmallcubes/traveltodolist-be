package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/tasks")
class TaskController(@Autowired private val predefinedTaskRepository: PredefinedTaskRepository) {

    @GetMapping
    fun all(): List<PredefinedTaskDTO> {
        return predefinedTaskRepository.all()
    }

}
