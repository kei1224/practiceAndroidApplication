package jp.gr.java_conf.yamashita.qiitaclient

import android.support.annotation.IdRes
import android.view.View

fun <T: View> View.bindView(@IdRes id: Int):Lazy<T> = lazy {
    findViewById<T>(id)
}