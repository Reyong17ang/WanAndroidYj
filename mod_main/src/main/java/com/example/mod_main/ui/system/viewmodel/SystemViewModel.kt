package com.example.mod_main.ui.system.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lib_framework.toast.TipsToast
import com.example.lib_network.callback.IApiErrorCallback
import com.example.lib_network.manager.ApiManager
import com.sum.common.model.SystemList
import com.sum.network.viewmodel.BaseViewModel

class SystemViewModel : BaseViewModel() {
    val errorListLiveData: MutableLiveData<String?> = MutableLiveData()

    /**
     * 获取体系列表
     */
    fun getSystemList(): LiveData<MutableList<SystemList>> {
        return liveData {
            val data = safeApiCallWithResult(errorCall = object : IApiErrorCallback {
                override fun onError(code: Int?, error: String?) {
                    TipsToast.showTips(error)
                    errorListLiveData.value = error
                }
            }) {
                ApiManager.api.getSystemList()
            }
            data?.let {
                emit(it)
            } ?: kotlin.run {
                errorListLiveData.value = ""
            }
        }
    }
}