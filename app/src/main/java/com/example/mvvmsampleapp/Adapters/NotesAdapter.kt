package com.example.mvvmsampleapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleapp.Models.Note
import com.example.mvvmsampleapp.R
import kotlin.random.Random

class NotesAdapter(private val context:Context,val listner: NotesClickListner):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList=ArrayList<Note>()
    private val fullList=ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=NotesList[position]
        holder.title_tv.text = currentNote.title
        holder.title_tv.isSelected=true
        holder.note_tv.text = currentNote.note
        holder.date_tv.text = currentNote.date
        holder.date_tv.isSelected=true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor()))

        holder.notes_layout.setOnClickListener {
            listner.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listner.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }
            }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search:String){
        NotesList.clear()
        for (item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){
                NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun randomColor():Int{

        val list= ArrayList<Int>()
        list.add(R.color.noteColor1)
        list.add(R.color.noteColor2)
        list.add(R.color.noteColor3)
        list.add(R.color.noteColor4)
        list.add(R.color.noteColor5)
        list.add(R.color.noteColor6)
        val seed= System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title_tv=itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv=itemView.findViewById<TextView>(R.id.tv_notes)
        val date_tv=itemView.findViewById<TextView>(R.id.tv_date)

    }
    interface NotesClickListner{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }
}
