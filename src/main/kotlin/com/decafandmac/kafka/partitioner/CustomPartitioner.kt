package com.decafandmac.kafka.partitioner

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.InvalidRecordException
import org.apache.kafka.common.utils.Utils

class CustomPartitioner: Partitioner {
    override fun configure(configs: MutableMap<String, *>?) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun partition(topic: String?, key: Any?, keyBytes: ByteArray?, value: Any?, valueBytes: ByteArray?, cluster: Cluster?): Int {
        key?.let { if(it as String == "Pangyo") 0 } ?: throw InvalidRecordException("Need message key")

        val partitions = cluster?.partitionsForTopic(topic)
        val numPartitions = partitions?.size ?: 1
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions
    }
}