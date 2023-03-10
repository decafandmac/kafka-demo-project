package com.decafandmac.kafka

import com.decafandmac.kafka.join.KStreamJoinKTable
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
//	val aSyncCommitConsumer = ASyncCommitConsumer()
//	aSyncCommitConsumer("test")
//	val rebalanceConsumer = RebalanceConsumer()
//	rebalanceConsumer("test")
//	val streamsFilter = StreamsFilter()
//	streamsFilter()
	val kStreamJoinKTable = KStreamJoinKTable()
	kStreamJoinKTable()
}
