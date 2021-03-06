package top.stores.marketitemcheckinsqr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.mugan86.qrscanner.utils.DateTime
import top.stores.marketitemcheckinsqr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val ZXING_CAMERA_PERMISSION = 1
    private var mClss: Class<*>? = null
    private lateinit var binding: ActivityMainBinding
    private var itemList = ArrayList<ItemData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.openScanner.setOnClickListener {
            launchActivity(ScannerViewActivity::class.java)
        }
        binding.btnCheckOut.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            val args = Bundle()
            args.putSerializable("ARRAYLIST", itemList)
            intent.putExtra("BUNDLE", args)
            startActivity(intent)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val barcode = data?.extras?.getString(Constants.BAR_CODE)

        if (barcode == "") {
            Toast.makeText(this@MainActivity,Constants.BAR_CODE_NOT_FOUND, Toast.LENGTH_LONG).show()
        } else {
            binding.barCodeIdTxt?.text = barcode
            binding.scanDataTxt.text = DateTime.currentDataTime
            itemList.add( ItemData(binding.barCodeIdTxt?.text as String?, binding.scanDataTxt.text))
        }
       }

    private fun setItemList(activity : ItemListActivity){
        startActivity(Intent(this, activity::class.java))
        this.itemList
    }




    private fun launchActivity(clss: Class<*>) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            mClss = clss
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA), ZXING_CAMERA_PERMISSION)
        } else {
            val intent = Intent(this, clss)
            startActivityForResult(intent, 2)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ZXING_CAMERA_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.
                    Toast.makeText(this, Constants.PRESS_AGAIN_TO_SCAN, Toast.LENGTH_LONG).show()


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'switch' lines to check for other
        // permissions this app might request
    }




}