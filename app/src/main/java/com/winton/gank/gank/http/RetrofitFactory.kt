package com.winton.gank.gank.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.winton.gank.gank.App
import com.winton.gank.gank.http.retrofit.GankRetrofitClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @author: winton
 * @time: 2018/10/9 下午4:08
 * @desc: Retrofit 客户端
 */
object RetrofitFactory {
    /**
     * 默认超时时间
     */
    private const val DEFAULT_TIMEOUT = 15L

    private lateinit var mOkHttpClient:OkHttpClient

    init {
        val cookieJar = PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(App.INSTANCE))
        var okHttpBuilder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .cookieJar(cookieJar)

        mOkHttpClient = okHttpBuilder.build()

    }

    /**
     * 获取Gank server
     */
    fun gank():GankRetrofitClient{
        return GankRetrofitClient(mOkHttpClient)
    }


}