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
import com.codecanyon.umes.adapter.coordinator.CoordinatorInstitutionsAdapter
import com.codecanyon.umes.adapter.decoration.LinearManagerDecoration
import com.codecanyon.umes.model.coordinator.InstitutionCoordinator
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.coordinator.CoordinatorInstitutionsViewModel
import kotlinx.android.synthetic.main.fragment_coordinator_institutions.*

class CoordinatorInstitutionsFragment(val departmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var coordinatorInstitutionsViewModel: CoordinatorInstitutionsViewModel

    private lateinit var coordinatorInstitutionsAdapter: CoordinatorInstitutionsAdapter
    private lateinit var institutionsList: List<InstitutionCoordinator>

    private fun init(){
        coordinator_institutions_fragment_recyclerView.setHasFixedSize(true)
        coordinator_institutions_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        coordinatorInstitutionsAdapter = CoordinatorInstitutionsAdapter(arrayListOf(), v.context)
        coordinator_institutions_fragment_recyclerView.adapter = coordinatorInstitutionsAdapter

        coordinatorInstitutionsViewModel = ViewModelProvider(this).get(CoordinatorInstitutionsViewModel::class.java)
        observeLiveData()
        coordinatorInstitutionsViewModel.getInstitutions(departmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinator_institutions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        coordinatorInstitutionsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        coordinatorInstitutionsViewModel.institutionsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                institutionsList = it

                if (coordinator_institutions_fragment_recyclerView.itemDecorationCount > 0)
                    coordinator_institutions_fragment_recyclerView.removeItemDecorationAt(0)

                coordinator_institutions_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                coordinatorInstitutionsAdapter.loadData(institutionsList)
            }
        })
    }
}