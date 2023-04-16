package com.healthbridge.vitalis.feature_home.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_home.data.models.response.Content
import com.healthbridge.vitalis.feature_home.data.repository.HealthBitsRepository
import kotlinx.coroutines.launch

class HealthBitsViewModel  constructor(
    private val healthBitsRepository: HealthBitsRepository
): ViewModel() {

    val healthBits = healthBitsRepository.healthBits
    val event = healthBitsRepository.event
    val categories = healthBitsRepository.category
    val healthBitsByCategory: MutableState<List<Content>> = mutableStateOf(listOf())


    init{
        viewModelScope.launch {
            healthBitsRepository.getHealthBits()
            healthBitsRepository.getEvents()
            healthBitsRepository.getCategories()
        }
    }

    fun getHealthBitsByCategory(category: String) {
        viewModelScope.launch {
            healthBitsByCategory.value = healthBitsRepository.getHealthBitsByCategory(category)
        }
    }

}
