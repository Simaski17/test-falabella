package com.example.testfalabella.ui.detail

import com.example.data.repository.IndicatorsRepository
import com.example.usecases.indicators.FindIndicatorByCode
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class IndicatorDetailFragmentModule() {

    @Provides
    fun indicatorDetailViewModelProvider(findIndicatorByCode: FindIndicatorByCode): IndicatorDetailViewModel {
        return IndicatorDetailViewModel(findIndicatorByCode)
    }

    @Provides
    fun findIndicatorByCode(indicatorsRepository: IndicatorsRepository) = FindIndicatorByCode(indicatorsRepository)

}

@Subcomponent(modules = [IndicatorDetailFragmentModule::class])
interface IndicatorDetailFragmentComponent {
    val indicatorDetailViewModel: IndicatorDetailViewModel
}