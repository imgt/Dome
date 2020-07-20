package com.lg.dome.mvc

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by lqj on 2020/7/20.
 */
object ImageDownloader {
    const val SUCCESS = 200
    const val ERROR = 401
    /**
     * 下载图片
     *
     * @param callback
     * @param imageBean
     */
    @JvmStatic
    fun down(callback: Callback?, imageBean: ImageBean) {
        Thread(Downloader(callback, imageBean)).start()
    }

    internal class Downloader(private val callback: Callback?, private val imageBean: ImageBean) : Runnable {
        override fun run() {
            try {
                val url = URL(imageBean.requestPath)
                val httpURLConnection =
                    url.openConnection() as HttpURLConnection
                httpURLConnection.connectTimeout = 5000
                httpURLConnection.requestMethod = "GET"
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = httpURLConnection.inputStream
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    showUI(SUCCESS, bitmap)
                } else {
                    showUI(ERROR, null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showUI(ERROR, null)
            }
        }

        private fun showUI(resultCode: Int, bitmap: Bitmap?) {
            if (callback != null) {
                imageBean.bitmap = bitmap
                callback.callback(resultCode, imageBean)
            }
        }

    }
}