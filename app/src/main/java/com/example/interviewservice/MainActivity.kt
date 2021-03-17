package com.example.interviewservice

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.interviewservice.database.ServiceDatabase
import com.example.interviewservice.database.entity.RecommendInfo
import com.example.interviewservice.utils.replaceBlank
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope(), View.OnClickListener {
    companion object {
        const val REQUEST_FILE_CODE = 0X1
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initClick()
    }

    private fun initClick() {
        import_btn.setOnClickListener(this)
        delete_btn.setOnClickListener(this)
        clear_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.import_btn -> importFile()
            R.id.delete_btn -> deleteItem()
            R.id.clear_btn -> clearTable()
        }
    }


    private fun importFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*" //设置类型
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_FILE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_FILE_CODE) {
            data?.data?.let {
                readFileToDatabase(it)
            }
        }else{
            Toast.makeText(this,getString(R.string.import_fail),Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 读取配置文件到数据库
     * 规定为json文件
     * 测试数据：
    [
    {
    "packageName":"com.tencent.mm",
    "appName":"微信",
    "icon":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3262171803,2318469631&fm=26&gp=0.jpg",
    "version":"1.0.0"
    },
    {
    "packageName":"battymole.trainticket",
    "appName":"12306",
    "icon":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3938531412,642018340&fm=26&gp=0.jpg",
    "version":"1.0.0"
    }
    ]
     */
    private fun readFileToDatabase(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.let {
            launch(Dispatchers.IO) {
                val bytes = ByteArray(it.available())
                it.read(bytes)
                val recommendInfo: List<RecommendInfo>
                try {
                    val result = String(bytes).replaceBlank()
                    Log.i(TAG, "readFile:$result")
                    recommendInfo = Gson().fromJson(
                        result,
                        object : TypeToken<List<RecommendInfo>>() {}.type
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.file_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@launch
                }
                ServiceDatabase.getRecommendAppDao().insertApp(recommendInfo)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.import_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /**
     * 删除一条数据
     */
    private fun deleteItem() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.input_delete_package_name))
        val et = AppCompatEditText(this)
        builder.setView(et)
        builder.setPositiveButton(R.string.confirm) { _, _ ->
            launch(Dispatchers.IO) {
                val resultCode = ServiceDatabase.getRecommendAppDao().deleteApp(et.text.toString())
                Log.d(TAG, "resultCode:$resultCode")
                withContext(Dispatchers.Main) {
                    val result: String = if (resultCode > 0) {
                        getString(R.string.delete_item_success)
                    } else {
                        getString(R.string.delete_item_fail)
                    }
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                }
            }
        }
        builder.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.setView(et, 0, 0, 0, 0)
        dialog.show()
    }

    /**
     * 清除数据
     */
    private fun clearTable() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.clear_item_confirm))
        builder.setPositiveButton(R.string.confirm) { _, _ ->
            launch(Dispatchers.IO) {
                ServiceDatabase.getRecommendAppDao().deleteAll()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.clear_item_finish),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        builder.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
