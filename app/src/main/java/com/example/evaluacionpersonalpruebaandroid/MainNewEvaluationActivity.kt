package com.example.evaluacionpersonalpruebaandroid


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityMainNewEvaluationBinding
import com.example.evaluacionpersonalpruebaandroid.model.EvaluationPlaces
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainNewEvaluationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNewEvaluationBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val items = arrayOf("Pricesmart", "Colonia Plaza Once", "Plaza inter")

        //val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)
        //binding.spinnerPlaces.setAdapter(adapter);

        /////////////////////////////
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, setSpinnerData())
        binding.spinnerPlaces.setAdapter(adapter)

        /*binding.spinnerPlaces.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val country = parent.selectedItem as Country
                Toast.makeText(baseContext, "Country ID: " + country.id + ",  Country Name : " + country.name, Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })*/



        ////////////////////////////////

        binding.editTextDateEvaluation.text.insert(0, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString())
        /*binding.spinnerPlaces.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Toast.makeText(baseContext,"item"+ parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Required method stub
            }
        })*/

        binding.buttonStartEvaluation.setOnClickListener {
            if (binding.inputNameEvaluated.text.toString().isEmpty()) {
                binding.inputNameEvaluated.setError("Ingrese el Nombre")
            } else {
                //var mainFragment: NewEvaluationFragment = NewEvaluationFragment()
                //supportFragmentManager.beginTransaction()
                    //.replace(binding.ConstraintLayout.id, mainFragment).commit()

                val intent = Intent(this, ContinueNewEvaluationActivity::class.java)
                val gpsIdPlace = binding.spinnerPlaces.selectedItem as EvaluationPlaces

                intent.putExtra("gpsIdPlace", gpsIdPlace.gpsIdPlace)
                intent.putExtra("namePlace", gpsIdPlace.namePlace)
                intent.putExtra("nameEvaluated", binding.inputNameEvaluated.text.toString())
                intent.putExtra("dateEvaluation",binding.editTextDateEvaluation.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun setSpinnerData(): ArrayList<EvaluationPlaces> {
        val placesList: ArrayList<EvaluationPlaces> = ArrayList()

        placesList.add(EvaluationPlaces("12.0730092, -86.2225334", "PriceSmart"))
        placesList.add(EvaluationPlaces("12.076579, -86.222108", "Colonia Plaza Once"))
        placesList.add(EvaluationPlaces("12.1438966, -86.2761858", "Plaza inter"))

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