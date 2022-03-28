package com.example.evaluacionpersonalpruebaandroid


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityMainNewEvaluationBinding
import com.example.evaluacionpersonalpruebaandroid.model.EvaluationPlaces
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainNewEvaluationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainNewEvaluationBinding

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var gpsIdPlace: EvaluationPlaces
    private lateinit var currentLatLong : LatLng

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        binding.mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, setSpinnerData())
        binding.spinnerPlaces.adapter = adapter

        binding.editTextDateEvaluation.text.insert(0, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString())
        binding.buttonStartEvaluation.setOnClickListener {
            if (binding.inputNameEvaluated.text.toString().isEmpty()) {
                binding.inputNameEvaluated.error = "Ingrese el Nombre"
            }else {

                gpsIdPlace = binding.spinnerPlaces.selectedItem as EvaluationPlaces

                //if(gpsIdPlace.gpsIdPlace != currentLatLong){
                //Toast.makeText(this,"${gpsIdPlace.gpsIdPlace} --- ${currentLatLong}",Toast.LENGTH_LONG).show()
                //}else {
                val intent = Intent(this, ContinueNewEvaluationActivity::class.java)
                intent.putExtra("IdPlace", gpsIdPlace.gpsIdPlace.toString())
                intent.putExtra("namePlace", gpsIdPlace.namePlace)
                intent.putExtra("nameEvaluated", binding.inputNameEvaluated.text.toString())
                intent.putExtra("dateEvaluation",binding.editTextDateEvaluation.text.toString())
                startActivity(intent)
                //}
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setUpLocation()
    }

    private fun setUpLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            return
        }
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null){
                currentLatLong = LatLng(location.latitude, location.longitude)
            }

        }
    }

    private fun setSpinnerData(): ArrayList<EvaluationPlaces> {
        val placesList: ArrayList<EvaluationPlaces> = ArrayList()

        placesList.add(EvaluationPlaces(LatLng(12.0730092,-86.2225334), "PriceSmart"))
        placesList.add(EvaluationPlaces(LatLng(12.076579,-86.222108), "Colonia Plaza Once"))
        placesList.add(EvaluationPlaces(LatLng(12.1438966,-86.2761858), "Plaza inter"))

        return placesList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}