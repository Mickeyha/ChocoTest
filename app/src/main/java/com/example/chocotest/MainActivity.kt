package com.example.chocotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chocotest.db.entity.ChocoDataEntity
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val presenter: MainActivityPresenter by inject()
    internal lateinit var onDramaItemClicked: PublishSubject<ChocoDataEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()
    }
}
