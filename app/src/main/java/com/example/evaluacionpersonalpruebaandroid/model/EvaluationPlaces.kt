package com.example.evaluacionpersonalpruebaandroid.model

import com.google.android.gms.maps.model.LatLng

data class EvaluationPlaces(var gpsIdPlace: LatLng, var namePlace: String){
    override fun toString(): String {
        return namePlace
    }
}
