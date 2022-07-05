package com.codecanyon.umes.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.FacultyDirectorStudentDetailsDialogBinding
import com.codecanyon.umes.model.facultydirector.StudentsFacultyDirector
import kotlinx.android.synthetic.main.faculty_director_student_details_dialog.*

class FacultyDirectorStudentDetailsDialog(val studentData: StudentsFacultyDirector, val mContext: Context) : Dialog(mContext), View.OnClickListener {
    private lateinit var v: FacultyDirectorStudentDetailsDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.faculty_director_student_details_dialog, null, false)
        v.student = studentData
        setContentView(v.root)

        window?.let {
            it.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        }

        faculty_director_student_details_dialog_btnClose.setOnClickListener(this)
        faculty_director_student_details_dialog_imgClose.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id){
                R.id.faculty_director_student_details_dialog_btnClose -> closeThisDialog()
                R.id.faculty_director_student_details_dialog_imgClose -> closeThisDialog()
            }
        }
    }

    private fun closeThisDialog(){
        if (this.isShowing)
            this.dismiss()
    }
}