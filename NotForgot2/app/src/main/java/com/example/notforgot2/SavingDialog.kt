package com.example.notforgot2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.DialogFragment


class SavingDialog : DialogFragment()  {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.saving_dialog_activity, null))
        Handler().postDelayed({
            val intent = Intent(activity, ListActivity::class.java)
            startActivity(intent)
            this.dismiss()
        },4000)

        return builder.create();
    }
}