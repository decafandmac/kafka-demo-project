package com.decafandmac.kafka.connector

import org.apache.kafka.connect.data.Schema
import org.apache.kafka.connect.errors.ConnectException
import org.apache.kafka.connect.source.SourceRecord
import org.apache.kafka.connect.source.SourceTask
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

class SingleFileSourceTask : SourceTask() {
    private val logger = LoggerFactory.getLogger(SingleFileSourceTask::class.java)
    val fileNameField = "filename"
    val positionField = "position"
    private var fileNamePartition: Map<String, String?>? = null
    private var offset: Map<String, Any>? = null
    private var topic: String? = null
    private var file: String? = null
    private var position: Long = -1
    override fun version(): String {
        return "1.0"
    }

    override fun start(props: Map<String, String>) {
        try {
            // Init variables
            val config = SingleFileSourceConnectorConfig(props)
            topic = config.getString(SingleFileSourceConnectorConfig.topicName)
            file = config.getString(SingleFileSourceConnectorConfig.dirFileName)
            fileNamePartition = Collections.singletonMap(fileNameField, file)
            offset = context.offsetStorageReader().offset(fileNamePartition)

            // Get file offset from offsetStorageReader
            if (offset != null) {
                val lastReadFileOffset = offset!![positionField]
                if (lastReadFileOffset != null) {
                    position = lastReadFileOffset as Long
                }
            } else {
                position = 0
            }
        } catch (e: Exception) {
            throw ConnectException(e.message, e)
        }
    }

    override fun poll(): List<SourceRecord> {
        val results: MutableList<SourceRecord> = ArrayList()
        return try {
            Thread.sleep(1000)
            val lines = getLines(position)
            if (lines.size > 0) {
                lines.forEach(Consumer { line: String? ->
                    val sourceOffset = Collections.singletonMap(positionField, ++position)
                    val sourceRecord = SourceRecord(fileNamePartition, sourceOffset, topic, Schema.STRING_SCHEMA, line)
                    results.add(sourceRecord)
                })
            }
            results
        } catch (e: Exception) {
            logger.error(e.message, e)
            throw ConnectException(e.message, e)
        }
    }

    @Throws(Exception::class)
    private fun getLines(readLine: Long): List<String> {
        val reader = Files.newBufferedReader(Paths.get(file))
        return reader.lines().skip(readLine).collect(Collectors.toList())
    }

    override fun stop() {}
}