package com.android.myapplication.androidStudy

fun main(){
    /**
     * Activity 四种启动模式
     * 1. standard(标准模式) 以栈的方式创建新的activity实例
     * 2. singleTop(栈顶复用模式) 如果activity已经存在于栈顶，就会复用，不然就会创建新实例
     * 3. singleTask (栈内复用模式 复用之前栈中存在的实例) 系统创建新 task 并在 task 的根目录下实例化 activity。但如果 activity 的实例已存在于单独的任务中，则调用其 onNewIntent() 方法
     * ，其上面的实例会被移除栈。一次只能存在一个 activity 实例
     * 4. singleInstance (单列模式 一直创建新的实例 栈中只存在一个实例) 相同 singleTask，activity始终是其task的唯一成员; 任何由此开始的activity 都在一个单独的 task 中打开
     * 生命周期 onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()
     * onRestart() 用户重新回到当前界面（例如从主界面回来）
     * Activity 异常退出时，如何保存状态: onSaveInstanceState()(在onStop之前调用)  onRestoreInstanceState()（在onStart之后调用）
     * 横竖屏切换的生命周期：onPause()->onSaveInstanceState()-> onStop()->onDestroy()->onCreate()->onStart()
     * ->onRestoreInstanceState->onResume()
     * Service() 启动方式
     * startService():主要启动后台服务，不进行通信 生命周期 startService() -> onCreate() -> onStartCommand()(每次启动服务都会调用该方法)  -> onDestroy()
     * bindService()：主要启动后台服务，进行通信（Binder） bindService() -> onCreate() -> onBind()（只会在启动的时候调用一次） -> onUnbind() -> onDestroy()
     *
     *
     * Fragment 的生命周期
     * onAttach() -> onCreate() ->  onCreateView() -> onActivityCreate() -> onStart() -> onResume() -> onPause() -> onStop()
     * -> onDestroyView() -> onDestroy() -> onDetach()
     *
     * */
   /** 保存数据
    *  lateinit var textView: TextView
    var gameState: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        textView.text = savedInstanceState?.getString(TEXT_VIEW_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putString(GAME_STATE_KEY, gameState)
            putString(TEXT_VIEW_KEY, textView.text.toString())
        }
        super.onSaveInstanceState(outState)
    } */

}
