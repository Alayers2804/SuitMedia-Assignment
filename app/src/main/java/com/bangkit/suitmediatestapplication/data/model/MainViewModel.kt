package com.bangkit.suitmediatestapplication.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bangkit.suitmediatestapplication.data.api.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _selectedUsername = MutableLiveData<String>()
    val selectedUsername: LiveData<String> get() = _selectedUsername

    val userList = MutableLiveData<List<DataItem>?>()
    var totalPages = 0

    fun getStory(page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiConfig.getApi().getData(page, perPage).execute()
                }
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    totalPages = response.body()?.totalPages ?: 0
                    userList.postValue(data)
                } else {
                    // Handle error response
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun setSelectedUsername(username: String) {
        _selectedUsername.value = username
    }
}
