package com.example.notforgot2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_card.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var notes: MutableList<Note>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(superheros : MutableList<Note>, context: Context){
        this.notes = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.category_card, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.titleTextView
        val subtitle = view.subtitleTextView
        val check = view.checkBox
        var valCheck: Boolean = false;

        fun bind(note: Note, context: Context){
            title.text = note.title
            subtitle.text = note.subtitle
            valCheck = note.check
            check.isChecked = valCheck
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "Оно нажалось!", Toast.LENGTH_SHORT).show() })
        }
    }
}