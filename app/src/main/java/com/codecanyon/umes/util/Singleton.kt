package com.codecanyon.umes.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.InsDirector
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.view.dialog.CoordinatorStudentDetailsDialog
import com.codecanyon.umes.view.dialog.ExitAppDialog
import com.codecanyon.umes.view.dialog.UpdateCoordinatorAndDepDirectorProfileDialog
import com.codecanyon.umes.view.dialog.UpdateInstitutionDirectorProfileDialog
import com.codecanyon.umes.viewmodel.MainViewModel

class Singleton {
    companion object{
        val SITE_URL: String = "192.168.27.3"
        val BASE_URL: String = "http://${SITE_URL}/UMES/api/"

        val VERTICAL_ITEM_SIZE: Int = 15

        lateinit var coordinatorStudentDetailsDialog: CoordinatorStudentDetailsDialog
        lateinit var exitAppDialog: ExitAppDialog
        var updateCoordinatorAndDepDirectorProfileDialog: UpdateCoordinatorAndDepDirectorProfileDialog? = null
        var updateInstitutionDirectorProfileDialog: UpdateInstitutionDirectorProfileDialog? = null

        @SuppressLint("StaticFieldLeak")
        var mNavView: RelativeLayout? = null
        var navIsOpen: Boolean = false

        fun hideNavView() : Boolean{
            mNavView?.let {
                if (navIsOpen){
                    it.visibility = View.GONE
                    navIsOpen = false

                    return true
                }
            }

            return false
        }

        fun showCoordinatorStudentDetailsDialog(mContext: Context, studentCoordinator: StudentCoordinator){
            coordinatorStudentDetailsDialog = CoordinatorStudentDetailsDialog(mContext, studentCoordinator)
            coordinatorStudentDetailsDialog.setCancelable(false)
            coordinatorStudentDetailsDialog.show()
        }

        fun showExitAppDialog(mContext: Context){
            exitAppDialog = ExitAppDialog(mContext)
            exitAppDialog.setCancelable(false)
            exitAppDialog.show()
        }

        fun showUpdateCoordinatorAndDepDirectorProfileDialog(mContext: Context, coordinator: Coordinator, mV: MainViewModel){
            updateCoordinatorAndDepDirectorProfileDialog = UpdateCoordinatorAndDepDirectorProfileDialog(mContext, coordinator, mV)
            updateCoordinatorAndDepDirectorProfileDialog!!.setCancelable(false)
            updateCoordinatorAndDepDirectorProfileDialog!!.show()
        }

        fun closeUpdateCoordinatorAndDepDirectorProfileDialog(){
            updateCoordinatorAndDepDirectorProfileDialog?.let {
                if (it.isShowing)
                    it.dismiss()
            }
        }

        fun showUpdateInstitutionDirectorProfileDialog(mContext: Context, insDirector: InsDirector){
            updateInstitutionDirectorProfileDialog = UpdateInstitutionDirectorProfileDialog(mContext, insDirector)
            //updateInstitutionDirectorProfileDialog!!.setCancelable(false)
            updateInstitutionDirectorProfileDialog!!.show()
        }

        fun closeUpdateInstitutionDirectorProfileDialog(){
            updateInstitutionDirectorProfileDialog?.let {
                if (it.isShowing)
                    it.dismiss()
            }
        }
    }
}