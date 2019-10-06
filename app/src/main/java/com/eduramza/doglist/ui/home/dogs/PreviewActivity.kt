package com.eduramza.doglist.ui.home.dogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.bumptech.glide.Glide
import com.eduramza.api.PATH
import com.eduramza.doglist.R
import kotlinx.android.synthetic.main.dog_preview.*

class PreviewActivity : AppCompatActivity() {

    lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.dog_preview)

        val intent = intent
        path = intent.getStringExtra(PATH)

        Glide.with(this)
            .load(path)
            .into(zoom_dog)

    }
}
