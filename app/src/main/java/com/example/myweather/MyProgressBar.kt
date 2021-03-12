package com.example.myweather

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window


class MyProgressBar(context: Context) : Dialog(context) {
    init {
        // 다이얼 로그 제목을 안보이게...
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_bar)
        getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        setCancelable(false)
        show();
    }
}