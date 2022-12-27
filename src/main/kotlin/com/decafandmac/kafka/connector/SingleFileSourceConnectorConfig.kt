package com.decafandmac.kafka.connector

import org.apache.kafka.common.config.AbstractConfig
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.common.config.ConfigDef.Importance

class SingleFileSourceConnectorConfig(props: Map<String, String>?): AbstractConfig(config, props) {
    companion object {
        const val dirFileName = "file"
        private const val dirFileNameDefaultValue = "/tmp/kafka.txt"
        private const val dirFileNameDoc = "읽을 파일 경로와 이름"

        const val topicName = "topic"
        private const val topicDefaultValue = "test"
        private const val topicDoc = "보낼 토픽명"

        val config: ConfigDef = ConfigDef()
                .define(dirFileName, ConfigDef.Type.STRING, dirFileNameDefaultValue, Importance.HIGH, dirFileNameDoc)
                .define(topicName, ConfigDef.Type.STRING, topicDefaultValue, Importance.HIGH, topicDoc)
    }
}