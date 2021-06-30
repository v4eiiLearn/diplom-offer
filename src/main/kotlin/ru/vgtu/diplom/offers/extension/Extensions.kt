package ru.vgtu.diplom.offers.extension

import ru.vgtu.diplom.app.extensions.ApplicationStatus
import ru.vgtu.diplom.app.model.Decision
import ru.vgtu.diplom.offers.entity.DecisionEntity


fun Decision.toEntity(clientId: String) = DecisionEntity(
    applicationId,
    clientId,
    ApplicationStatus.valueOf(statusCode!!),
    this
)