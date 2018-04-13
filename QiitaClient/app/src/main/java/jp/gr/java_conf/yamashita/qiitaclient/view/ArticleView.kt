package jp.gr.java_conf.yamashita.qiitaclient.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import jp.gr.java_conf.yamashita.qiitaclient.R
import jp.gr.java_conf.yamashita.qiitaclient.bindView
import jp.gr.java_conf.yamashita.qiitaclient.model.Article
import org.w3c.dom.Text

class ArticleView : FrameLayout {
    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    // var profileImageView: ImageView? = null
    // var titleTextView: TextView? = null
    // var userNameTextView: TextView? = null
    /*
    private val profileImageView: ImageView by lazy {
        findViewById<ImageView>(R.id.profile_image_view)
    }

    private val titleTextView: TextView by lazy {
        findViewById<TextView>(R.id.title_text_view)
    }

    private val userNameTextView: TextView by lazy {
        findViewById<TextView>(R.id.user_name_text_view)
    }*/
    private val profileImageView: ImageView by bindView(R.id.profile_image_view)
    private val titleTextView: TextView by bindView(R.id.title_text_view)
    private val userNameTextView: TextView by bindView(R.id.user_name_text_view)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
        // profileImageView = findViewById(R.id.profile_image_view)
        // titleTextView = findViewById(R.id.title_text_view)
        // userNameTextView = findViewById(R.id.user_name_text_view)
    }

    fun setArticle(article: Article){
        titleTextView.text = article.title
        userNameTextView.text = article.user.name
        profileImageView.setBackgroundColor(Color.RED)
    }
}