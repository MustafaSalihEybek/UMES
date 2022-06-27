package com.codecanyon.umes.view.institutiondirector

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
import com.codecanyon.umes.adapter.institutiondirector.InstitutionDirectorStudentsAdapter
import com.codecanyon.umes.model.institutiondirector.StudentsInstitutionDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.institutiondirector.InstitutionDirectorStudentsViewModel
import kotlinx.android.synthetic.main.fragment_institution_director_students.*

class InstitutionDirectorStudentsFragment(val institutionDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var institutionDirectorStudentsViewModel: InstitutionDirectorStudentsViewModel

    private lateinit var studentsAdapter: InstitutionDirectorStudentsAdapter
    private lateinit var studentList: List<StudentsInstitutionDirector>

    private fun init(){
        institution_director_students_fragment_recyclerView.setHasFixedSize(true)
        institution_director_students_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        studentsAdapter = InstitutionDirectorStudentsAdapter(arrayListOf())
        institution_director_students_fragment_recyclerView.adapter = studentsAdapter

        institutionDirectorStudentsViewModel = ViewModelProvider(this).get(InstitutionDirectorStudentsViewModel::class.java)
        observeLiveData()
        institutionDirectorStudentsViewModel.getStudents(institutionDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institution_director_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        institutionDirectorStudentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        institutionDirectorStudentsViewModel.studentList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentList = it

                if (institution_director_students_fragment_recyclerView.itemDecorationCount > 0)
                    institution_director_students_fragment_recyclerView.removeItemDecorationAt(0)

                institution_director_students_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                studentsAdapter.loadData(it)
            }
        })
    }
}