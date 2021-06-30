package ru.vgtu.diplom.offers.controller

import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vgtu.diplom.app.model.Decision
import ru.vgtu.diplom.common.logging.Loggable
import ru.vgtu.diplom.offers.service.DecisionService

@RestController
class OfferController(
    private val decisionService: DecisionService
) {

    companion object : Loggable

    @GetMapping("/decision/{clientId}/single")
    suspend fun singleOffer(@PathVariable clientId: String): ResponseEntity<Decision> =
        try {
            val decision =
                decisionService.getSingleDecisionByClientId(clientId).awaitFirstOrNull() ?: throw Exception("not found")
            ResponseEntity.ok(decision)
        } catch (ex: Exception) {
            logger.error("decision not found clientId: $clientId", ex)
            ResponseEntity.notFound().build()
        }

    @GetMapping("/decision/{appId}")
    suspend fun singleOfferByAppId(@PathVariable appId: String) =
        try {
            val decision = decisionService.getDecisionByAppId(appId).awaitFirstOrNull() ?: throw Exception("not found")
            ResponseEntity.ok(decision)
        } catch (ex: Exception) {
            logger.error("decision not found appId: $appId", ex)
            ResponseEntity.notFound().build()
        }

    @GetMapping("/decision/{clientId}/batch")
    suspend fun batchOffer(@PathVariable clientId: String) =
        try {
            val decisions =
                decisionService.getBatchDecisionByClientId(clientId).collectList().awaitFirstOrNull()
                    ?: throw Exception("not found")
            if (decisions.isEmpty())
                throw Exception("not found")
            ResponseEntity.ok(decisions)
        } catch (ex: Exception) {
            logger.error("no one decision not found clientId: $clientId")
            ResponseEntity.notFound().build()
        }


}