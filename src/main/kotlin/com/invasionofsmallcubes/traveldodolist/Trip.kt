package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "trips")
data class Trip(@Id var id:String? = null, val departureAirport: String,
                val arrivalAirport: String, val departureDate: String,
                val arrivalDate: String, var owner: String)