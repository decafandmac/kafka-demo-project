package com.decafandmac.kafka.producer

import com.decafandmac.kafka.partitioner.CustomPartitioner
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory
import java.util.*

class CustomKafkaTemplate<K, V> {
    private val logger = LoggerFactory.getLogger(CustomKafkaTemplate::class.java)
    private val bootstrapServer = "localhost:9092"

    fun send(topic: String, partitionNo: Int? = null, key: K? = null, value: V, customPartitioner: Boolean? = false) {
        val configs = Properties()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.qualifiedName
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.qualifiedName
//        configs[ProducerConfig.ACKS_CONFIG] = "0"

        if(customPartitioner == true) configs[ProducerConfig.PARTITIONER_CLASS_CONFIG] = CustomPartitioner::class.qualifiedName

        val producer = KafkaProducer<K?, V>(configs)
        val record = ProducerRecord<K?, V>(topic, key, value)

        logger.info("{}", record)

        try {
            val recordMetadata = producer.send(record).get()
            logger.info(recordMetadata.toString())
        } catch (e: Exception) {
            logger.error(e.message, e)
        } finally {
            producer.flush()
            producer.close()
        }
    }
}