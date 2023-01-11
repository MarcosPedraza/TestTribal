package com.marcospb.testtribal.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcospb.testtribal.R
import com.marcospb.testtribal.databinding.CategoryListBinding
import com.marcospb.testtribal.ui.categories.adapter.CategoryAdapter
import com.marcospb.testtribal.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [CategoryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class CategoryListFragment : Fragment() {


    private lateinit var binding: CategoryListBinding

    private val viewModel by viewModels<CategoryListViewModel>()


    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategoryList()

        initViews()
        viewModel.categoryListState.observe(viewLifecycleOwner, Observer { state ->

            when (state) {
                is ResourceState.SUCCESS -> {
                    adapter.submitList(state.data)
                }

                is ResourceState.ERROR -> {

                    Toast.makeText(
                        requireContext(),
                        "Error: ${state.error.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()


                }
                is ResourceState.LOADING -> {
                }

            }


        })


    }

    private fun initViews() {

        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryAdapter { category ->
            val dir = CategoryListFragmentDirections.actionCategoryListFragmentToDetailFragment2(category)
            findNavController().navigate(dir)
        }


        binding.rvCategory.adapter = adapter


    }


}