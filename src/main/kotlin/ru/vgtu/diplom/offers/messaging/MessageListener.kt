package ru.vgtu.diplom.offers.messaging

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import ru.vgtu.diplom.app.model.Decision
import ru.vgtu.diplom.common.logging.Loggable
import ru.vgtu.diplom.offers.service.DecisionService

@Component
@EnableBinding(Sink::class)
class MessageListener(
    private val decisionService: DecisionService
) {

    companion object : Loggable

    @StreamListener(target = Sink.INPUT)
    fun on(
        message: Message<Decision>,
        @Header(KafkaHeaders.CORRELATION_ID) appId: String,
        @Header("M-Client-Id") clientId: String
    ) {
        logger.info("read message with appId: ${message.payload.applicationId}, clientId: $clientId")
        decisionService.saveDecision(message.payload, clientId)
    }

}