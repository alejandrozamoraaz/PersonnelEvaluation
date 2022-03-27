package com.example.evaluacionpersonalpruebaandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityContinueNewEvaluationBinding
import com.example.evaluacionpersonalpruebaandroid.model.EvaluationRecord
import com.google.firebase.firestore.FirebaseFirestore

class ContinueNewEvaluationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContinueNewEvaluationBinding
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinueNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseFirestore.getInstance()
        val bundle = intent.extras

        binding.buttonFinishEvaluation.setOnClickListener {
            when {
                binding.inputClientsServed.text.toString().isEmpty() -> {
                    binding.inputClientsServed.error = "Rellene el Campo"
                }
                binding.inputServicesSold.text.toString().isEmpty() -> {
                    binding.inputServicesSold.error = "Rellene el Campo"
                }
                else -> {
                    val record = EvaluationRecord(
                        bundle?.getString("gpsIdPlace").toString(),
                        bundle?.getString("namePlace").toString(),
                        bundle?.getString("nameEvaluated").toString(),
                        bundle?.getString("dateEvaluation").toString(),
                        binding.checkCleanSuit.isChecked,
                        binding.checkCleanNails.isChecked,
                        binding.checkCombedHair.isChecked,
                        binding.checkFaceMask.isChecked,
                        binding.checkCap.isChecked,
                        binding.checkCourtesy.isChecked,
                        Integer.parseInt(binding.inputClientsServed.text.toString()),
                        Integer.parseInt(binding.inputServicesSold.text.toString())
                    )

                    database.collection("evaluation_records").add(record).addOnSuccessListener {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "¡${bundle?.getString("nameEvaluated")} fue Evaluado!", Toast.LENGTH_LONG).show()
                        bundle?.clear()
                    }.addOnFailureListener {
                        Toast.makeText(applicationContext, "Ha ocurrido un error, Vuelva a intentarlo", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
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