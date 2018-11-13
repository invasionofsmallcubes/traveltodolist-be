package com.invasionofsmallcubes.traveldodolist

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Configuration {

    @Bean
    fun tripRepository(): TripRepository {
        return InMemoryTripRepository()
    }

}