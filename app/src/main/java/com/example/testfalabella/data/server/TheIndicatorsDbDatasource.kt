package com.example.testfalabella.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.indicators.Bitcoin
import com.example.domain.indicators.Indicators
import com.example.domain.indicators.Welcome

class TheIndicatorsDbDatasource(private val theIndicatorsDbService: TheIndicatorsDbService) : RemoteDataSource  {

    override suspend fun getListIndicators(): List<Indicators> {


        var arryList: ArrayList<Indicators> = arrayListOf<Indicators>()
        var arryListEnd: ArrayList<Indicators> = arrayListOf<Indicators>()

        val listIndicators = theIndicatorsDbService.getListIndicators().execute().body()!!

        for (prop in Welcome::class.members) {

            if (prop.returnType.toString() == "com.example.domain.indicators.Bitcoin") {

                val name = listIndicators.getThroughReflection<Bitcoin>(prop.name.toString())

                arryList.add(Indicators(codigo = name?.codigo.toString(), nombre = name?.nombre.toString(), unidadMedida = name?.unidad_medida.toString(),
                    fecha = name?.fecha.toString(), valor = name?.valor.toString()))
            }

        }

        arryList.forEach {
            if (it.codigo.toString() != "null") {
                arryListEnd.add(it)
            }
        }
        return arryListEnd

    }

    inline fun <reified T : Any> Any.getThroughReflection(propertyName: String): T? {
        val getterName = "get" + propertyName.capitalize()
        return try {
            javaClass.getMethod(getterName).invoke(this) as? T
        } catch (e: NoSuchMethodException) {
            null
        }
    }

}