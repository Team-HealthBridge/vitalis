package com.healthbridge.vitalis.feature_records.presentation.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.healthbridge.vitalis.util.application.HiltApplication

object RecordsViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RecordsViewModel(
                vitalsRepository = recordsApplication().appContainer.vitalsRepository,
                healthRecordRepository = recordsApplication().appContainer.healthRecordRepository,
                measurementRepository = recordsApplication().appContainer.measurementRepository
            )
        }
    }
}

fun CreationExtras.recordsApplication(): HiltApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HiltApplication)