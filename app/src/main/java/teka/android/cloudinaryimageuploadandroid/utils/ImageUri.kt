package teka.android.cloudinaryimageuploadandroid.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


fun getRealPathFromURI(contentUri: Any?, context: Context): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor? =
        contentUri?.let { context.contentResolver.query(it as Uri, projection, null, null, null) }
    if (cursor != null) {
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val filePath = cursor.getString(columnIndex)
        cursor.close()
        return filePath
    }
    return null
}