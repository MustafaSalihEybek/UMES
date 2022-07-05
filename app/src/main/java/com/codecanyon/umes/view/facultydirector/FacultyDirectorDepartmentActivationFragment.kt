package com.codecanyon.umes.view.facultydirector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codecanyon.umes.R
import com.codecanyon.umes.adapter.decoration.LinearManagerDecoration
import com.codecanyon.umes.adapter.facultydirector.FacultyDirectorSectionActivationsAdapter
import com.codecanyon.umes.model.facultydirector.SectionActivationFacultyDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.facultydirector.FacultyDirectorDepartmentActivationViewModel
import kotlinx.android.synthetic.main.fragment_faculty_director_department_activation.*

class FacultyDirectorDepartmentActivationFragment(val facultyDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var facultyDirectorDepartmentActivationViewModel: FacultyDirectorDepartmentActivationViewModel

    private lateinit var sectionActivationList: List<SectionActivationFacultyDirector>
    private lateinit var sectionActivationsAdapter: FacultyDirectorSectionActivationsAdapter

    private fun init(){
        faculty_director_department_activation_recyclerView.setHasFixedSize(true)
        faculty_director_department_activation_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        sectionActivationsAdapter = FacultyDirectorSectionActivationsAdapter(arrayListOf())
        faculty_director_department_activation_recyclerView.adapter = sectionActivationsAdapter

        facultyDirectorDepartmentActivationViewModel = ViewModelProvider(this).get(FacultyDirectorDepartmentActivationViewModel::class.java)
        observeLiveData()
        facultyDirectorDepartmentActivationViewModel.getSectionActivationList(facultyDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_faculty_director_department_activation,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        facultyDirectorDepartmentActivationViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        facultyDirectorDepartmentActivationViewModel.sectionActivationList.observe(viewLifecycleOwner, Observer {
            it?.let {
                sectionActivationList = it

                if (faculty_director_department_activation_recyclerView.itemDecorationCount > 0)
                    faculty_director_department_activation_recyclerView.removeItemDecorationAt(0)

                faculty_director_department_activation_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                sectionActivationsAdapter.loadData(sectionActivationList)
            }
        })
    }
}