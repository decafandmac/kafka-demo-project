package com.decafandmac.kafka.producer

class PartitionNoProducer(
        private val topic: String,
        private val partitionNo: Int,
        private val customKafkaTemplate: CustomKafkaTemplate<String?, String>
) {
    fun send(key: String, value: String) {
        customKafkaTemplate.send(topic, partitionNo, key, value)
    }
}