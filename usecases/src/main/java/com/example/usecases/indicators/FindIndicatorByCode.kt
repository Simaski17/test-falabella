package com.example.usecases.indicators

import com.example.data.repository.IndicatorsRepository
import com.example.domain.indicators.Indicators

class FindIndicatorByCode (private val indicatorsRepository: IndicatorsRepository) {
    suspend fun invoke(code: String): List<Indicators> = indicatorsRepository.findIndicatorByCode(code)
}