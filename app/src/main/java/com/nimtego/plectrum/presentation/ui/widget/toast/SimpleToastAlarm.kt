package com.nimtego.plectrum.presentation.ui.widget.toast

import android.content.Context
import android.widget.Toast

class SimpleToastAlarm(private var mContext: Context?) : ToastAlarm {

    override fun destroy() {
        mContext = null
    }

    override fun message(message: String) {
        val toast = Toast.makeText(mContext!!.applicationContext,
                message, Toast.LENGTH_SHORT)
        toast.show()
    }
}
