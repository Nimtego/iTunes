package com.nimtego.plectrum.presentation.ui.massage

data class SystemMessage(
        val text: String,
        val type: SystemMessageType = SystemMessageType.ALERT
)