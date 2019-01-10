package com.invasionofsmallcubes.traveldodolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
class Configuration {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @Bean
    fun tripRepository(): TripRepository {
        return MongoTripRepository(mongoTemplate)
    }

    @Bean
    fun predefinedTaskRepository(): PredefinedTaskRepository {
        return MongoPredefinedTaskRepository(mongoTemplate)
    }

    @Bean
    fun taskRepository(tripRepository: TripRepository, predefinedTaskRepository: PredefinedTaskRepository): TaskRepository {
        return MongoTaskRepository(mongoTemplate, tripRepository, predefinedTaskRepository)
    }

}