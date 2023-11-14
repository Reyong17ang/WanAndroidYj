package com.example.mod_main.repository

import com.example.lib_network.manager.ApiManager
import com.example.lib_network.repository.BaseRepository
import com.sum.common.model.ProjectSubInfo
import com.sum.common.model.ProjectSubList
import com.sum.common.model.ProjectTabItem

class HomeRepository : BaseRepository() {
    /**
     * 项目tab
     */
    suspend fun getProjectTab(): MutableList<ProjectTabItem>? {
        return requestResponse {
            ApiManager.api.getProjectTab()
        }
    }

    /**
     * 项目列表
     * @param page
     * @param cid
     */
    suspend fun getProjectList(page: Int, cid: Int): ProjectSubList? {
        return requestResponse {
            ApiManager.api.getProjectList(page, cid)
        }
    }
}