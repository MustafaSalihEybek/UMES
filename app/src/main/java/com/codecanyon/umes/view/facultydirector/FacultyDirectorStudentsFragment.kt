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
import com.codecanyon.umes.adapter.facultydirector.FacultyDirectorStudentsAdapter
import com.codecanyon.umes.model.facultydirector.StudentsFacultyDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.facultydirector.FacultyDirectorStudentsViewModel
import kotlinx.android.synthetic.main.fragment_faculty_director_students.*

class FacultyDirectorStudentsFragment(val facultyDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var facultyDirectorStudentsViewModel: FacultyDirectorStudentsViewModel

    private lateinit var studentList: List<StudentsFacultyDirector>
    private lateinit var studentsAdapter: FacultyDirectorStudentsAdapter

    private fun init(){
        faculty_director_students_fragment_recyclerView.setHasFixedSize(true)
        faculty_director_students_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        studentsAdapter = FacultyDirectorStudentsAdapter(arrayListOf())
        faculty_director_students_fragment_recyclerView.adapter = studentsAdapter

        facultyDirectorStudentsViewModel = ViewModelProvider(this).get(FacultyDirectorStudentsViewModel::class.java)
        observeLiveData()
        facultyDirectorStudentsViewModel.getStudents(facultyDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculty_director_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        facultyDirectorStudentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        facultyDirectorStudentsViewModel.studentList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentList = it

                if (faculty_director_students_fragment_recyclerView.itemDecorationCount > 0)
                    faculty_director_students_fragment_recyclerView.removeItemDecorationAt(0)

                faculty_director_students_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                studentsAdapter.loadData(studentList)

                studentsAdapter.setStudentDetailsOnItemClickListener(object : FacultyDirectorStudentsAdapter.StudentDetailsOnItemClickListener{
                    override fun onItemClick(studentData: StudentsFacultyDirector) {
                        Singleton.showFacultyDirectorStudentDetailsDialog(studentData, v.context)
                    }
                })
            }
        })
    }
}