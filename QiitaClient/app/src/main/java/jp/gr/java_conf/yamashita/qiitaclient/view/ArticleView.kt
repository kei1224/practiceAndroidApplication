package jp.gr.java_conf.yamashita.qiitaclient.view

import android.content.Context
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import jp.gr.java_conf.yamashita.qiitaclient.R
import jp.gr.java_conf.yamashita.qiitaclient.databinding.ViewArticleBinding
import jp.gr.java_conf.yamashita.qiitaclient.model.Article

@BindingMethods(BindingMethod(type = Article::class,
        attribute = "bind:article",
        method = "setArticle"))
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

    /* private val profileImageView: ImageView by bindView(R.id.profile_image_view)
    private val titleTextView: TextView by bindView(R.id.title_text_view)
    private val userNameTextView: TextView by bindView(R.id.user_name_text_view) */

    val binding: ViewArticleBinding

    init {
        // LayoutInflater.from(context).inflate(R.layout.view_article, this)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_article, this, true)
    }



    fun setArticle(article: Article){
        binding.article = article
        /*
        title_text_view.text = article.title
        user_name_text_view.text = article.user.name
        Glide.with(context).load(article.user.profileImageUrl).into(profile_image_view)*/
    }
}