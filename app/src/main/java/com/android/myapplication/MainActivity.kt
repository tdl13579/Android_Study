package com.android.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.databinding.ActivityMainBinding
import com.android.myapplication.model.MainData
import com.android.myapplication.model.MyFragment
import com.android.myapplication.model.MyReceiver
import okhttp3.*
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private var data: ArrayList<MainData> = arrayListOf()
    private lateinit var myReceiver: MyReceiver
    private lateinit var myFragment: MyFragment

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show()
        val date = SimpleDateFormat("YYYY-MM-DD  hh:mm:ss").format(Date().time)
        // 构造数据
        for (i in 1..20) {
            var mainData =
                MainData(R.drawable.ic_launcher_foreground, "标题--$i", "副标题--$i", "$date -- $i")
            data.add(mainData)
        }
        // 动态加载广播
        myReceiver = MyReceiver()
        var intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(myReceiver, intentFilter)

        // 加载动态fragment
        myFragment = MyFragment();
        supportFragmentManager.beginTransaction().replace(R.id.fragment, myFragment).commit()


        // 创建适配器
        var linearLayoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = linearLayoutManager
        mainAdapter = MainAdapter(this, data)
        mainAdapter.setOnItemClickListener(object : MainAdapter.ItemOnClickListener {
            override fun onClick(position: Int) {
                Toast.makeText(this@MainActivity, data[position].title, Toast.LENGTH_SHORT).show()
            }
        })
        binding.recycleView.adapter = mainAdapter

        // 子线程中开启主线程
        Thread {
            runOnUiThread() {
                println("hello world")
            }
        }.start()
        // 去登录
        binding.goLogin.setOnClickListener{
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver) // 取消广播
    }

    // 文件的读写方法
    // output 是写入
    private fun save(file: String, text: String) {
        var fileOutputStream = openFileOutput(file, MODE_PRIVATE);
        fileOutputStream.write(text.toByteArray())
    }

    // input 是读取
    private fun read(file: String) {
        var fileInputStream = openFileInput(file)
        val temp = ByteArray(1024)
        var hasRead = fileInputStream.read(temp)
        Log.e("read", String(temp, 0, hasRead))
    }

    // 同步get请求
    fun getAsync() {
        Thread {
            var request = Request.Builder()
                .get()
                .url("www.baidu.com")
                .build()
            var response = OkHttpClient().newCall(request).execute()
            if (response.isSuccessful) {
                Log.e("get同步", "${response.body.toString()}")
            }
        }.start()
    }

    fun get() {
        var request = Request.Builder()
            .get().url("www.baidu.com").build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("get异步", "${response.body.toString()}")
                }
            }
        })
    }

    fun postAsync() {
        Thread {
            var requestArgs = FormBody.Builder()
                .add("a", "1")
                .add("b", "2")
                .build()
            var request = Request.Builder()
                .post(requestArgs)
                .url("www.baidu.com")
                .build()
            var response = OkHttpClient().newCall(request)
                .execute()
            if (response.isSuccessful) {
                Log.e("post同步", "${response.body.toString()}")
            }
        }.start()
    }

    fun post() {
        var requestArgs = FormBody.Builder()
            .add("a", "1")
            .add("b", "2")
            .build()
        var request = Request.Builder()
            .post(requestArgs)
            .url("www.baidu.com")
            .build()
        OkHttpClient().newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        Log.e("post异步", "${response.body.toString()}")
                    }
                }
            })
    }

}