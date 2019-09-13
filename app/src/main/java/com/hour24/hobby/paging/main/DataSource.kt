package com.hour24.hobby.paging.main

import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import com.hour24.hobby.model.OfflineItemModel

/**
 * PageKeyedDataSource : 페이지의 키 값을 기반으로 페이징을 수행하기 위해 Next/Previous 필드를 가지고 있는 경우 이용합니다.
 * ItemKeyedDataSource : 페이지 아이템의 키 값을 기반으로 페이징을 수행합니다.
 * PositionalDataSource : 페이지 번호나 offset을 이용해서 페이징을 수행합니다.
 */

class DataSource : PositionalDataSource<OfflineItemModel>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<OfflineItemModel>
    ) {

    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<OfflineItemModel>) {

    }

}