package com.decafandmac.kafka.producer

class KeyValueProducer(
        private val topic: String,
        private val customKafkaTemplate: CustomKafkaTemplate<String?, String>
) {
    fun send(key: String, value: String) {
        customKafkaTemplate.send(topic, partitionNo = null, key, value)
    }
}