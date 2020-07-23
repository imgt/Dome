package com.lg.dome.mvvms.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.lg.baselib.base.BaseFragment
import com.lg.dome.R
import com.lg.dome.databinding.FragmentOtherBinding
import com.lg.dome.mvvms.utils.BitmapUtil
import kotlinx.android.synthetic.main.fragment_other.*
import java.io.File
import java.util.*


/**
 */
class OtherFragment : BaseFragment<FragmentOtherBinding>() {
    private val TAKE_PHOTO = 100
    private val PHOTO_ALBUM = 200
    private val PHOTO_CLIP = 300

    private var takePhotoSaveAdr: Uri? = null
    // 获取权限
    private val REQUEST_CODE_PERMISSIONS = 101

    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2
    private var permissionRequestCount: Int = 0

    private val permissions = Arrays.asList(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    override fun initViewModel() {
    }

    override fun layoutId() = R.layout.fragment_other
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 获取权限
        requestPermissionsIfNecessary()
        btn_a.setOnClickListener {
            taskPhoto(activity!!)
        }
        btn_b.setOnClickListener {
            selectPhoto(activity!!)
        }
    }

    /**
     * 拍照
     */
    private fun taskPhoto(activity: FragmentActivity) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val mImageCaptureUri: Uri
        // 判断7.0android系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //临时添加一个拍照权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            //通过FileProvider获取uri
            takePhotoSaveAdr = FileProvider.getUriForFile(activity, activity.packageName+".fileProvider", File(activity.externalCacheDir, "case.jpg")
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, takePhotoSaveAdr)
        } else {
            mImageCaptureUri = Uri.fromFile(File(activity.externalCacheDir, "case.jpg"))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri)
        }
       startActivityForResult(
            intent, TAKE_PHOTO
        )
    }

    /**
     * 相册
     */
    private fun selectPhoto(activity: FragmentActivity) {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*")
       startActivityForResult(intent, PHOTO_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            when (requestCode) {
                TAKE_PHOTO -> {
                    var clipUri: Uri? = null
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        if (takePhotoSaveAdr != null) {
                            clipUri = takePhotoSaveAdr
                        }
                    } else {
                        clipUri =
                            Uri.fromFile(File(activity!!.externalCacheDir.toString() + "/case.jpg"))
                    }
                    Thread(Runnable {
                        BitmapUtil.compressImage(File(activity!!.externalCacheDir.toString() + "/case.jpg").absolutePath)
                    }).start()
                    val bitmap =
                        BitmapFactory.decodeStream(activity!!.contentResolver.openInputStream(clipUri!!))
                    images.setImageBitmap(bitmap)
                }
                PHOTO_ALBUM -> {
                    // data.getData()
                    val bitmap = BitmapFactory.decodeStream(data!!.getData()?.let {
                        activity!!.contentResolver.openInputStream(it)
                    })
                    images.setImageBitmap(bitmap)
                }
                PHOTO_CLIP -> {

                }
                else -> {

                }

            }
        }
    }


    private fun requestPermissionsIfNecessary() {
        if (!checkAllPermissions()) {
            if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                permissionRequestCount += 1
                ActivityCompat.requestPermissions(
                    mActivity!!,
                    permissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                Toast.makeText( mActivity!!, "设置权限", Toast.LENGTH_LONG).show()

            }
        }
    }

    /** Permission Checking  */
    private fun checkAllPermissions(): Boolean {
        var hasPermissions = true
        for (permission in permissions) {
            hasPermissions = hasPermissions and isPermissionGranted(permission)
        }
        return hasPermissions
    }

    private fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(activity!!, permission) ==
                PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermissionsIfNecessary() // no-op if permissions are granted already.
        }
    }

}


//class OtherFragment :Fragment(){
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: FragmentOtherBinding = FragmentOtherBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//}