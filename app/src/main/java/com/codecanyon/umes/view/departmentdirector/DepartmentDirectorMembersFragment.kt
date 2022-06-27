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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorMembersAdapter
import com.codecanyon.umes.model.deparmentdirector.MemberDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorMembersViewModel
import kotlinx.android.synthetic.main.fragment_department_director_members.*

class DepartmentDirectorMembersFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorMembersViewModel: DepartmentDirectorMembersViewModel

    private lateinit var memberList: List<MemberDepartmentDirector>
    private lateinit var departmentDirectorMembersAdapter: DepartmentDirectorMembersAdapter

    private fun init(){
        department_director_members_fragment_recyclerView.setHasFixedSize(true)
        department_director_members_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorMembersAdapter = DepartmentDirectorMembersAdapter(arrayListOf(), v.context)
        department_director_members_fragment_recyclerView.adapter = departmentDirectorMembersAdapter

        departmentDirectorMembersViewModel = ViewModelProvider(this).get(DepartmentDirectorMembersViewModel::class.java)
        observeLiveData()
        departmentDirectorMembersViewModel.getMembers(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department_director_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        departmentDirectorMembersViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorMembersViewModel.memberList.observe(viewLifecycleOwner, Observer {
            it?.let {
                memberList = it

                if (department_director_members_fragment_recyclerView.itemDecorationCount > 0)
                    department_director_members_fragment_recyclerView.removeItemDecorationAt(0)

                department_director_members_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorMembersAdapter.loadData(it)
            }
        })
    }
}