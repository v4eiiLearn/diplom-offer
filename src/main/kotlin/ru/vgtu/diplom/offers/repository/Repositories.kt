package ru.vgtu.diplom.offers.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.vgtu.diplom.offers.entity.DecisionEntity

@Repository
interface DecisionReactiveRepository: ReactiveCrudRepository<DecisionEntity, String> {
    fun findFirstByClientId(clientId: String): Mono<DecisionEntity>

    fun findAllByClientId(clientId: String) : Flux<DecisionEntity>
}