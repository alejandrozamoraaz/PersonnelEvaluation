package com.example.evaluacionpersonalpruebaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityContinueNewEvaluationBinding

class ContinueNewEvaluationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContinueNewEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinueNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        Toast.makeText(this,bundle?.getString("gpsIdPlace")+" "+bundle?.getString("namePlace")+" "+ bundle?.getString("nameEvaluated") +" "+ bundle?.getString("dateEvaluation"), Toast.LENGTH_LONG).show()

        binding.buttonFinishEvaluation.setOnClickListener {
            if (binding.input7NewEvaluation.text.toString().isEmpty()){
                binding.input7NewEvaluation.setError("Rellene el Campo")
            } else if(binding.input8NewEvaluation.text.toString().isEmpty()){
                binding.input8NewEvaluation.setError("Rellene el Campo")
            }else{

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