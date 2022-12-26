package com.decafandmac.kafka.listner

import com.decafandmac.kafka.consumer.SyncCommitConsumer
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition
import org.slf4j.LoggerFactory

class RebalanceListener : ConsumerRebalanceListener {
    private val logger = LoggerFactory.getLogger(RebalanceListener::class.java)
    override fun onPartitionsRevoked(partitions: MutableCollection<TopicPartition>?) {
        logger.warn("Partitions are revoked : ${partitions.toString()}")
    }

    override fun onPartitionsAssigned(partitions: MutableCollection<TopicPartition>?) {
        logger.warn("Partitions are assigned : ${partitions.toString()}")
    }
}