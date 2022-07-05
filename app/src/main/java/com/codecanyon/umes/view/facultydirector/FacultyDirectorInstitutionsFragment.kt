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
import com.codecanyon.umes.adapter.facultydirector.FacultyDirectorInstitutionsAdapter
import com.codecanyon.umes.model.facultydirector.InstitutionFacultyDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.facultydirector.FacultyDirectorInstitutionsViewModel
import kotlinx.android.synthetic.main.fragment_faculty_director_institutions.*

class FacultyDirectorInstitutionsFragment(val facultyDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var facultyDirectorInstitutionsViewModel: FacultyDirectorInstitutionsViewModel

    private lateinit var institutionList: List<InstitutionFacultyDirector>
    private lateinit var institutionsAdapter: FacultyDirectorInstitutionsAdapter

    private fun init(){
        faculty_director_institutions_fragment_recyclerView.setHasFixedSize(true)
        faculty_director_institutions_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        institutionsAdapter = FacultyDirectorInstitutionsAdapter(arrayListOf())
        faculty_director_institutions_fragment_recyclerView.adapter = institutionsAdapter

        facultyDirectorInstitutionsViewModel = ViewModelProvider(this).get(FacultyDirectorInstitutionsViewModel::class.java)
        observeLiveData()
        facultyDirectorInstitutionsViewModel.getInstitutions(facultyDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculty_director_institutions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        facultyDirectorInstitutionsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        facultyDirectorInstitutionsViewModel.institutionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                institutionList = it

                if (faculty_director_institutions_fragment_recyclerView.itemDecorationCount > 0)
                    faculty_director_institutions_fragment_recyclerView.removeItemDecorationAt(0)

                faculty_director_institutions_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                institutionsAdapter.loadData(institutionList)
            }
        })
    }
}