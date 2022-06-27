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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorStudentPapersAdapter
import com.codecanyon.umes.model.deparmentdirector.StudentPaperDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorStudentPaperViewModel
import kotlinx.android.synthetic.main.fragment_department_director_documents.*

class DepartmentDirectorDocumentsFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorStudentPaperViewModel: DepartmentDirectorStudentPaperViewModel

    private lateinit var studentPaperList: List<StudentPaperDepartmentDirector>
    private lateinit var departmentDirectorStudentPapersAdapter: DepartmentDirectorStudentPapersAdapter

    private fun init(){
        department_director_student_papers_recyclerView.setHasFixedSize(true)
        department_director_student_papers_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorStudentPapersAdapter = DepartmentDirectorStudentPapersAdapter(arrayListOf(), v.context)
        department_director_student_papers_recyclerView.adapter = departmentDirectorStudentPapersAdapter

        departmentDirectorStudentPaperViewModel = ViewModelProvider(this).get(DepartmentDirectorStudentPaperViewModel::class.java)
        observeLiveData()
        departmentDirectorStudentPaperViewModel.getStudentPapers(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department_director_documents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        departmentDirectorStudentPaperViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorStudentPaperViewModel.studentPaperList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentPaperList = it

                if (department_director_student_papers_recyclerView.itemDecorationCount > 0)
                    department_director_student_papers_recyclerView.removeItemDecorationAt(0)

                department_director_student_papers_recyclerView.addItemDecoration(LinearManagerDecoration(
                    Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorStudentPapersAdapter.loadData(it)
            }
        })
    }
}