package com.example.evaluacionpersonalpruebaandroid.model

data class EvaluationRecord(
    var gpsIdPlace: String,
    var namePlace: String,
    var nameEvaluated: String,
    var dateEvaluation: String,
    @field:JvmField var cleanSuit: Boolean,
    @field:JvmField var cleanNails: Boolean,
    @field:JvmField var combedHair: Boolean,
    @field:JvmField var faceMask: Boolean,
    @field:JvmField var cap: Boolean,
    @field:JvmField var courtesy: Boolean,
    var clientsServed: Int,
    var servicesSold: Int
)
