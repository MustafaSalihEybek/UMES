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
import com.codecanyon.umes.adapter.departmentdirector.DepartmentDirectorInstitutionsAdapter
import com.codecanyon.umes.model.deparmentdirector.InstitutionDepartmentDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.departmentdirector.DepartmentDirectorInstitutionsViewModel
import kotlinx.android.synthetic.main.fragment_department_director_institutions.*

class DepartmentDirectorInstitutionsFragment(val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var departmentDirectorInstitutionsViewModel: DepartmentDirectorInstitutionsViewModel

    private lateinit var institutionList: List<InstitutionDepartmentDirector>
    private lateinit var departmentDirectorInstitutionsAdapter: DepartmentDirectorInstitutionsAdapter

    private fun init(){
        department_director_institutions_recyclerView.setHasFixedSize(true)
        department_director_institutions_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        departmentDirectorInstitutionsAdapter = DepartmentDirectorInstitutionsAdapter(arrayListOf(), v.context)
        department_director_institutions_recyclerView.adapter = departmentDirectorInstitutionsAdapter

        departmentDirectorInstitutionsViewModel = ViewModelProvider(this).get(DepartmentDirectorInstitutionsViewModel::class.java)
        observeLiveData()
        departmentDirectorInstitutionsViewModel.getInstitutions(teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_department_director_institutions,
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
        departmentDirectorInstitutionsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        departmentDirectorInstitutionsViewModel.institutionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                institutionList = it

                if (department_director_institutions_recyclerView.itemDecorationCount > 0)
                    department_director_institutions_recyclerView.removeItemDecorationAt(0)

                department_director_institutions_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                departmentDirectorInstitutionsAdapter.loadData(it)
            }
        })
    }
}