package teka.android.cloudinaryimageuploadandroid.features.image_upload_feature.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback


fun uploadToCloudinary(context: Context, filePath: String) {
    // Implement your Cloudinary upload logic here
    // This is just a placeholder method
    Log.d("UA", "sign up uploadToCloudinary- ")

    MediaManager.get()
        .upload(filePath)
        .unsigned("byt8azgi")
        .callback(object : UploadCallback {
                override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                    // Handle upload success
                    Log.d("CLOUDINARYSUCCESS", "XXUISUCCESS URI: ")
                    Toast.makeText(context, "Task successful", Toast.LENGTH_SHORT).show()
                }

                override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                    // Handle upload progress
                    Log.d("CLOUDINARYPROGRESS", "XXUIPROGRESS URI: ")

                }

                override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                    // Handle upload rescheduling
                    Log.d("CLOUDINARYRESCHEDULE", "XXUIRESCHEDULE URI: ")

                }

                override fun onError(requestId: String?, error: ErrorInfo?) {
                    // Handle upload error
                    Log.d("CLOUDINARYERROR", "XXUIERROR URI: ")

                    Toast.makeText(context, "Task Not successful $error", Toast.LENGTH_SHORT).show()
                }

                override fun onStart(requestId: String?) {
                    // Handle upload start
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
        }).dispatch()
}

fun uploadToCloudinary2(filePath: String) {



    Log.d("A", "sign up uploadToCloudinary- ")
    MediaManager.get().upload(filePath).callback(object : UploadCallback {
        override fun onStart(requestId: String) {
            Log.d("B", "sign up uploadToCloudinary- ")
        }

        override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
            Log.d("C", "sign up uploadToCloudinary- ")
        }

        override fun onSuccess(requestId: String, resultData: Map<*, *>) {
            Log.d("D", "sign up uploadToCloudinary- ")
        }

        override fun onError(requestId: String, error: ErrorInfo) {
            Log.d("E", "sign up uploadToCloudinary- ")
        }

        override fun onReschedule(requestId: String, error: ErrorInfo) {
            Log.d("F", "sign up uploadToCloudinary- ")
        }
    }).dispatch()
}


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
