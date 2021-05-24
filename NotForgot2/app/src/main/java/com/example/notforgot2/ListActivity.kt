package com.example.notforgot2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.list_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class ListActivity: AppCompatActivity() {

    lateinit var mRecyclerView : RecyclerView
    private val mAdapter : RecyclerAdapter = RecyclerAdapter()
    private lateinit var db: MyDatabase
    private var noteList : List<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        dataBaseInit(this)
        setUpRecyclerView()

        addNoteButton.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dataBaseInit(context : Context) = runBlocking{
        launch (Dispatchers.Default) {
            setUpDatabase(context)
        }
    }

    private fun setUpDatabase(context: Context) {
        db = Room.databaseBuilder(
                context,
                MyDatabase::class.java, "populus-database"
        ).build()

        db = MyDatabase.getDatabase(application)
        noteList = db.noteDao.getAllNotes() as List<Note>

        checkList()
    }

    private fun checkList(){
        restImageView.visibility = if(noteList!!.isEmpty()){
            View.VISIBLE
        }
        else View.INVISIBLE

        restTextView.visibility = restImageView.visibility
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.notesRecyclerView) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getNotes(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun getNotes(): MutableList<Note>{
        var notes:MutableList<Note>
        notes = noteList as MutableList<Note>
        /*notes.add(Note(0, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(1, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(2, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(3, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(4, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(5, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(6, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(7, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(8, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(9, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(10, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(11, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(12, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
        notes.add(Note(13, "Title", "Subtitle", "Description", "Deadline", "Priority", false))
         */
        return notes
    }
}