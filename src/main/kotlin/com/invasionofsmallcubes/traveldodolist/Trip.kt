package com.invasionofsmallcubes.traveldodolist

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "trips")
data class Trip(val departureAirport: String, val arrivalAirport: String, val departureDate: String, val arrivalDate: String)