package com.example.evaluacionpersonalpruebaandroid

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluacionpersonalpruebaandroid.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            ValidateInputs()
            if (binding.inputEmail.error.isNullOrEmpty() && binding.inputPassword.error.isNullOrEmpty()) {
                LoginOrRegister(binding.inputEmail.text.toString(), binding.inputPassword.text.toString())
                //val intent = Intent(this,MainActivity::class.java)
                //startActivity(intent)
            }
        }
    }

    private fun ValidateInputs() {
        if (binding.inputEmail.text.toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches()){
            //Toast.makeText(this,"Correo no Válido", Toast.LENGTH_LONG).show()
             binding.inputEmail.setError("Correo no Válido")
        }else{
            binding.inputEmail.setError(null)
        }

        if (binding.inputPassword.text.toString().isEmpty() || binding.inputPassword.length() <= 7){
            //Toast.makeText(this,"8 caracteres mínimo", Toast.LENGTH_LONG).show()
            binding.inputPassword.setError("7 caracteres mínimo")
        }else if (!Pattern.compile("[0-9]").matcher(binding.inputPassword.text.toString()).find()){
            //Toast.makeText(this,"Se necesita al menos un Número", Toast.LENGTH_LONG).show()
            binding.inputPassword.setError("Se necesita al menos un Número")
        }else{
            binding.inputPassword.setError(null)
        }
    }

    fun LoginOrRegister(email: String, password: String){
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