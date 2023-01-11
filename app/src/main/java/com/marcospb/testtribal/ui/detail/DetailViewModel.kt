package com.marcospb.testtribal.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.testtribal.data.models.DetailResponse
import com.marcospb.testtribal.data.repository.ChuckNorrisRepository
import com.marcospb.testtribal.utils.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: ChuckNorrisRepository) :
    ViewModel() {


    private val _detailState = MutableLiveData<ResourceState<DetailResponse>>()
    val detailState = _detailState

    fun getDetailByCategory(category: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getDetail(category)

                if (result.isSuccessful) {
                    _detailState.postValue(ResourceState.SUCCESS(result.body()!!))
                }

            } catch (ex: java.lang.Exception) {
                _detailState.postValue(ResourceState.ERROR(null, ex))
            }

        }

    }


}