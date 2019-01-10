package com.invasionofsmallcubes.traveldodolist

data class Task(val taskId: String, val description: String, var done: Boolean = false)