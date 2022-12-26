package com.decafandmac.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import java.time.Duration
import java.util.*

class ExactPartitionConsumer {
    private val logger = LoggerFactory.getLogger(ExactPartitionConsumer::class.java)
    private val bootstrapServer = "localhost:9092"
    private val groupId = "test-group"

    operator fun invoke(topicName: String) {
        val configs = Properties()

        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        configs[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.qualifiedName
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.qualifiedName

        val consumer = KafkaConsumer<String, String>(configs)
        consumer.assign(setOf(TopicPartition(topicName, 0)))
        consumer.subscribe(listOf(topicName))

        while(true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.forEach { logger.info("record : $it") }
        }
    }
}