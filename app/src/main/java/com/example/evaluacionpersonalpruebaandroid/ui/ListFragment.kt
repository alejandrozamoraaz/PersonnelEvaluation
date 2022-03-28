package com.example.evaluacionpersonalpruebaandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluacionpersonalpruebaandroid.adapter.ResumenListCard
import com.example.evaluacionpersonalpruebaandroid.databinding.FragmentListBinding
import com.example.evaluacionpersonalpruebaandroid.model.EvaluationRecord
import com.google.firebase.firestore.FirebaseFirestore

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val db = FirebaseFirestore.getInstance()
        val list: ArrayList<EvaluationRecord> = ArrayList()

        db.collection("evaluation_records").get().addOnSuccessListener{ result ->
            for (document in result) {
                list.add(EvaluationRecord(
                    document.data["gpsIdPlace"].toString(),
                    document.data["namePlace"].toString(),
                    document.data["nameEvaluated"].toString(),
                    document.data["dateEvaluation"].toString(),
                    document.data["cleanSuit"].toString().toBoolean(),
                    document.data["cleanNails"].toString().toBoolean(),
                    document.data["combedHair"].toString().toBoolean(),
                    document.data["faceMask"].toString().toBoolean(),
                    document.data["cap"].toString().toBoolean(),
                    document.data["courtesy"].toString().toBoolean(),
                    document.data["clientsServed"].toString().toInt(),
                    document.data["servicesSold"].toString().toInt()
                ))
            }

            binding.list.layoutManager = LinearLayoutManager(context)
            val adapter = ResumenListCard(list)
            binding.list.adapter = adapter
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Error getting documents.", Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}