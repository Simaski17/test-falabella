package com.example.testfalabella.ui.home

import com.example.data.repository.IndicatorsRepository
import com.example.usecases.indicators.FindIndicatorByCode
import com.example.usecases.indicators.GetListIndicatorsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class HomeFragmentModule() {

    @Provides
    fun homeViewModelProvider(getListIndicatorsUseCase: GetListIndicatorsUseCase, findIndicatorByCode: FindIndicatorByCode): HomeViewModel {
        return HomeViewModel(getListIndicatorsUseCase, findIndicatorByCode)
    }

    @Provides
    fun getListIndicators(indicatorsRepository: IndicatorsRepository) = GetListIndicatorsUseCase(indicatorsRepository)

    @Provides
    fun findIndicatorByCode(indicatorsRepository: IndicatorsRepository) = FindIndicatorByCode(indicatorsRepository)

}

@Subcomponent(modules = [HomeFragmentModule::class])
interface HomeFragmentComponent {
    val homeViewModel: HomeViewModel
}