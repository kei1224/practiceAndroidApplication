package jp.gr.java_conf.yamashita.twinotetest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import jp.gr.java_conf.yamashita.twinotetest.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // アクセストークンを所持していなければアプリケーション認証Activityへ遷移
        if(!hasAccessToken(this)){
            val intent = Intent(getApplication(), TwitterOAuthActivity::class.java)
            startActivity(intent)
            finish()
        }else{

        }
        if(intent.action == Intent.ACTION_SEND){
            val twitterIntentExtras : Bundle = intent.extras

            Log.i("twinote_extras", twitterIntentExtras.toString())
            for(key in twitterIntentExtras.keySet()){
                Log.i("twinote_bundle", key)
            }

            val text = twitterIntentExtras.getString("android.intent.extra.TEXT")
            val id : Long = twitterIntentExtras.getLong("tweet_id")
            Log.i("twinote_TEXT", text)
            Log.i("twinote_ID", id.toString())

            val builder = ConfigurationBuilder()
            // builder.setTweetModeExtended(true)
            val preferences: SharedPreferences = getSharedPreferences("twitter_access_token", Context.MODE_PRIVATE)
            builder.setOAuthConsumerKey(getString(R.string.twitter_consumer_key))
                    .setOAuthConsumerSecret(getString(R.string.twitter_consumer_secret))
                    .setOAuthAccessToken(preferences.getString("token", null))
                    .setOAuthAccessTokenSecret(preferences.getString("token_secret", null))
                    .setTweetModeExtended(true)
            val factory = TwitterFactory(builder.build())
            val twitter = factory.getInstance()
            // ここから

            // val twitter = getTwitterInstance(this)

            val task = object : AsyncTask<Void, Void, Status>(){
                override fun doInBackground(vararg p0: Void?): twitter4j.Status {
                    return twitter.showStatus(id)
                }

                override fun onPostExecute(tweetStatus: twitter4j.Status) {
                    intent_tweet.text = tweetStatus.text
                }
            }
            task.execute()

        }



    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
