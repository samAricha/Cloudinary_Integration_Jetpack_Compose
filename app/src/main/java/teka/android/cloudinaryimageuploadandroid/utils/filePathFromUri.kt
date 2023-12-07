package teka.android.cloudinaryimageuploadandroid.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback


@SuppressLint("Recycle")
private fun getRealPathFromUri(imageUri: Uri, activity: Activity): String? {
    val cursor = activity.contentResolver.query(imageUri, null, null, null, null)
    return if (cursor == null) {
        imageUri.path
    } else {
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        cursor.getString(idx)
    }
}


private fun uploadToCloudinary(filePath: String) {
    Log.d("A", "sign up uploadToCloudinary- ")
    MediaManager.get().upload(filePath).callback(object : UploadCallback {
        override fun onStart(requestId: String) {
//            Toast.setText("start")
        }

        override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
//            mText.setText("Uploading... ")
        }

        override fun onSuccess(requestId: String, resultData: Map<*, *>) {
//            mText.setText("image URL: " + resultData["url"].toString())
        }

        override fun onError(requestId: String, error: ErrorInfo) {
//            mText.setText("error " + error.description)
        }

        override fun onReschedule(requestId: String, error: ErrorInfo) {
//            mText.setText("Reshedule " + error.description)
        }
    }).dispatch()
}