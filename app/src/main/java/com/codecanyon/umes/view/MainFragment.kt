package com.codecanyon.umes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codecanyon.umes.R
import com.codecanyon.umes.adapter.NavListAdapter
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.FacultyDirector
import com.codecanyon.umes.model.InsDirector
import com.codecanyon.umes.model.Student
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.view.coordinator.CoordinatorInstitutionsFragment
import com.codecanyon.umes.view.coordinator.CoordinatorRatingFragment
import com.codecanyon.umes.view.coordinator.CoordinatorStudentPapersFragment
import com.codecanyon.umes.view.coordinator.CoordinatorStudentsFragment
import com.codecanyon.umes.view.departmentdirector.*
import com.codecanyon.umes.view.facultydirector.*
import com.codecanyon.umes.view.institutiondirector.InstitutionDirectorDocumentsFragment
import com.codecanyon.umes.view.institutiondirector.InstitutionDirectorStudentsFragment
import com.codecanyon.umes.view.student.StudentDailyActivityFragment
import com.codecanyon.umes.view.student.StudentDocumentsFragment
import com.codecanyon.umes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var v: View
    private lateinit var transaction: FragmentTransaction
    private lateinit var mainViewModel: MainViewModel
    private var isNavOpen: Boolean = false

    private lateinit var loginType: String
    private lateinit var coordinatorData: Coordinator
    private lateinit var studentData: Student
    private lateinit var insDirectorData: InsDirector
    private lateinit var facultyDirectorData: FacultyDirector

    private var studentFragments = arrayOf(
        StudentDailyActivityFragment(),
        StudentDocumentsFragment()
    )
    private lateinit var studentItemList: Array<TextView>

    private lateinit var coordinatorFragments: Array<Fragment>
    private lateinit var coordinatorItemList: Array<TextView>

    private var institutionDirectorFragments = arrayOf(
        InstitutionDirectorStudentsFragment(),
        InstitutionDirectorDocumentsFragment()
    )
    private lateinit var institutionDirectorItemList: Array<TextView>

    private lateinit var departmentFragments: Array<Fragment>
    private lateinit var departmentItemList: Array<TextView>

    private var facultyDirectorFragments = arrayOf(
        FacultyDirectorStudentsFragment(),
        FacultyDirectorMembersFragment(),
        FacultyDirectorDepartmentsFragment(),
        FacultyDirectorDepartmentActivationFragment(),
        FacultyDirectorInstitutionsFragment(),
        FacultyDirectorDocumentsFragment()
    )
    private lateinit var facultyDirectorItemList: Array<TextView>

    private fun init(){
        arguments?.let {
            loginType = MainFragmentArgs.fromBundle(it).signType

            if (loginType == "1"){
                studentData = MainFragmentArgs.fromBundle(it).studentData!!
                main_fragment_navStudentItemList.visibility = View.VISIBLE
                main_fragment_txtUserName.text = studentData.studentFullName
            } else if (loginType == "2" || loginType == "4"){
                coordinatorData = MainFragmentArgs.fromBundle(it).coordinatorData!!
                main_fragment_txtUserName.text = coordinatorData.teacherFullName

                if (loginType == "2"){
                    coordinatorFragments = arrayOf(
                        CoordinatorStudentsFragment(coordinatorData.teacherId, coordinatorData.teacherDepartmenId),
                        CoordinatorInstitutionsFragment(coordinatorData.teacherDepartmenId),
                        CoordinatorRatingFragment(coordinatorData.teacherId, coordinatorData.teacherDepartmenId),
                        CoordinatorStudentPapersFragment(coordinatorData.teacherId, coordinatorData.teacherDepartmenId)
                    )
                    main_fragment_navCoordinatorItemList.visibility = View.VISIBLE
                } else {
                    departmentFragments = arrayOf(
                        DepartmentDirectorStudentsFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorMembersFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorMemberActivationFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorDocumentsFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorInstitutionsFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorInstitutionDirectorsFragment(coordinatorData.teacherDepartmenId),
                        DepartmentDirectorNoticeFragment()
                    )
                    main_fragment_navDepartmentDirectorItemList.visibility = View.VISIBLE
                }
            } else if (loginType == "3"){
                insDirectorData = MainFragmentArgs.fromBundle(it).insDirectorData!!
                main_fragment_navInsDirectorItemList.visibility = View.VISIBLE
                main_fragment_txtUserName.text = insDirectorData.insDirectorFullName
            } else if (loginType == "5"){
                facultyDirectorData = MainFragmentArgs.fromBundle(it).facultyDirectorData!!
                main_fragment_navFacultyDirectorItemList.visibility = View.VISIBLE
                main_fragment_txtUserName.text = facultyDirectorData.facultyDirectorFullName
            }

            Singleton.mNavView = main_fragment_navView
            Singleton.navIsOpen = isNavOpen
            main_fragment_imgNavMenu.setOnClickListener(this)
            main_fragment_linearUserProfile.setOnClickListener(this)

            //Student
            main_fragment_txtStudentDailyActivity.setOnClickListener(this)
            main_fragment_txtStudentDocuments.setOnClickListener(this)
            studentItemList = arrayOf(
                main_fragment_txtStudentDailyActivity,
                main_fragment_txtStudentDocuments
            )

            //Coordinator
            main_fragment_txtCoordinatorStudents.setOnClickListener(this)
            main_fragment_txtCoordinatorInstitutions.setOnClickListener(this)
            main_fragment_txtCoordinatorRating.setOnClickListener(this)
            main_fragment_txtCoordinatorStudentPapers.setOnClickListener(this)
            coordinatorItemList = arrayOf(
                main_fragment_txtCoordinatorStudents,
                main_fragment_txtCoordinatorInstitutions,
                main_fragment_txtCoordinatorRating,
                main_fragment_txtCoordinatorStudentPapers
            )

            //Institution Director
            main_fragment_txtInsDirectorStudents.setOnClickListener(this)
            main_fragment_txtInsDirectorDocuments.setOnClickListener(this)
            institutionDirectorItemList = arrayOf(
                main_fragment_txtInsDirectorStudents,
                main_fragment_txtInsDirectorDocuments
            )

            //Department Director
            main_fragment_txtDepartmentDirectorStudents.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorMembers.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorMemberActivation.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorDocuments.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorInstitutions.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorInstitutionDirectors.setOnClickListener(this)
            main_fragment_txtDepartmentDirectorNotice.setOnClickListener(this)
            departmentItemList = arrayOf(
                main_fragment_txtDepartmentDirectorStudents,
                main_fragment_txtDepartmentDirectorMembers,
                main_fragment_txtDepartmentDirectorMemberActivation,
                main_fragment_txtDepartmentDirectorDocuments,
                main_fragment_txtDepartmentDirectorInstitutions,
                main_fragment_txtDepartmentDirectorInstitutionDirectors,
                main_fragment_txtDepartmentDirectorNotice
            )

            //Faculty Director
            main_fragment_txtFacultyDirectorStudents.setOnClickListener(this)
            main_fragment_txtFacultyDirectorMembers.setOnClickListener(this)
            main_fragment_txtFacultyDirectorDepartments.setOnClickListener(this)
            main_fragment_txtFacultyDirectorDepartmentActivation.setOnClickListener(this)
            main_fragment_txtFacultyDirectorInstitutions.setOnClickListener(this)
            main_fragment_txtFacultyDirectorDocuments.setOnClickListener(this)
            facultyDirectorItemList = arrayOf(
                main_fragment_txtFacultyDirectorStudents,
                main_fragment_txtFacultyDirectorMembers,
                main_fragment_txtFacultyDirectorDepartments,
                main_fragment_txtFacultyDirectorDepartmentActivation,
                main_fragment_txtFacultyDirectorInstitutions,
                main_fragment_txtFacultyDirectorDocuments
            )

            mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            observeLiveData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        mainViewModel.successMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        mainViewModel.newTeacherData.observe(viewLifecycleOwner, Observer {
            it?.let {
                Singleton.closeUpdateCoordinatorAndDepDirectorProfileDialog()

                AppUtils.mCoordinator = Coordinator(
                    coordinatorData.teacherId,
                    coordinatorData.teacherFullName,
                    coordinatorData.teacherDepartmenId,
                    coordinatorData.teacherDepartmenName,
                    it.teacherPassword,
                    it.teacherPhone,
                    it.teacherEmail
                )
                coordinatorData = AppUtils.mCoordinator
            }
        })
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id){
                R.id.main_fragment_imgNavMenu -> setNavVisibility()
                R.id.main_fragment_linearUserProfile -> showUpdateProfileDialog(loginType)

                //Student
                R.id.main_fragment_txtStudentDailyActivity -> setFragment(studentFragments.get(0), studentItemList, 0)
                R.id.main_fragment_txtStudentDocuments -> setFragment(studentFragments.get(1), studentItemList, 1)

                //Coordinator
                R.id.main_fragment_txtCoordinatorStudents -> setFragment(coordinatorFragments.get(0), coordinatorItemList, 0)
                R.id.main_fragment_txtCoordinatorInstitutions -> setFragment(coordinatorFragments.get(1), coordinatorItemList, 1)
                R.id.main_fragment_txtCoordinatorRating -> setFragment(coordinatorFragments.get(2), coordinatorItemList, 2)
                R.id.main_fragment_txtCoordinatorStudentPapers -> setFragment(coordinatorFragments.get(3), coordinatorItemList, 3)

                //Institution Director
                R.id.main_fragment_txtInsDirectorStudents -> setFragment(institutionDirectorFragments.get(0), institutionDirectorItemList, 0)
                R.id.main_fragment_txtInsDirectorDocuments -> setFragment(institutionDirectorFragments.get(1), institutionDirectorItemList, 1)

                //Department Director
                R.id.main_fragment_txtDepartmentDirectorStudents -> setFragment(departmentFragments.get(0), departmentItemList, 0)
                R.id.main_fragment_txtDepartmentDirectorMembers -> setFragment(departmentFragments.get(1), departmentItemList, 1)
                R.id.main_fragment_txtDepartmentDirectorMemberActivation -> setFragment(departmentFragments.get(2), departmentItemList, 2)
                R.id.main_fragment_txtDepartmentDirectorDocuments -> setFragment(departmentFragments.get(3), departmentItemList, 3)
                R.id.main_fragment_txtDepartmentDirectorInstitutions -> setFragment(departmentFragments.get(4), departmentItemList, 4)
                R.id.main_fragment_txtDepartmentDirectorInstitutionDirectors -> setFragment(departmentFragments.get(5), departmentItemList, 5)
                R.id.main_fragment_txtDepartmentDirectorNotice -> setFragment(departmentFragments.get(6), departmentItemList, 6)

                //Faculty Director
                R.id.main_fragment_txtFacultyDirectorStudents -> setFragment(facultyDirectorFragments.get(0), facultyDirectorItemList, 0)
                R.id.main_fragment_txtFacultyDirectorMembers -> setFragment(facultyDirectorFragments.get(1), facultyDirectorItemList, 1)
                R.id.main_fragment_txtFacultyDirectorDepartments -> setFragment(facultyDirectorFragments.get(2), facultyDirectorItemList, 2)
                R.id.main_fragment_txtFacultyDirectorDepartmentActivation -> setFragment(facultyDirectorFragments.get(3), facultyDirectorItemList, 3)
                R.id.main_fragment_txtFacultyDirectorInstitutions -> setFragment(facultyDirectorFragments.get(4), facultyDirectorItemList, 4)
                R.id.main_fragment_txtFacultyDirectorDocuments -> setFragment(facultyDirectorFragments.get(5), facultyDirectorItemList, 5)
            }
        }
    }

    private fun showUpdateProfileDialog(loginType: String){
        if (loginType == "2" || loginType == "4")
            Singleton.showUpdateCoordinatorAndDepDirectorProfileDialog(v.context, coordinatorData, mainViewModel)
    }

    private fun setFragment(fragment: Fragment?, itemList: Array<TextView>, selectedIndex: Int){
        fragment?.let {
            transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.main_fragment_frameLayout, it)
            transaction.commit()

            changeSelectedItemColor(itemList, selectedIndex)
            setNavVisibility()
        }
    }

    private fun changeSelectedItemColor(itemList: Array<TextView>, selectedIndex: Int){
        for (i in itemList.indices){
            if (i == selectedIndex){
                main_fragment_txtNavTitle.text = itemList.get(i).text.substring(2, itemList.get(i).text.length)
                itemList.get(i).setBackgroundColor(ContextCompat.getColor(v.context, R.color.navItemBgColor))
            }
            else
                itemList.get(i).setBackgroundColor(ContextCompat.getColor(v.context, R.color.navBgColor))
        }
    }

    private fun setNavVisibility(){
        if (!isNavOpen || !Singleton.navIsOpen){
            main_fragment_navView.visibility = View.VISIBLE
            isNavOpen = true
            Singleton.navIsOpen = isNavOpen
        }
        else{
            main_fragment_navView.visibility = View.GONE
            isNavOpen = false
            Singleton.navIsOpen = isNavOpen
        }

    }
}