package jp.gr.java_conf.yamashita.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import jp.gr.java_conf.yamashita.qiitaclient.model.Article
import jp.gr.java_conf.yamashita.qiitaclient.model.User
import jp.gr.java_conf.yamashita.qiitaclient.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("Kotlin入門", "たろう"),
                dummyArticle("Java入門", "じろう"))

        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = listAdapter

        listView.setOnItemClickListener{ adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it)}
        }

        /*
        val articleView = ArticleView(applicationContext)

        articleView.setArticle(Article(id = "123",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/123",
                user = User(id = "456", name = "たろう", profileImageUrl = "")))

        setContentView(articleView)*/
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "https://kotlinlang.org/",
                    user = User(id = "", name = userName, profileImageUrl = ""))
}
