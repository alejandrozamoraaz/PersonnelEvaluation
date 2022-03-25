package com.example.evaluacionpersonalpruebaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityContinueNewEvaluationBinding

class ContinueNewEvaluationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContinueNewEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinueNewEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)
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