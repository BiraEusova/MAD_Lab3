package com.example.notforgot2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class AddingDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.adding_dialog_activity, null))
                .setPositiveButton("СОХРАНИТЬ"){
                    dialog, id -> dialog.cancel()
                }
                .setNegativeButton("ОТМЕНА"){
                    dialog, id ->  dialog.cancel()
                }
        return builder.create();
    }
}