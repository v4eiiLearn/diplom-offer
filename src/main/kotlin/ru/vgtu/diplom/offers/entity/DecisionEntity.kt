package ru.vgtu.diplom.offers.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.vgtu.diplom.app.extensions.ApplicationStatus
import ru.vgtu.diplom.app.model.Decision

@Document(value = "decision")
class DecisionEntity(
    @Id
    var appId: String?,
    val clientId: String,
    val status: ApplicationStatus,
    val decision: Decision
)

