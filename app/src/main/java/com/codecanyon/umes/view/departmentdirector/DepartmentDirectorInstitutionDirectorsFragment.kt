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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorInstitutionDirectorsAdapter
import com.codecanyon.umes.model.deparmentdirector.InstitutionDirectorDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorInstitutionDirectorsViewModel
import kotlinx.android.synthetic.main.fragment_department_director_institution_directors.*

class DepartmentDirectorInstitutionDirectorsFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorInstitutionDirectorsViewModel: DepartmentDirectorInstitutionDirectorsViewModel

    private lateinit var institutionDirectorList: List<InstitutionDirectorDepartmentDirector>
    private lateinit var departmentDirectorInstitutionDirectorsAdapter: DepartmentDirectorInstitutionDirectorsAdapter

    private fun init(){
        department_director_institution_directors_fragment_recyclerView.setHasFixedSize(true)
        department_director_institution_directors_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorInstitutionDirectorsAdapter = DepartmentDirectorInstitutionDirectorsAdapter(
            arrayListOf(), v.context)
        department_director_institution_directors_fragment_recyclerView.adapter = departmentDirectorInstitutionDirectorsAdapter

        departmentDirectorInstitutionDirectorsViewModel = ViewModelProvider(this).get(DepartmentDirectorInstitutionDirectorsViewModel::class.java)
        observeLiveData()
        departmentDirectorInstitutionDirectorsViewModel.getInstitutionDirectors(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_department_director_institution_directors,
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
        departmentDirectorInstitutionDirectorsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorInstitutionDirectorsViewModel.institutionDirectorList.observe(viewLifecycleOwner, Observer {
            it?.let {
                institutionDirectorList = it

                if (department_director_institution_directors_fragment_recyclerView.itemDecorationCount > 0)
                    department_director_institution_directors_fragment_recyclerView.removeItemDecorationAt(0)

                department_director_institution_directors_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorInstitutionDirectorsAdapter.loadData(institutionDirectorList)
            }
        })
    }
}