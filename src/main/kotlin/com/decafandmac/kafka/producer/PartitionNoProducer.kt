package com.decafandmac.kafka.producer

class PartitionNoProducer(
        private val topic: String,
        private val partitionNo: Int,
        private val customProducerTemplate: CustomProducerTemplate<String?, String>
) {
    fun send(key: String, value: String) {
        customProducerTemplate.send(topic, partitionNo, key, value)
    }
}