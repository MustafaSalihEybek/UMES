package com.codecanyon.umes.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.codecanyon.umes.R
import com.codecanyon.umes.databinding.UpdateCoordinatorAndDepDirectorProfileDialogBinding
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.update_coordinator_and_dep_director_profile_dialog.*

class UpdateCoordinatorAndDepDirectorProfileDialog(val mContext: Context, val coordinator: Coordinator, val mV: MainViewModel) : Dialog(mContext), View.OnClickListener {
    private lateinit var v: UpdateCoordinatorAndDepDirectorProfileDialogBinding

    private lateinit var txtUserPhone: String
    private lateinit var txtUserEmail: String
    private lateinit var txtUserOldPassword: String
    private lateinit var txtUserNewPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.update_coordinator_and_dep_director_profile_dialog, null, false)
        v.coordinator = coordinator
        setContentView(v.root)

        window?.let {
            it.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        }

        update_coordinator_and_dep_director_profile_dialog_btnUpdate.setOnClickListener(this)
        update_coordinator_and_dep_director_profile_dialog_btnClose.setOnClickListener(this)
        update_coordinator_and_dep_director_profile_dialog_imgClose.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id){
                R.id.update_coordinator_and_dep_director_profile_dialog_btnUpdate -> updateUserProfile()
                R.id.update_coordinator_and_dep_director_profile_dialog_btnClose -> Singleton.closeUpdateCoordinatorAndDepDirectorProfileDialog()
                R.id.update_coordinator_and_dep_director_profile_dialog_imgClose -> Singleton.closeUpdateCoordinatorAndDepDirectorProfileDialog()
            }
        }
    }

    private fun updateUserProfile(){
        txtUserPhone = update_coordinator_and_dep_director_profile_dialog_editUserPhone.text.toString().trim()
        txtUserEmail = update_coordinator_and_dep_director_profile_dialog_editUserEmail.text.toString().trim()
        txtUserOldPassword = update_coordinator_and_dep_director_profile_dialog_editUserOldPassword.text.toString().trim()
        txtUserNewPassword = update_coordinator_and_dep_director_profile_dialog_editUserNewPassword.text.toString().trim()

        if (!txtUserEmail.isEmpty()){
            if (!txtUserOldPassword.isEmpty()){
                if (!txtUserNewPassword.isEmpty()){
                    if (txtUserOldPassword.equals(coordinator.teacherPassword)){
                        mV.updateTeacherData(coordinator.teacherId, txtUserPhone, txtUserEmail, txtUserNewPassword)
                    } else
                        txtUserOldPassword.show(v.root, "Eski şifrenizi yanlış girdiniz")
                } else
                    txtUserNewPassword.show(v.root, "Lütfen yeni bir şifre belirleyiniz")
            } else
                txtUserOldPassword.show(v.root, "Lütfen eski şifrenizi giriniz")
        } else
            txtUserEmail.show(v.root, "Lütfen geçerli bir email adresi giriniz")
    }
}