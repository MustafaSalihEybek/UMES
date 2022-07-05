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
import com.codecanyon.umes.adapter.facultydirector.FacultyDirectorMembersAdapter
import com.codecanyon.umes.model.facultydirector.MemberFacultyDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.facultydirector.FacultyDirectorMembersViewModel
import kotlinx.android.synthetic.main.fragment_faculty_director_members.*

class FacultyDirectorMembersFragment(val facultyDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var facultyDirectorMembersViewModel: FacultyDirectorMembersViewModel

    private lateinit var memberList: List<MemberFacultyDirector>
    private lateinit var membersAdapter: FacultyDirectorMembersAdapter

    private fun init(){
        faculty_director_members_fragment_recyclerView.setHasFixedSize(true)
        faculty_director_members_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        membersAdapter = FacultyDirectorMembersAdapter(arrayListOf())
        faculty_director_members_fragment_recyclerView.adapter = membersAdapter

        facultyDirectorMembersViewModel = ViewModelProvider(this).get(FacultyDirectorMembersViewModel::class.java)
        observeLiveData()
        facultyDirectorMembersViewModel.getMembers(facultyDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculty_director_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        facultyDirectorMembersViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        facultyDirectorMembersViewModel.memberList.observe(viewLifecycleOwner, Observer {
            it?.let {
                memberList = it

                if (faculty_director_members_fragment_recyclerView.itemDecorationCount > 0)
                    faculty_director_members_fragment_recyclerView.removeItemDecorationAt(0)

                faculty_director_members_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                membersAdapter.loadData(memberList)
            }
        })
    }
}