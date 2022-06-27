package com.codecanyon.umes.view.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.codecanyon.umes.R
import kotlinx.android.synthetic.main.exit_app_dialog.*

class ExitAppDialog(val mContext: Context) : Dialog(mContext), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exit_app_dialog)

        this.window?.let {
            it.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        }

        exit_app_dialog_imgClose.setOnClickListener(this)
        exit_app_dialog_imgNo.setOnClickListener(this)
        exit_app_dialog_imgYes.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id){
                R.id.exit_app_dialog_imgClose -> closeThisDialog()
                R.id.exit_app_dialog_imgNo -> closeThisDialog()
                R.id.exit_app_dialog_imgYes -> exitTheApp()
            }
        }
    }

    private fun closeThisDialog(){
        if (this.isShowing)
            this.dismiss()
    }

    private fun exitTheApp(){
        (mContext as Activity).moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}