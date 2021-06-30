package ru.vgtu.diplom.offers.service

import org.springframework.stereotype.Service
import ru.vgtu.diplom.app.model.Decision
import ru.vgtu.diplom.common.logging.Loggable
import ru.vgtu.diplom.offers.extension.toEntity
import ru.vgtu.diplom.offers.repository.DecisionReactiveRepository

@Service
class DecisionService(
    private val decisionReactiveRepository: DecisionReactiveRepository
) {

    companion object : Loggable

    fun saveDecision(decision: Decision, clientId: String) {
        decisionReactiveRepository.save(decision.toEntity(clientId)).subscribe()
        logger.info("decision for clientId: $clientId saved")
    }

    fun getSingleDecisionByClientId(clientId: String) =
        decisionReactiveRepository.findFirstByClientId(clientId).map { it.decision }

    fun getBatchDecisionByClientId(clientId: String) =
        decisionReactiveRepository.findAllByClientId(clientId).map { it.decision }

    fun getDecisionByAppId(appId: String) = decisionReactiveRepository.findById(appId).map { it.decision }
}