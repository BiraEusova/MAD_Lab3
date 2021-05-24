package com.example.notforgot2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.add_note_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddNoteActivity: AppCompatActivity() {

    private lateinit var db: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_activity)

        backImageButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        addImageButton.setOnClickListener {
            val resetAddingFragment = AddingDialog()
            val manager = supportFragmentManager
            resetAddingFragment.show(manager,"resetAddingFragment")
        }

        saveButton.setOnClickListener {
            addNote(this);
            val resetSavingFragment = SavingDialog()
            val manager = supportFragmentManager
            resetSavingFragment.show(manager, "resetSavingFragment")
        }

        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select a date");
        val picker : MaterialDatePicker<*> = builder.build()

        ibCalendar.setOnClickListener {
            setTheme(R.style.ButtonBackground)
            picker.show(supportFragmentManager, picker.toString())
            setTheme(R.style.Theme_NotForgot2Main)
        }

        picker.addOnPositiveButtonClickListener{
            picker.exitTransition
        }

    }

    private fun addNote(context: Context) = runBlocking{
        launch(Dispatchers.Default){
            setAddingNote(context);
        }
    }

    private fun setAddingNote(context: Context){

        db = Room.databaseBuilder(
                context,
                MyDatabase::class.java, "populus-database"
        ).build()

        db = MyDatabase.getDatabase(application)
        val title = titleEditText.text.toString()
        val subtitle = "subtitle"
        val description = descriptionEditText.text.toString()
        val deadline = deadlineEditText.text.toString()
        val priority = priorityEditText.text.toString()
        val check = false

        val note = Note(0, title, subtitle, description, deadline, priority, check)

        db.noteDao.insertAll(note);
    }
}