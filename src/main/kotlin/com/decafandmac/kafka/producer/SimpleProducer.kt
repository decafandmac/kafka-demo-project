package com.decafandmac.kafka.producer

class SimpleProducer(
        private val topic: String,
        private val customProducerTemplate: CustomProducerTemplate<String?, String>
) {

    fun send(value: String) {
        customProducerTemplate.send(topic, partitionNo = null, key = null, value)
    }
}