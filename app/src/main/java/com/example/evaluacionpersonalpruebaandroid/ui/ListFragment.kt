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
import com.google.firebase.firestore.ktx.toObject

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
                //document.toObject<EvaluationRecord>()
                list.add(EvaluationRecord(
                    document.data["gpsIdPlace"].toString(),
                    document.data["namePlace"].toString(),
                    document.data["nameEvaluated"].toString(),
                    document.data["dateEvaluation"].toString(),false,true,false,true,true,true,0,1
                    //document.data["checkCleanSuit"] as Boolean,
                    //document.data["checkCleanNails"] as Boolean,
                    //document.data["checkCombedHair"] as Boolean,
                    //document.data["checkFaceMask"] as Boolean,
                    //document.data["checkCap"] as Boolean,
                    //document.data["checkCourtesy"] as Boolean,
                    //document.data["inputClientsServed"] as Int,
                    //document.data["inputServicesSold"] as Int
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}