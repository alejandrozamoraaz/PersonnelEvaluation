package com.example.evaluacionpersonalpruebaandroid

import android.R
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityNewEvaluationBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class NewEvaluationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewEvaluationBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = arrayOf("Pricesmart", "Colonia Plaza Once", "Plaza inter")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)
        binding.spinnerPlaces.setAdapter(adapter);


        binding.editTextDateEvaluation.text.insert(0, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString())
        binding.spinnerPlaces.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Toast.makeText(baseContext,"item"+ parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Required method stub
            }
        })

        binding.buttonStartEvaluation.setOnClickListener {
            if (binding.inputNameEvaluated.text.toString().isEmpty()){
                binding.inputNameEvaluated.setError("Ingrese el Nombre")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.evaluacionpersonalpruebaandroid.R.menu.action_bar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            com.example.evaluacionpersonalpruebaandroid.R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}