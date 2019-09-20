package com.hour24.hobby.viewmodel

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.google.gson.Gson
import com.hour24.hobby.R
import com.hour24.hobby.consts.CourseConst
import com.hour24.hobby.view.extentions.toast
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.view.detail.DetailActivity
import com.hour24.hobby.view.recent.RecentViewModel
import timber.log.Timber
import java.util.*


class CourseViewModel(private val mContextProvider: ContextProvider) :
    BaseViewModel(mContextProvider) {

    private lateinit var mModel: OfflineItemModel
    private val mRecentVM: RecentViewModel by lazy {
        RecentViewModel(mContextProvider)
    }

    init {

    }

    fun setModel(model: OfflineItemModel) {
        this.mModel = model
    }

    fun getModel() = mModel

    fun getName(): String {
        val name = mModel.name.trim()
        return if (name.isEmpty()) mContextProvider.getString(R.string.main_course_name_empty) else name
    }

    /**
     * 수강정보 타이틀
     */
    fun getCourseInfoTitle(type: CourseConst.CourseInfo): String {
        return when (type) {
            CourseConst.CourseInfo.CAPACITY -> mContextProvider.getString(R.string.main_course_info_capacity_title)
            CourseConst.CourseInfo.TARGET -> mContextProvider.getString(R.string.main_course_info_target_title)
            CourseConst.CourseInfo.URL -> mContextProvider.getString(R.string.main_course_info_url_title)
        }
    }

    /**
     * 수강정보
     */
    fun getCourseInfo(type: CourseConst.CourseInfo): String {
        tryCatch {
            return when (type) {
                CourseConst.CourseInfo.CAPACITY -> {
                    String.format(
                        Locale.KOREA,
                        mContextProvider.getString(R.string.main_course_info_capacity),
                        mModel.capacity
                    )
                }
                CourseConst.CourseInfo.TARGET -> mModel.target
                CourseConst.CourseInfo.URL -> mModel.applyUrl
            }
        }

        return ""
    }

    /**
     * 기간 타이틀
     */
    fun getDateRangeTitle(type: CourseConst.DateRange): String {
        return when (type) {
            CourseConst.DateRange.APPLY -> mContextProvider.getString(R.string.main_course_date_apply_title)
            CourseConst.DateRange.COURSE -> mContextProvider.getString(R.string.main_course_date_course_title)
        }
    }

    /**
     * 기간
     */
    fun getDateRange(type: CourseConst.DateRange): String {

        tryCatch {

            var start: String
            var end: String

            if (type == CourseConst.DateRange.APPLY) {
                start = mModel.applyStartDate
                end = mModel.applyEndDate
            } else {
                start = mModel.courseStartDate
                end = mModel.courseEndDate
            }

            start = DateUtils.convertDateFormat(
                start,
                DateUtils.YYYYMMDD,
                DateUtils.YYYY_MM_DD
            )

            end = DateUtils.convertDateFormat(
                end,
                DateUtils.YYYYMMDD,
                DateUtils.YYYY_MM_DD
            )

            return String.format(
                Locale.KOREA,
                mContextProvider.getString(R.string.main_course_date),
                start,
                end
            )
        }

        return ""
    }


    fun onClick(v: View, model: OfflineItemModel) {
        when (v.id) {
            R.id.ll_main -> {
                tryCatch {
                    // 상세 이동
                    val intent = Intent(v.context, DetailActivity::class.java)
                    intent.putExtra(model::class.java.name, model)
                    v.context.startActivity(intent)

                    // 최근 본 강의에 저장
                    mRecentVM.insert(model.id, Gson().toJson(model))
                }
            }
        }
    }

    fun onClick(v: View, model: OfflineItemModel, type: CourseConst.CourseInfo) {
        when (v.id) {
            R.id.tv_desc -> {

                if (type != CourseConst.CourseInfo.URL) {
                    return
                }

                val url = model.applyUrl
                Timber.d("url : ${model.applyUrl}")

                if (url.isNotEmpty()) {
                    val builder = CustomTabsIntent.Builder().apply {
                        setToolbarColor(mContextProvider.getColor(R.color.colorPrimary))
                        setShowTitle(true)
                        addDefaultShareMenuItem()
                    }

                    val intent = builder.build()
                    intent.launchUrl(v.context, Uri.parse(model.applyUrl))
                } else {
                    mContextProvider.getContext().toast(R.string.main_empty_url)
                }
            }
        }
    }
}