package com.invasionofsmallcubes.traveldodolist

import com.mongodb.MongoClient
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.junit.Before
import org.springframework.data.mongodb.core.MongoTemplate
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test


class MongoDBTest {

    private lateinit var mongodExecutable: MongodExecutable
    private lateinit var mongoTemplate: MongoTemplate

    @Before
    fun setUp() {
        val ip = "localhost"
        val port = 27017
        val mongodConfig = MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(Net(ip, port, Network.localhostIsIPv6()))
                .build()
        val starter = MongodStarter.getDefaultInstance()
        mongodExecutable = starter.prepare(mongodConfig)
        mongodExecutable.start()
        mongoTemplate = MongoTemplate(MongoClient(ip, port), "test")
    }

    @Test
    @Throws(Exception::class)
    fun testCanSaveATrip() {
        val trip = Trip("departure", "arrival", "2017-06-10", "2017-03-12")

        val tripRepository = MongoTripRepository(mongoTemplate)
        val id = tripRepository.save(trip)

        val value = tripRepository.find(id)
        assertThat(value?.arrivalAirport, `is`("arrival"))
    }
}

