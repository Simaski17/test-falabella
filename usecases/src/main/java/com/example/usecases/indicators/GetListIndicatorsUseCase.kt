package com.example.usecases.indicators

import com.example.data.repository.IndicatorsRepository
import com.example.domain.indicators.Indicators

class GetListIndicatorsUseCase(private val indicatorsRepository: IndicatorsRepository) {

    suspend fun invoke(): List<Indicators> = indicatorsRepository.getListIndicators()

}