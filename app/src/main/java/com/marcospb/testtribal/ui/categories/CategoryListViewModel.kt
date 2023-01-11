package com.marcospb.testtribal.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.testtribal.data.models.DetailResponse
import com.marcospb.testtribal.data.repository.ChuckNorrisRepository
import com.marcospb.testtribal.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryListViewModel @Inject constructor(private val repository: ChuckNorrisRepository) :
    ViewModel() {


    private val _categoryListState = MutableLiveData<ResourceState<List<String>>>()
    val categoryListState = _categoryListState



    fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                _categoryListState.postValue(
                    ResourceState.LOADING(null)
                )

                val result = repository.getCategoryList()

                if (result.isSuccessful) {
                    //result.body()
                    _categoryListState.postValue(
                        ResourceState.SUCCESS(
                            result.body()?: listOf()
                        )
                    )
                }
            } catch (ex: Exception) {

                _categoryListState.postValue(
                    ResourceState.ERROR(null, ex)
                )
            }

        }

    }





}