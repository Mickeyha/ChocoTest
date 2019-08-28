package com.example.chocotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.chocotest.db.entity.ChocoDataEntity
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chocotest.adapter.DramaListAdapter
import com.example.chocotest.state.MainActivityState
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val onDramaItemClicked = PublishSubject.create<ChocoDataEntity>()
    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    private lateinit var dramaListAdapter : DramaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()
    }

    fun render(state: MainActivityState) {
        when(state) {
            is MainActivityState.StartLoading -> {
                Timber.d("MainActivityState: StartLoading")
                progress_bar.visibility = View.VISIBLE
            }
            is MainActivityState.FinishLoading -> {
                Timber.d("MainActivityState: FinishLoading")
                progress_bar.visibility = View.GONE
            }
            is MainActivityState.NoData -> {
                Timber.d("MainActivityState: NoData")

                recyclerView.visibility = View.GONE
            }
            is MainActivityState.ShowDramaList -> {
                Timber.d("MainActivityState: ShowDramaList")

                recyclerView.visibility = View.VISIBLE

                dramaListAdapter = DramaListAdapter(state.dramas)
                dramaListAdapter.onClick = onDramaItemClicked
                recyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(context,  RecyclerView.VERTICAL, false)
                    adapter = dramaListAdapter
                }
            }
            is MainActivityState.ShowPopupDialog -> {
                Timber.d("MainActivityState: ShowPopupDialog")

            }
            is MainActivityState.Error -> {
            }
        }
    }
}
