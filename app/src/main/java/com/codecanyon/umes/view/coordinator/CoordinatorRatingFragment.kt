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
import com.codecanyon.umes.adapter.coordinator.CoordinatorRatingsAdapter
import com.codecanyon.umes.adapter.decoration.LinearManagerDecoration
import com.codecanyon.umes.model.coordinator.RatingCoordinator
import com.codecanyon.umes.util.Singleton
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.coordinator.CoordinatorRatingViewModel
import kotlinx.android.synthetic.main.fragment_coordinator_rating.*

class CoordinatorRatingFragment(val teacherId: Int, val teacherDepartmentId: Int) : Fragment() {
    private lateinit var v: View
    private lateinit var coordinatorRatingViewModel: CoordinatorRatingViewModel

    private lateinit var ratingList: List<RatingCoordinator>
    private lateinit var coordinatorRatingsAdapter: CoordinatorRatingsAdapter

    private fun init(){
        coordinator_rating_fragment_recyclerView.setHasFixedSize(true)
        coordinator_rating_fragment_recyclerView.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        coordinatorRatingsAdapter = CoordinatorRatingsAdapter(arrayListOf(), v.context)
        coordinator_rating_fragment_recyclerView.adapter = coordinatorRatingsAdapter

        coordinatorRatingViewModel = ViewModelProvider(this).get(CoordinatorRatingViewModel::class.java)
        observeLiveData()
        coordinatorRatingViewModel.getRatings(teacherId, teacherDepartmentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinator_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        coordinatorRatingViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        coordinatorRatingViewModel.ratingList.observe(viewLifecycleOwner, Observer {
            it?.let {
                ratingList = it

                if (coordinator_rating_fragment_recyclerView.itemDecorationCount > 0)
                    coordinator_rating_fragment_recyclerView.removeItemDecorationAt(0)

                coordinator_rating_fragment_recyclerView.addItemDecoration(LinearManagerDecoration(Singleton.VERTICAL_ITEM_SIZE, it.size))
                coordinatorRatingsAdapter.loadData(ratingList)
            }
        })
    }
}