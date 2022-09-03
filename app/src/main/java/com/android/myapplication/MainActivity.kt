package com.android.myapplication

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.databinding.ActivityMainBinding
import com.android.myapplication.model.MainData
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private var data: ArrayList<MainData> = arrayListOf()
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
        // 创建适配器
        var linearLayoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = linearLayoutManager
        mainAdapter = MainAdapter(this, data)
        mainAdapter.setmItemOnClickListener(object :MainAdapter.setItemOnClickListener{
            override fun onClick(position: Int) {
                Toast.makeText(this@MainActivity,data[position].title,Toast.LENGTH_SHORT).show()
            }
        })
        binding.recycleView.adapter = mainAdapter


    }
}