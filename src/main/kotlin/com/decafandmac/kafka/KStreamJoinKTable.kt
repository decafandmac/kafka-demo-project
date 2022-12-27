package com.decafandmac.kafka

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import java.util.*

class KStreamJoinKTable {
    private val applicationName = "order-join-application"
    private val bootstrapServer = "localhost:9092"
    private val topicAddressTable = "address"
    private val topicOrderStream = "order"
    private val orderJoinStream = "order-join"

    operator fun invoke() {
        val props = Properties()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = applicationName
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String()::class.java
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String()::class.java

        val builder = StreamsBuilder()
        val addressTable = builder.table<String, String>(topicAddressTable)
        val orderStream = builder.stream<String, String>(topicOrderStream)

        orderStream.join(addressTable) { order: String, address: String -> "$order send to $address" }.to(orderJoinStream)

        val kafkaStreams = KafkaStreams(builder.build(), props)
        kafkaStreams.start()
    }
}