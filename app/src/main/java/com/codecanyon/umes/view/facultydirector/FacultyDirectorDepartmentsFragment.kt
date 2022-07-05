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
import com.codecanyon.umes.adapter.facultydirector.FacultyDirectorSectionsAdapter
import com.codecanyon.umes.model.facultydirector.SectionFacultyDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.facultydirector.FacultyDirectorDepartmentsViewModel
import kotlinx.android.synthetic.main.fragment_faculty_director_departments.*

class FacultyDirectorDepartmentsFragment(val facultyDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var facultyDirectorDepartmentsViewModel: FacultyDirectorDepartmentsViewModel

    private lateinit var sectionList: List<SectionFacultyDirector>
    private lateinit var sectionsAdapter: FacultyDirectorSectionsAdapter

    private fun init(){
        faculty_director_departments_fragment_recyclerView.setHasFixedSize(true)
        faculty_director_departments_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        sectionsAdapter = FacultyDirectorSectionsAdapter(arrayListOf())
        faculty_director_departments_fragment_recyclerView.adapter = sectionsAdapter

        facultyDirectorDepartmentsViewModel = ViewModelProvider(this).get(FacultyDirectorDepartmentsViewModel::class.java)
        observeLiveData()
        facultyDirectorDepartmentsViewModel.getSections(facultyDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculty_director_departments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        facultyDirectorDepartmentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        facultyDirectorDepartmentsViewModel.sectionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                sectionList = it

                if (faculty_director_departments_fragment_recyclerView.itemDecorationCount > 0)
                    faculty_director_departments_fragment_recyclerView.removeItemDecorationAt(0)

                faculty_director_departments_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                sectionsAdapter.loadData(sectionList)
            }
        })
    }
}