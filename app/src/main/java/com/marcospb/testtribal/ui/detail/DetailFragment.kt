package com.marcospb.testtribal.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.marcospb.testtribal.R
import com.marcospb.testtribal.databinding.FragmentDetailBinding
import com.marcospb.testtribal.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class DetailFragment : Fragment() {


    lateinit var binding: FragmentDetailBinding


    private val viewModel by viewModels<DetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val category = DetailFragmentArgs.fromBundle(requireArguments()).categoryName
        viewModel.getDetailByCategory(category)



        viewModel.detailState.observe(viewLifecycleOwner, Observer { status ->

            when (status) {

                is ResourceState.SUCCESS -> {

                    Glide.with(requireContext())
                        .load(status.data.iconUrl)
                        .into(binding.image)

                    binding.value.text = status.data.value
                    binding.updateAt.text = status.data.updatedAt

                }

                is ResourceState.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${status.error.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResourceState.LOADING -> {


                }
            }
        })
    }


}