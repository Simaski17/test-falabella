package com.example.domain.indicators

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Welcome (
    val version: String,
    val autor: String,
    val fecha: String,
    val uf: Bitcoin,
    val ivp: Bitcoin,
    val dolar: Bitcoin,

    @SerialName("dolar_intercambio")
    val dolarIntercambio: Bitcoin,

    val euro: Bitcoin,
    val ipc: Bitcoin,
    val utm: Bitcoin,
    val imacec: Bitcoin,
    val tpm: Bitcoin,

    @SerialName("libra_cobre")
    val libraCobre: Bitcoin,

    @SerialName("tasa_desempleo")
    val tasaDesempleo: Bitcoin,

    val bitcoin: Bitcoin
)

@Serializable
data class Bitcoin (
    val codigo: String,
    val nombre: String,

    @SerialName("unidad_medida")
    val unidad_medida: UnidadMedida,

    val fecha: String,
    val valor: Double
)

@Serializable(with = UnidadMedida.Companion::class)
enum class UnidadMedida(val value: String) {
    Dólar("Dólar"),
    Pesos("Pesos"),
    Porcentaje("Porcentaje");

    companion object : KSerializer<UnidadMedida> {
        override val descriptor: SerialDescriptor
            get() {
            return PrimitiveSerialDescriptor("quicktype.UnidadMedida", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): UnidadMedida = when (val value = decoder.decodeString()) {
            "Dólar"      -> Dólar
            "Pesos"      -> Pesos
            "Porcentaje" -> Porcentaje
            else         -> throw IllegalArgumentException("UnidadMedida could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: UnidadMedida) {
            return encoder.encodeString(value.value)
        }
    }
}