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
import com.codecanyon.umes.adapter.coordinator.CoordinatorStudentsAdapter
import com.codecanyon.umes.adapter.decoration.LinearManagerDecoration
import com.codecanyon.umes.model.coordinator.StudentCoordinator
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.coordinator.CoordinatorStudentsViewModel
import kotlinx.android.synthetic.main.fragment_coordinator_students.*

class CoordinatorStudentsFragment(val coordinatorId: Int, val departmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var coordinatorStudentsViewModel: CoordinatorStudentsViewModel

    private lateinit var studentList: List<StudentCoordinator>
    private lateinit var coordinatorStudentsAdapter: CoordinatorStudentsAdapter

    private fun init(){
        coordinator_students_fragment_recyclerView.setHasFixedSize(true)
        coordinator_students_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        coordinatorStudentsAdapter = CoordinatorStudentsAdapter(arrayListOf(), v.context)
        coordinator_students_fragment_recyclerView.adapter = coordinatorStudentsAdapter

        coordinatorStudentsViewModel = ViewModelProvider(this).get(CoordinatorStudentsViewModel::class.java)
        observeLiveData()
        coordinatorStudentsViewModel.getStudents(coordinatorId, departmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinator_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        coordinatorStudentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        coordinatorStudentsViewModel.studentList.observe(viewLifecycleOwner, Observer {
            it?.let {
                studentList = it

                if (coordinator_students_fragment_recyclerView.itemDecorationCount > 0)
                    coordinator_students_fragment_recyclerView.removeItemDecorationAt(0)

                coordinator_students_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                coordinatorStudentsAdapter.loadData(studentList)
            }
        })
    }
}