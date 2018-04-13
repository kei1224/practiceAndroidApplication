package jp.gr.java_conf.yamashita.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import jp.gr.java_conf.yamashita.qiitaclient.model.Article
import jp.gr.java_conf.yamashita.qiitaclient.view.ArticleView

class ArticleActivity : AppCompatActivity(){
    companion object {
        private const val ARTICLE_EXTRA: String = "article"
        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, article)
    }

    private val articleView: ArticleView by lazy {
        findViewById<ArticleView>(R.id.article_view)
    }

    private val webView: WebView by lazy {
        findViewById<WebView>(R.id.web_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }
}