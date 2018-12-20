package com.invasionofsmallcubes.traveldodolist

import com.mongodb.MongoClient
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(properties = ["spring.data.mongodb.uri=mongodb://localhost:27018/test"])
class TravelToDoListApplicationTests {

	private lateinit var mongodExecutable: MongodExecutable
	private lateinit var mongoTemplate: MongoTemplate

	@Before
	fun setUp() {
		val ip = "localhost"
		val port = 27018
		val mongodConfig = MongodConfigBuilder().version(Version.Main.DEVELOPMENT)
				.net(Net(ip, port, Network.localhostIsIPv6()))
				.build()
		val starter = MongodStarter.getDefaultInstance()
		mongodExecutable = starter.prepare(mongodConfig)
		mongodExecutable.start()
		mongoTemplate = MongoTemplate(MongoClient(ip, port), "test")
	}


	@Test
	fun contextLoads() {
	}

}
