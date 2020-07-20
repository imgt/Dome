package com.lg.dome.mvc

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lg.dome.R
import com.lg.dome.mvc.ImageDownloader.down
import com.lg.dome.mvc.MvcActivity

/**
 * Created by lqj on 2020/7/20.
 */
class MvcActivity : AppCompatActivity(), Callback {
    private var imageView: ImageView? = null
    private var progressBar: ProgressBar? = null
    private val handler = Handler(Handler.Callback { msg ->
            when (msg.what) {
                ImageDownloader.SUCCESS -> {
                    hideProgress()
                    imageView!!.setImageBitmap(msg.obj as Bitmap)
                }
                ImageDownloader.ERROR -> {
                    hideProgress()
                    Toast.makeText(this@MvcActivity, "下载失败", Toast.LENGTH_SHORT).show()
                }
            }
            false
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        imageView = findViewById(R.id.iv_picture)
        progressBar = findViewById(R.id.progress)
    }

  private  fun showProgress() {
        progressBar!!.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }

    /**
     * 点击事件，获取图片
     *
     * @param view
     */
    fun getImage(view: View?) {
        showProgress()
        val imageBean = ImageBean()
        imageBean.requestPath = path
        down(this, imageBean)
    }

    override fun callback(resultCode: Int, bitmap: ImageBean?) {
        val message = handler.obtainMessage(resultCode)
        message.obj = bitmap!!.bitmap
        handler.sendMessageDelayed(message, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
    companion object {
        private const val path = "http://photocdn.sohu.com/20130416/Img372885486.jpg"
    }
}