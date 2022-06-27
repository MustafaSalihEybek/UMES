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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorMembersActivationAdapter
import com.codecanyon.umes.model.deparmentdirector.MemberActivationDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorMemberActivationViewModel
import kotlinx.android.synthetic.main.fragment_department_director_member_activation.*

class DepartmentDirectorMemberActivationFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorMemberActivationViewModel: DepartmentDirectorMemberActivationViewModel

    private lateinit var memberActivationList: List<MemberActivationDepartmentDirector>
    private lateinit var departmentDirectorMembersActivationAdapter: DepartmentDirectorMembersActivationAdapter

    private fun init(){
        department_director_member_activation_fragment_recyclerView.setHasFixedSize(true)
        department_director_member_activation_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorMembersActivationAdapter = DepartmentDirectorMembersActivationAdapter(
            arrayListOf(),
            v.context
        )
        department_director_member_activation_fragment_recyclerView.adapter = departmentDirectorMembersActivationAdapter

        departmentDirectorMemberActivationViewModel = ViewModelProvider(this).get(DepartmentDirectorMemberActivationViewModel::class.java)
        observeLiveData()
        departmentDirectorMemberActivationViewModel.getMembersActivation(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_department_director_member_activation,
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
        departmentDirectorMemberActivationViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorMemberActivationViewModel.memberActivationList.observe(viewLifecycleOwner, Observer {
            it?.let {
                memberActivationList = it

                if (department_director_member_activation_fragment_recyclerView.itemDecorationCount > 0)
                    department_director_member_activation_fragment_recyclerView.removeItemDecorationAt(0)

                department_director_member_activation_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorMembersActivationAdapter.loadData(memberActivationList)
            }
        })
    }
}