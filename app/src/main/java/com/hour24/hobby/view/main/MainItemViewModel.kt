package com.hour24.hobby.view.main

import android.content.Intent
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.hour24.hobby.R
import com.hour24.hobby.consts.CourseConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.utils.tryCatch
import java.util.*
import android.net.Uri
import com.hour24.hobby.extentions.toast
import com.hour24.hobby.view.detail.DetailActivity
import timber.log.Timber


class MainItemViewModel(private val mContextProvider: ContextProvider) {

    private lateinit var model: OfflineItemModel

    fun setModel(model: OfflineItemModel) {
        this.model = model
    }

    fun getModel() = model

    fun getName(): String {
        val name = model.name.trim()
        return if (name.isEmpty()) mContextProvider.getString(R.string.main_course_name_empty) else name
    }

    /**
     * 수강정보 타이틀
     */
    fun getCourseInfoTitle(type: CourseConst.CourseInfo): String {
        return if (type == CourseConst.CourseInfo.CAPACITY) {
            mContextProvider.getString(R.string.main_course_info_capacity_title)
        } else {
            mContextProvider.getString(R.string.main_course_info_target_title)
        }
    }

    /**
     * 수강정보
     */
    fun getCourseInfo(type: CourseConst.CourseInfo): String {
        tryCatch {
            return if (type == CourseConst.CourseInfo.CAPACITY) {
                String.format(
                    Locale.KOREA,
                    mContextProvider.getString(R.string.main_course_info_capacity),
                    model.capacity
                )
            } else {
                model.target
            }
        }
        return ""
    }

    /**
     * 기간 타이틀
     */
    fun getDateRangeTitle(type: CourseConst.DateRange): String {
        return if (type == CourseConst.DateRange.APPLY) {
            mContextProvider.getString(R.string.main_course_date_apply_title)
        } else {
            mContextProvider.getString(R.string.main_course_date_course_title)
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
                start = model.applyStartDate
                end = model.applyEndDate
            } else {
                start = model.courseStartDate
                end = model.courseEndDate
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
                    Timber.d("url : ${model.applyUrl}")

                    val intent = Intent(v.context, DetailActivity::class.java)
                    intent.putExtra(model::class.java.name, model)
                    v.context.startActivity(intent)

//                    val url = model.applyUrl
//
//                    if (url.isNotEmpty()) {
//                        val builder = CustomTabsIntent.Builder().apply {
//                            setToolbarColor(mContextProvider.getColor(R.color.colorPrimary))
//                            setShowTitle(true)
//                            addDefaultShareMenuItem()
//                        }
//
//                        val intent = builder.build()
//                        intent.launchUrl(v.context, Uri.parse(model.applyUrl))
//                    } else {
//                        mContextProvider.getContext().toast(R.string.main_empty_url)
//                    }
                }
            }
        }
    }
}