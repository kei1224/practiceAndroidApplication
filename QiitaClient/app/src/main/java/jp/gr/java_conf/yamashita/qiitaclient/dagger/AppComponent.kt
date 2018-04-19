package jp.gr.java_conf.yamashita.qiitaclient.dagger

import dagger.Component
import jp.gr.java_conf.yamashita.qiitaclient.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(ClientModule::class))
@Singleton
interface AppComponent{
    fun inject(mainActivity: MainActivity)
}