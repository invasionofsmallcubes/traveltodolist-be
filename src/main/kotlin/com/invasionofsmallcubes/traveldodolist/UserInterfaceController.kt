package com.invasionofsmallcubes.traveldodolist

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UserInterfaceController {
    @RequestMapping("/")
    fun index(): String {
        return "index"
    }
}