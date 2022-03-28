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


class ResumenListCard(private var modelList: List<EvaluationRecord>) :
    RecyclerView.Adapter<ResumenListCard.ViewHolder>() {


    private lateinit var context:Context
    private lateinit var viewDetail: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewResume: View = LayoutInflater.from(parent.context).inflate(R.layout.card_evaluated_resumen, parent, false)
        val viewDetails:View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_evaluated_details_dialog, parent, false)
        context = parent.context

        viewDetail = viewDetails
        return ViewHolder(viewResume,viewDetails)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameEvaluatedDialog.text = modelList[position].nameEvaluated
        holder.placeEvaluationDialog.text = modelList[position].namePlace
        holder.dateEvaluationDialog.text = modelList[position].dateEvaluation
        
        holder.cleanSuit.isChecked = modelList[position].cleanSuit
        holder.cleanNails.isChecked = modelList[position].cleanNails
        holder.combedHair.isChecked = modelList[position].combedHair
        holder.faceMask.isChecked = modelList[position].faceMask
        holder.cap.isChecked = modelList[position].cap
        holder.courtesy.isChecked = modelList[position].courtesy
        holder.clientsServed.text = modelList[position].clientsServed.toString()
        holder.servicesSold.text = modelList[position].servicesSold.toString()

        holder.nameEvaluated.text = "Nombre: " + modelList[position].nameEvaluated
        holder.placeEvaluation.text = "Lugar: " + modelList[position].namePlace
        holder.dateEvaluation.text = "Fecha: " + modelList[position].dateEvaluation

        holder.itemCard.setOnClickListener {
            if (viewDetail.parent != null) {
                (viewDetail.parent as ViewGroup).removeView(viewDetail)
            }else {
                val dialog = BottomSheetDialog(context)
                dialog.setContentView(viewDetail)
                dialog.show()
                //Toast.makeText(context,"${modelList[position].nameEvaluated} ${modelList[position].namePlace} ${modelList[position].dateEvaluation}",Toast.LENGTH_LONG).show()
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
        val clientsServed: TextView = viewDialog.findViewById(R.id.clientsServedDialog)
        val servicesSold: TextView = viewDialog.findViewById(R.id.servicesSoldDialog)

        val itemCard:CardView = viewCard.findViewById(R.id.itemCard)
    }
}