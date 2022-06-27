package com.codecanyon.umes.view.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codecanyon.umes.R
import com.codecanyon.umes.adapter.coordinator.CoordinatorStudentPapersAdapter
import com.codecanyon.umes.adapter.decoration.LinearManagerDecoration
import com.codecanyon.umes.model.coordinator.StudentPaperCoordinator
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.coordinator.CoordinatorStudentPapersViewModel
import kotlinx.android.synthetic.main.fragment_coordinator_student_papers.*

class CoordinatorStudentPapersFragment(val teacherId: Int, val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var coordinatorStudentPapersViewModel: CoordinatorStudentPapersViewModel

    private lateinit var studentPaperList: List<StudentPaperCoordinator>
    private lateinit var coordinatorStudentPapersAdapter: CoordinatorStudentPapersAdapter

    private fun init(){
        coordinator_student_papers_fragment_recyclerView.setHasFixedSize(true)
        coordinator_student_papers_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        coordinatorStudentPapersAdapter = CoordinatorStudentPapersAdapter(arrayListOf(), v.context)
        coordinator_student_papers_fragment_recyclerView.adapter = coordinatorStudentPapersAdapter

        coordinatorStudentPapersViewModel = ViewModelProvider(this).get(CoordinatorStudentPapersViewModel::class.java)
        observeLiveData()
        coordinatorStudentPapersViewModel.getStudentPapers(teacherId, teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinator_student_papers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        coordinatorStudentPapersViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        coordinatorStudentPapersViewModel.studentPaperList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentPaperList = it

                if (coordinator_student_papers_fragment_recyclerView.itemDecorationCount > 0)
                    coordinator_student_papers_fragment_recyclerView.removeItemDecorationAt(0)

                coordinator_student_papers_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                coordinatorStudentPapersAdapter.loadData(studentPaperList)
            }
        })
    }
}