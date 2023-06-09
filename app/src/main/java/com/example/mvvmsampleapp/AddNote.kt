package com.example.mvvmsampleapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvvmsampleapp.Models.Note
import com.example.mvvmsampleapp.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var old_note:Note
    var isUpdate=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate=true

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
        binding.imgSave.setOnClickListener {
            val title=binding.etTitle.text.toString()
            val noteDes=binding.etNote.text.toString()
            if (title.isNotEmpty() || noteDes.isNotEmpty()){
                val formater=SimpleDateFormat("EEE, d MMM yyy HH:mm a")

                if (isUpdate){
                    note=Note(old_note.id,title,noteDes,formater.format(Date()))

                }else{
                    note = Note(null,title,noteDes,formater.format(Date())
                    )
                }
                val intent=Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@AddNote, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
        binding.imgBack.setOnClickListener{
            onBackPressed()
        }
    }
}