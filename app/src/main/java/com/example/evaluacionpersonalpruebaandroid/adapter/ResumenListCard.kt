package com.example.evaluacionpersonalpruebaandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacionpersonalpruebaandroid.R
import com.example.evaluacionpersonalpruebaandroid.model.EvaluationRecord
import com.google.android.material.bottomsheet.BottomSheetDialog


class ResumenListCard(private val modelList: List<EvaluationRecord>) :
    RecyclerView.Adapter<ResumenListCard.ViewHolder>() {


    private lateinit var context:Context
    lateinit var viewDetails: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewResume: View = LayoutInflater.from(parent.context).inflate(R.layout.card_evaluated_resumen, parent, false)
        viewDetails = LayoutInflater.from(parent.context).inflate(R.layout.fragment_evaluated_details_dialog, parent, false)
        context = viewResume.context

        return ViewHolder(viewResume,viewDetails)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val name: String = "Nombre: " + modelList[position].nameEvaluated
        //val place: String = "Lugar: " + modelList[position].namePlace
        //val date: String = "Fecha: " + modelList[position].dateEvaluation
        holder.nameEvaluated.text = "Nombre: " + modelList[position].nameEvaluated
        holder.placeEvaluation.text = "Lugar: " + modelList[position].namePlace
        holder.dateEvaluation.text = "Fecha: " + modelList[position].dateEvaluation

        holder.nameEvaluatedDialog.text = "Nombre: " + modelList[position].nameEvaluated
        holder.placeEvaluationDialog.text = "Lugar: " + modelList[position].namePlace
        holder.dateEvaluationDialog.text = "Fecha: " + modelList[position].dateEvaluation
        holder.cleanSuit.isChecked = modelList[position].cleanSuit
        holder.cleanNails.isChecked = modelList[position].cleanNails
        holder.combedHair.isChecked = modelList[position].combedHair
        holder.faceMask.isChecked = modelList[position].faceMask
        holder.cap.isChecked = modelList[position].cap
        holder.courtesy.isChecked = modelList[position].courtesy
        holder.clientsServed.text.insert(0, modelList[position].clientsServed.toString())
        holder.servicesSold.text.insert(0, modelList[position].servicesSold.toString())

        holder.item.setOnClickListener {
            if (viewDetails.getParent() != null) {
                (viewDetails.getParent() as ViewGroup).removeView(viewDetails)
            }else {
                val dialog = BottomSheetDialog(context)
                dialog.setContentView(viewDetails)
                dialog.show()
                ///dialog.show(FragmentManager.findFragment<EvaluatedDetailsDialog>(v2).parentFragmentManager,"dialog")
                //Toast.makeText(conte,"${modelList[position].name} ${modelList[position].place} ${modelList[position].date}",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class ViewHolder(viewCard: View, viewDialog: View) : RecyclerView.ViewHolder(viewCard) {
        val nameEvaluated: TextView = viewCard.findViewById(R.id.nameEvaluatedCard)
        val placeEvaluation: TextView = viewCard.findViewById(R.id.placeEvaluationCard)
        val dateEvaluation: TextView = viewCard.findViewById(R.id.dateEvaluationCard)

        val nameEvaluatedDialog: TextView = viewDialog.findViewById(R.id.nameEvaluatedDialog)
        val dateEvaluationDialog: TextView = viewDialog.findViewById(R.id.dateEvaluationDialog)
        val placeEvaluationDialog: TextView = viewDialog.findViewById(R.id.placeEvaluationDialog)

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val cleanSuit: Switch = viewDialog.findViewById(R.id.checkCleanSuitDialog)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val cleanNails: Switch = viewDialog.findViewById(R.id.checkCleanNailsDialog)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val combedHair: Switch = viewDialog.findViewById(R.id.checkCombedHairDialog)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val faceMask: Switch = viewDialog.findViewById(R.id.checkFaceMaskDialog)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val cap: Switch = viewDialog.findViewById(R.id.checkCapDialog)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val courtesy: Switch = viewDialog.findViewById(R.id.checkCourtesyDialog)
        val clientsServed: EditText = viewDialog.findViewById(R.id.inputClientsServedDialog)
        val servicesSold: EditText = viewDialog.findViewById(R.id.inputServicesSoldDialog)

        val item:CardView = viewCard.findViewById(R.id.itemCard)
    }
}