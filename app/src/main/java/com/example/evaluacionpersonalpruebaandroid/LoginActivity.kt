package com.example.evaluacionpersonalpruebaandroid

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        auth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            validateInputs()
            if (binding.inputEmail.error.isNullOrEmpty() && binding.inputPassword.error.isNullOrEmpty()) {
                loginOrRegister(binding.inputEmail.text.toString(), binding.inputPassword.text.toString())
            }
        }
    }

    private fun validateInputs() {
        if (binding.inputEmail.text.toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches()){
            binding.inputEmail.error = "Correo no Válido"
        }else{
            binding.inputEmail.error = null
        }

        if (binding.inputPassword.text.toString().isEmpty() || binding.inputPassword.length() < 7){
            binding.inputPassword.error = "7 caracteres mínimo"
        }else if (!Pattern.compile("[0-9]").matcher(binding.inputPassword.text.toString()).find()){
            binding.inputPassword.error = "Se necesita al menos un Número"
        }else{
            binding.inputPassword.error = null
        }
    }

    private fun loginOrRegister(email: String, password: String){
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {  taskSign ->
                if (taskSign.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { taskCreate ->
                        if (taskCreate.isSuccessful) {
                            Toast.makeText(this, "Usuario creado", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }catch (error:Error){
            Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}