package com.decafandmac.kafka.connector

import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.common.config.ConfigException
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.errors.ConnectException
import org.apache.kafka.connect.source.SourceConnector
import org.slf4j.LoggerFactory

class SingleFileSourceConnector: SourceConnector() {
    private val logger = LoggerFactory.getLogger(SingleFileSourceConnector::class.java)
    private var configProperties: Map<String, String>? = null

    override fun version(): String = "1.0"

    override fun start(props: MutableMap<String, String>?) {
        configProperties = props

        try{
            SingleFileSourceConnectorConfig(configProperties)
        } catch (e: ConfigException) {
            throw ConnectException(e.message, e)
        }
    }

    override fun taskClass(): Class<out Task> = SingleFileSourceTask::class.java

    override fun taskConfigs(maxTasks: Int): List<Map<String, String>> {
        val taskConfigs = ArrayList<Map<String, String>>()
        val taskProps = HashMap<String, String>()
        taskProps.putAll(configProperties!!)
        for(i in 0 until maxTasks) {
            taskConfigs.add(taskProps)
        }

        return taskConfigs
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun config(): ConfigDef = SingleFileSourceConnectorConfig.config
}