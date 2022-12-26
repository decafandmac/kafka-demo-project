package com.decafandmac.kafka

import com.decafandmac.kafka.consumer.ASyncCommitConsumer
import com.decafandmac.kafka.consumer.SimpleConsumer
import com.decafandmac.kafka.producer.CustomProducerTemplate
import com.decafandmac.kafka.producer.SimpleProducer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaDemoProjectApplication

fun main(args: Array<String>) {
	runApplication<KafkaDemoProjectApplication>(*args)

//	val simpleProducer = SimpleProducer("test", CustomProducerTemplate())
//
//	simpleProducer.send("TestValue2")

//	val simpleConsumer = SimpleConsumer()
//	simpleConsumer("test")
	val aSyncCommitConsumer = ASyncCommitConsumer()
	aSyncCommitConsumer("test")
}
