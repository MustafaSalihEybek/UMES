package com.codecanyon.umes.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.CoordinatorStudentDetailsDialogBinding
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import kotlinx.android.synthetic.main.coordinator_student_details_dialog.*

class CoordinatorStudentDetailsDialog(val mContext: Context, val studentCoordinator: StudentCoordinator) : Dialog(mContext), View.OnClickListener {
    private lateinit var v: CoordinatorStudentDetailsDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.coordinator_student_details_dialog, null, false)
        v.student = studentCoordinator
        setContentView(v.root)

        this.window?.let {
            it.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        }

        coordinator_students_details_dialog_imgClose.setOnClickListener(this)
        coordinator_students_details_dialog_btnClose.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id){
                R.id.coordinator_students_details_dialog_imgClose -> closeThisDialog()
                R.id.coordinator_students_details_dialog_btnClose -> closeThisDialog()
            }
        }
    }

    private fun closeThisDialog(){
        if (this.isShowing)
            this.dismiss()
    }
}