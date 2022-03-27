package com.example.evaluacionpersonalpruebaandroid.model

data class EvaluationRecord(var gpsIdPlace: String,
                            var namePlace: String,
                            var nameEvaluated: String,
                            var dateEvaluation: String,
                            val cleanSuit: Boolean,
                            val cleanNails: Boolean,
                            val combedHair: Boolean,
                            val faceMask: Boolean,
                            val cap: Boolean,
                            val courtesy: Boolean,
                            val clientsServed: Int,
                            val servicesSold: Int
)
