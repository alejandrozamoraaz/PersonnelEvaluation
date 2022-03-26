package com.example.evaluacionpersonalpruebaandroid.model

data class EvaluationPlaces(var gpsIdPlace: String, var namePlace: String){
    override fun toString(): String {
        return namePlace
    }
}
