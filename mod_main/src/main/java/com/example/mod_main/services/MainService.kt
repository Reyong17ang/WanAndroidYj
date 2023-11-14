package com.example.mod_main.services

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_common.constant.MAIN_SERVICE_HOME
import com.example.mod_main.MainActivity
import com.example.mod_main.ui.ArticleDetailActivity
import com.sum.common.service.IMainService

@Route(path = MAIN_SERVICE_HOME)
class MainService:IMainService {
    override fun toMain(context: Context, index: Int) {
        MainActivity.start(context, index)
    }

    override fun toArticleDetail(context: Context, url: String, title: String) {
        ArticleDetailActivity.start(context, url, title)
    }

    override fun init(context: Context?) {
    }
}