package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.annotation.Id

data class TripRequest(@Id var id: String? = null, val departureAirport: String,
                       val arrivalAirport: String, val departureDate: String,
                       val arrivalDate: String)