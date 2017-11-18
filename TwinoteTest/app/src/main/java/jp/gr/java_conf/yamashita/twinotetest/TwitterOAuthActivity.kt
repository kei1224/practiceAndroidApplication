package jp.gr.java_conf.yamashita.twinotetest

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import twitter4j.Twitter
import twitter4j.auth.RequestToken
import jp.gr.java_conf.yamashita.twinotetest.util.*
import kotlinx.android.synthetic.main.activity_twitter_oauth.*
import twitter4j.TwitterException
import twitter4j.auth.AccessToken

class TwitterOAuthActivity : AppCompatActivity() {
    var mCallbackURL: String? = null
    var mTwitter: Twitter? = null
    // private val mCallbackURL: String = getString(R.string.twitter_callback_url)
    // private val mTwitter: Twitter = getTwitterInstance(this)
    var mRequestToken: RequestToken? = null
    // private val mRequestToken: RequestToken = mTwitter.getOAuthRequestToken(mCallbackURL)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_oauth)

        mCallbackURL = getString(R.string.twitter_callback_url)
        mTwitter = getTwitterInstance(this)

        action_start_oauth.setOnClickListener{
            Log.i("TwinoteTest", "action_start_oauth clicked")
            startAuthorize()
        }
    }

    /**
     * OAuth認証（厳密には認可）を開始します。
     *
     * @param listener
     */
    fun startAuthorize(){
        val task: AsyncTask<Void, Void, String> = object : AsyncTask<Void, Void, String>(){
            override fun doInBackground(vararg params: Void): String? {
                try {
                    // ここでTwitterException
                    Log.i("Twinotetest", "callbackURL:"+mCallbackURL)
                    mRequestToken = mTwitter?.getOAuthRequestToken(mCallbackURL)
                    // Log.i("TwinoteTest", mRequestToken?.authorizationURL)
                    return mRequestToken?.authorizationURL
                }catch (e: TwitterException){
                    e.printStackTrace()
                }
                Log.i("TwinoteTest", "doInbackground fail")
                return null
            }

            override fun onPostExecute(url: String?) {
                if (url != null){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    Log.i("TwinoteTest", "OAuth start")
                    startActivity(intent)
                }else{
                    // 失敗
                    Log.i("TwinoteTest", "OAuth fail")
                }
            }
        }
        task.execute()
    }

    override fun onNewIntent(intent: Intent){
        Log.i("TwinoteTest", "excute onNewIntent")
        if(intent.data == null
                || !intent.data.toString().startsWith(mCallbackURL ?: return)){
            return
        }
        val verifier: String = intent.data.getQueryParameter("oauth_verifier")

        val task: AsyncTask<String, Void, AccessToken> = object: AsyncTask<String, Void, AccessToken>(){
            override fun doInBackground(vararg params: String?): AccessToken? {
                try {
                    return mTwitter?.getOAuthAccessToken(mRequestToken, params[0])
                }catch (e: TwitterException){
                    e.printStackTrace()
                }
                return null
            }

            override fun onPostExecute(accessToken: AccessToken?) {
                if (accessToken != null){
                    // 認証成功
                    showToast("認証成功!")
                    successOAuth(accessToken)
                }else{
                    // 認証失敗
                    showToast("認証失敗")
                }
            }
        }

        task.execute(verifier)
    }

    private fun successOAuth(accessToken: AccessToken){
        storeAccessToken(this, accessToken)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
