package jp.gr.java_conf.yamashita.qiitaclient

import android.app.Application
import jp.gr.java_conf.yamashita.qiitaclient.dagger.AppComponent
import jp.gr.java_conf.yamashita.qiitaclient.dagger.DaggerAppComponent

class QiitaClientApp : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}