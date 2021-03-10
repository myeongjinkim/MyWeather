package com.example.myweather

import android.content.Context
import android.location.Address
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myweather.databinding.ActivitySettingBinding
import com.example.myweather.viewModel.SettingViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        binding.viewModel = settingViewModel
        setSupportActionBar(binding.toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun textClear(view: View){
        binding.editText.setText("")
    }

    fun closeKeyboard(view: View) {
        if(view != null) {
            view.clearFocus()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setCity(view: View){
        settingViewModel.requestSettingRepository()
        onBackPressed()
    }

}