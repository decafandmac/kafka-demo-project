package com.decafandmac.kafka.producer

class SimpleProducer(
        private val topic: String,
        private val customKafkaTemplate: CustomKafkaTemplate<String?, String>
) {

    fun send(value: String) {
        customKafkaTemplate.send(topic, partitionNo = null, key = null, value)
    }
}