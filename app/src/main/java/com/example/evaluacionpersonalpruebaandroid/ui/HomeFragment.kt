package com.example.evaluacionpersonalpruebaandroid.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.evaluacionpersonalpruebaandroid.MainNewEvaluationActivity
import com.example.evaluacionpersonalpruebaandroid.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var user: FirebaseUser

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        user = FirebaseAuth.getInstance().currentUser!!
        (activity as AppCompatActivity).supportActionBar?.title = "Bienvenido: " + user.email

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(isLocationEnabled(requireContext()) == true){
            binding.buttonNewEvaluation.setOnClickListener {
                val intent = Intent(activity, MainNewEvaluationActivity::class.java)
                startActivity(intent)
            }
        }else{
            Toast.makeText(requireContext(),"Active la Ubicación", Toast.LENGTH_LONG).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isLocationEnabled(context: Context): Boolean {
        var locationMode = 0
        val locationProviders: String
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            locationMode = try {
                Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
                return false
            }
            locationMode != Settings.Secure.LOCATION_MODE_OFF
        } else {
            locationProviders = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            )
            !TextUtils.isEmpty(locationProviders)
        }
    }

    //override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        //(activity as AppCompatActivity).supportActionBar?.title = "Bienvenido: " + user.email
    //}
}