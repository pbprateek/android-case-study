package com.target.targetcasestudy.ui.deal_listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.core.utils.composeView
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent
import com.target.targetcasestudy.ui.deal_listing.ui.DealListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealListFragment : Fragment() {

//  override fun onCreateView(
//    inflater: LayoutInflater, container: ViewGroup?,
//    savedInstanceState: Bundle?
//  ): View? {
//    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)
//
//    view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())
//    view.findViewById<RecyclerView>(R.id.recycler_view).adapter = DealItemAdapter()
//
//    return view
//  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = composeView {
        MaterialTheme {
            DealListScreen {
              //
            }
        }
    }
}
