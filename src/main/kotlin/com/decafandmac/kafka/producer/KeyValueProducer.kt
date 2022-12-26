package com.decafandmac.kafka.producer

class KeyValueProducer(
        private val topic: String,
        private val customProducerTemplate: CustomProducerTemplate<String?, String>
) {
    fun send(key: String, value: String) {
        customProducerTemplate.send(topic, partitionNo = null, key, value)
    }
}