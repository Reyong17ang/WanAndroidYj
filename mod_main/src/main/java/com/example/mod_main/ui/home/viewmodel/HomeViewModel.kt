package com.example.mod_main.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.lib_common.model.Banner
import com.example.lib_framework.toast.TipsToast
import com.example.lib_network.manager.ApiManager
import com.example.mod_main.repository.HomeRepository
import com.sum.common.model.ProjectSubInfo
import com.sum.common.model.ProjectTabItem
import com.sum.network.flow.requestFlow
import com.sum.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    val projectItemLiveData = MutableLiveData<MutableList<ProjectSubInfo>?>()
    val homeRepository by lazy { HomeRepository() }
    val bannersLiveData = MutableLiveData<MutableList<Banner>?>()

    /**
     * 首页banner
     */
    fun getBannerList(): LiveData<MutableList<Banner>?> {
//        launchUI(errorBlock = { code, errorMsg ->
//            TipsToast.showTips(errorMsg)
//            bannersLiveData.value = null
//        }) {
//            val data = homeRepository.getHomeBanner()
//            bannersLiveData.value = data
//        }
//        return bannersLiveData
        //通过flow来请求
        viewModelScope.launch {
            val data = requestFlow(requestCall = {
                ApiManager.api.getHomeBanner()
            }, errorBlock = { code, error ->
                TipsToast.showTips(error)
                bannersLiveData.value = null
            })
            bannersLiveData.value = data
        }
        return bannersLiveData
    }

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