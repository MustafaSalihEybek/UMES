package com.codecanyon.umes.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.UpdateInstitutionDirectorProfileDialogBinding
import com.codecanyon.umes.model.InsDirector

class UpdateInstitutionDirectorProfileDialog(val mContext: Context, val insDirector: InsDirector) : Dialog(mContext) {
    private lateinit var v: UpdateInstitutionDirectorProfileDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.update_institution_director_profile_dialog, null, false)
        v.insdirector = insDirector
        setContentView(v.root)

        window?.let {
            it.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        }
    }
}