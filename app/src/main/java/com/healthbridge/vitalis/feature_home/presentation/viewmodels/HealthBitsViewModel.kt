package com.healthbridge.vitalis.feature_home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_home.data.repository.HealthBitsRepository
import kotlinx.coroutines.launch

class HealthBitsViewModel  constructor(
    private val healthBitsRepository: HealthBitsRepository
): ViewModel() {

    val healthBits = healthBitsRepository.healthBits
    val event = healthBitsRepository.event


    init{
        viewModelScope.launch {
            healthBitsRepository.getHealthBits()
            healthBitsRepository.getEvents()
        }
    }

}
