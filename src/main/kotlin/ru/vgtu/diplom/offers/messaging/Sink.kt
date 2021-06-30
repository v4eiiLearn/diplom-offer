package ru.vgtu.diplom.offers.messaging

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Component

@Component
interface Sink {
    companion object {
        const val INPUT = "offer-in"
        const val OUTPUT = "offer-out"
    }

    @Input(INPUT)
    fun input() : SubscribableChannel

    @Output(OUTPUT)
    fun output() : MessageChannel
}