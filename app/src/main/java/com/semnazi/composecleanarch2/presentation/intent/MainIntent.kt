package com.semnazi.composecleanarch2.presentation.intent

sealed class MainIntent {
    data object LoadUsers : MainIntent()
}