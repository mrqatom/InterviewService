package com.example.interviewservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interviewservice.database.ServiceDatabase
import com.example.interviewservice.database.entity.RecommendInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preDateInsert()

    }

    private fun preDateInsert() {
        val info = mutableListOf<RecommendInfo>()
        info.add(
            RecommendInfo(
                "com.jianshu.haruki",
                "简书",
                "https://pic.17qq.com/uploads/qruectcchx.jpeg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.zhihu.android",
                "知乎",
                "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/1b4c510fd9f9d72a26f9db77df2a2834349bbb3f.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.luojilab.player",
                "得到",
                "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/86d6277f9e2f07080361fd58e224b899a901f206.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.flightmanager.view",
                "航班管家",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1310084190,3440202351&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.iflytek.inputmethod",
                "讯飞输入法",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=55616755,1069134233&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.meitu.meiyancamera",
                "美颜相机",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2771030822,2247883056&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.dp.android.elong",
                "艺龙旅行",
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4277017437,2026531708&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.tencent.weread",
                "微信读书",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=87885162,4179658412&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.immomo.momo",
                "陌陌",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3551731065,3155712203&fm=11&gp=0.jpg",
                "1.0.0"
            )
        )
        info.add(
            RecommendInfo(
                "com.tencent.qqlive",
                "腾讯视频",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3829821006,1893451978&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )
        launch(Dispatchers.IO) {
            ServiceDatabase.getRecommendAppDao().insertApp(info)
        }
    }
}