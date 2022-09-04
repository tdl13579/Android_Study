package com.android.myapplication

import android.content.IntentFilter
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.databinding.ActivityMainBinding
import com.android.myapplication.model.MainData
import com.android.myapplication.model.MyFragment
import com.android.myapplication.model.MyReceiver
import okhttp3.Interceptor
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private var data: ArrayList<MainData> = arrayListOf()
    private lateinit var  myReceiver: MyReceiver
    private lateinit var  myFragment: MyFragment
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
        var intentFilter =  IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(myReceiver,intentFilter)

        // 加载动态fragment
        myFragment = MyFragment();
        supportFragmentManager.beginTransaction().replace(R.id.fragment,myFragment).commit()


        // 创建适配器
        var linearLayoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = linearLayoutManager
        mainAdapter = MainAdapter(this, data)
        mainAdapter.setOnItemClickListener(object :MainAdapter.ItemOnClickListener{
            override fun onClick(position: Int) {
                Toast.makeText(this@MainActivity,data[position].title,Toast.LENGTH_SHORT).show()
            }
        })
        binding.recycleView.adapter = mainAdapter

        // 子线程中开启主线程
        Thread {
            runOnUiThread(){
                println("hello world")
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver) // 取消广播
    }
}