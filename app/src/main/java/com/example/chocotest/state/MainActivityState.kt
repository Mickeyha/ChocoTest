package com.example.chocotest.state

import com.example.chocotest.db.entity.ChocoDataEntity

sealed class MainActivityState {
    object StartLoading : MainActivityState()
    object FinishLoading : MainActivityState()
    object NoData : MainActivityState()
    data class ShowDramaList(val dramas: List<ChocoDataEntity>) : MainActivityState()
    data class ShowPopupDialog(val notificationId: Int) : MainActivityState()
    data class OnClick(val message: MainActivityState) : MainActivityState()
    data class Error(val errorMessage : Throwable) : MainActivityState()
}