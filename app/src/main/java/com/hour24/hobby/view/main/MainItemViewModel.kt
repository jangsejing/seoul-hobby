package com.hour24.hobby.view.main

import android.content.Intent
import android.view.View
import com.hour24.hobby.R
import com.hour24.hobby.consts.CourseConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.view.detail.DetailActivity
import java.util.*

class MainItemViewModel(private val contextProvider: ContextProvider) {

    private lateinit var model: OfflineItemModel

    fun setModel(model: OfflineItemModel) {
        this.model = model
    }

    fun getModel() = model

    fun getName(): String {
        val name = model.name.trim()
        return if (name.isEmpty()) contextProvider.getString(R.string.main_course_name_empty) else name
    }

    /**
     * 수강정보 타이틀
     */
    fun getCourseInfoTitle(type: CourseConst.CourseInfo): String {
        return if (type == CourseConst.CourseInfo.CAPACITY) {
            contextProvider.getString(R.string.main_course_info_capacity_title)
        } else {
            contextProvider.getString(R.string.main_course_info_target_title)
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
                    contextProvider.getString(R.string.main_course_info_capacity),
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
            contextProvider.getString(R.string.main_course_date_apply_title)
        } else {
            contextProvider.getString(R.string.main_course_date_course_title)
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
                contextProvider.getString(R.string.main_course_date),
                start,
                end
            )
        }

        return ""
    }

    fun onClick(v: View, model: OfflineItemModel) {
        when (v.id) {
            R.id.ll_main -> {
                val intent = Intent(v.context, DetailActivity::class.java)
                intent.putExtra(OfflineItemModel::class.java.name, model)
                v.context.startActivity(intent)
            }
        }
    }
}