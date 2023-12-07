package teka.android.cloudinaryimageuploadandroid.features.image_upload_feature.presentation

import android.content.Context
import android.widget.Toast
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback

class ImageUploadViewModel {

    fun uploadToCloudinary(filepath: String, applicationContext: Context) {
        MediaManager.get().upload(filepath).unsigned("kks8dyht").callback(object : UploadCallback {
            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                Toast.makeText(applicationContext, "Task successful", Toast.LENGTH_SHORT).show()
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {

            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {

            }

            override fun onError(requestId: String?, error: ErrorInfo?) {

                Toast.makeText(applicationContext, "Task Not successful"+ error, Toast.LENGTH_SHORT).show()
            }

            override fun onStart(requestId: String?) {

                Toast.makeText(applicationContext, "Start", Toast.LENGTH_SHORT).show()
            }
        }).dispatch()
    }


}