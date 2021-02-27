package com.example.testfalabella.ui.home

import com.example.data.repository.IndicatorsRepository
import com.example.usecases.indicators.GetListIndicatorsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class HomeFragmentModule() {

    @Provides
    fun homeViewModelProvider(getListIndicatorsUseCase: GetListIndicatorsUseCase): HomeViewModel {
        return HomeViewModel(getListIndicatorsUseCase)
    }

    @Provides
    fun getListIndicators(indicatorsRepository: IndicatorsRepository) = GetListIndicatorsUseCase(indicatorsRepository)

}

@Subcomponent(modules = [HomeFragmentModule::class])
interface HomeFragmentComponent {
    val homeViewModel: HomeViewModel
}