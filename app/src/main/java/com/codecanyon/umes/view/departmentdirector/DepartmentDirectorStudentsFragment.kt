package com.codecanyon.umes.view.departmentdirector

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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorStudentsAdapter
import com.codecanyon.umes.model.deparmentdirector.StudentDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorStudentsViewModel
import kotlinx.android.synthetic.main.fragment_department_director_students.*

class DepartmentDirectorStudentsFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorStudentsViewModel: DepartmentDirectorStudentsViewModel

    private lateinit var studentList: List<StudentDepartmentDirector>
    private lateinit var departmentDirectorStudentsAdapter : DepartmentDirectorStudentsAdapter

    private fun init(){
        department_director_students_fragment_recyclerView.setHasFixedSize(true)
        department_director_students_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorStudentsAdapter = DepartmentDirectorStudentsAdapter(arrayListOf(), v.context)
        department_director_students_fragment_recyclerView.adapter = departmentDirectorStudentsAdapter

        departmentDirectorStudentsViewModel = ViewModelProvider(this).get(
            DepartmentDirectorStudentsViewModel::class.java)
        observeLiveData()
        departmentDirectorStudentsViewModel.getStudents(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department_director_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        departmentDirectorStudentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorStudentsViewModel.studentList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentList = it

                if (department_director_students_fragment_recyclerView.itemDecorationCount > 0)
                    department_director_students_fragment_recyclerView.removeItemDecorationAt(0)

                department_director_students_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorStudentsAdapter.loadData(studentList)
            }
        })
    }
}