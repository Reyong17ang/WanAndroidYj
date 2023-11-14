package com.example.mod_main.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lib_framework.toast.TipsToast
import com.example.mod_main.repository.HomeRepository
import com.sum.common.model.ProjectSubInfo
import com.sum.common.model.ProjectTabItem
import com.sum.network.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel() {

    val projectItemLiveData = MutableLiveData<MutableList<ProjectSubInfo>?>()
    val homeRepository by lazy { HomeRepository() }
    fun getProjectTab(): LiveData<MutableList<ProjectTabItem>?> {
        return liveData {
            val response = safeApiCall(errorBlock = { _, errorMessage ->
                TipsToast.showTips(errorMessage)
            }) {
                homeRepository.getProjectTab()
            }
            emit(response)
        }
    }

    fun getProjectList(page: Int, cid: Int): LiveData<MutableList<ProjectSubInfo>?> {
        launchUI(errorBlock = { code, errorMsg ->
            TipsToast.showTips(errorMsg)
            projectItemLiveData.value = null
        }) {
            val data = homeRepository.getProjectList(page, cid)
            projectItemLiveData.value = data?.datas
        }
        return projectItemLiveData
    }
}