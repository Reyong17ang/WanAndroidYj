package com.example.mod_main.ui.category.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.lib_common.model.CategoryItem
import com.example.lib_framework.toast.TipsToast
import com.example.lib_network.callback.IApiErrorCallback
import com.example.lib_network.manager.ApiManager
import com.sum.network.viewmodel.BaseViewModel

class CategoryViewModel : BaseViewModel() {
    val categoryItemLiveData = MutableLiveData<MutableList<CategoryItem>?>()
    fun getCategoryData() {
        launchUIWithResult(responseBlock = {
            ApiManager.api.getCategoryData()
        }, errorCall = object : IApiErrorCallback {
            override fun onError(code: Int?, error: String?) {
                super.onError(code, error)
                TipsToast.showTips(error)
                categoryItemLiveData.value = null
            }
        }) {
            categoryItemLiveData.value = it
        }
    }
}