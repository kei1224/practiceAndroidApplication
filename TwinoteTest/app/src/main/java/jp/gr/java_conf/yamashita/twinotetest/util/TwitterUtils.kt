package jp.gr.java_conf.yamashita.twinotetest.util

import android.content.Context
import android.content.SharedPreferences
import jp.gr.java_conf.yamashita.twinotetest.R
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

/**
 * Created by satoshi on 2017/11/16.
 */

    private val token = "token"
    private val token_secret = "token_secret"
    private val pref_name = "twitter_access_token"

    fun getTwitterInstance(context: Context): Twitter {
        val consumerKey: String =context.getString(R.string.twitter_consumer_key)
        val consumerSecret: String = context.getString(R.string.twitter_consumer_secret)

        // Twitterオブジェクトのインスタンス
        val factory : TwitterFactory = TwitterFactory()
        val twitter : Twitter = factory.instance
        twitter.setOAuthConsumer(consumerKey, consumerSecret)

        // トークンの設定
        if(hasAccessToken(context)){
            twitter.oAuthAccessToken = loadAccessToken(context)
        }
        return twitter
    }

    fun storeAccessToken(context: Context, accessToken: AccessToken){
        val preferences: SharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.putString(token, accessToken.token)
        editor.putString(token_secret, accessToken.tokenSecret)

        editor.commit()
    }

    fun loadAccessToken(context: Context): AccessToken? {
        // preferenceからトークンの呼び出し
        val preferences: SharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE)
        val token = preferences.getString(token, null)
        val tokenSecret = preferences.getString(token_secret, null)
        if(token != null && tokenSecret != null){
            return AccessToken(token, tokenSecret)
        }else{
            return null
        }
    }

    fun hasAccessToken(context: Context): Boolean = (loadAccessToken(context) != null)

