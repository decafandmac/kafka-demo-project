package com.decafandmac.kafka.filter

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import java.util.Properties

class StreamsFilter {
    private val applicationName = "streams-filter-application"
    private val bootstrapServer = "localhost:9092"
    private val streamLog = "stream_log"
    private val streamLogFilter = "stream_log_filter"

    operator fun invoke() {
        val props = Properties()

        props[StreamsConfig.APPLICATION_ID_CONFIG] = applicationName
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String()::class.java
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String()::class.java

        val builder = StreamsBuilder()

        val stream = builder.stream<String, String>(streamLog)
        stream.filter{ _, value -> value.length > 5}.to(streamLogFilter)

        val kafkaStreams = KafkaStreams(builder.build(), props)
        kafkaStreams.start()
    }
}