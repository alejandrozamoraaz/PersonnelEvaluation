package com.example.evaluacionpersonalpruebaandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacionpersonalpruebaandroid.R
import com.example.evaluacionpersonalpruebaandroid.model.ResumeCard


class ResumeListCard(private val modelList: List<ResumeCard>) :
    RecyclerView.Adapter<ResumeListCard.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.card_evaluated_resume, parent, false)
        v.setOnClickListener {
            Toast.makeText(v.context, "${"g"}",Toast.LENGTH_LONG).show()
        }
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: String = modelList[position].name
        val place: String = modelList[position].place
        val date: String = modelList[position].date
        holder.name.text = name
        holder.place.text = place
        holder.date.text = date
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.name)
        val place: TextView = v.findViewById(R.id.place)
        val date: TextView = v.findViewById(R.id.date)
    }
}