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
import com.codecanyon.umes.adapter.institutiondirector.InstitutionDirectorReportsAdapter
import com.codecanyon.umes.model.institutiondirector.ReportsInstitutionDirector
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.institutiondirector.InstitutionDirectorDocumentsViewModel
import kotlinx.android.synthetic.main.fragment_institution_director_documents.*

class InstitutionDirectorDocumentsFragment(val institutionDirectorId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var institutionDirectorDocumentsViewModel: InstitutionDirectorDocumentsViewModel

    private lateinit var reportsAdapter: InstitutionDirectorReportsAdapter
    private lateinit var reportList: List<ReportsInstitutionDirector>

    private fun init(){
        institution_director_documents_fragment_recyclerView.setHasFixedSize(true)
        institution_director_documents_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        reportsAdapter = InstitutionDirectorReportsAdapter(arrayListOf())
        institution_director_documents_fragment_recyclerView.adapter = reportsAdapter

        institutionDirectorDocumentsViewModel = ViewModelProvider(this).get(InstitutionDirectorDocumentsViewModel::class.java)
        observeLiveData()
        institutionDirectorDocumentsViewModel.getReports(institutionDirectorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institution_director_documents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        institutionDirectorDocumentsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        institutionDirectorDocumentsViewModel.reportList.observe(viewLifecycleOwner, Observer {
            it?.let {
                reportList = it

                if (institution_director_documents_fragment_recyclerView.itemDecorationCount > 0)
                    institution_director_documents_fragment_recyclerView.removeItemDecorationAt(0)

                institution_director_documents_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                reportsAdapter.loadData(it)
            }
        })
    }
}