package teka.android.cloudinaryimageuploadandroid.features.image_upload_feature.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import teka.android.cloudinaryimageuploadandroid.R
import teka.android.cloudinaryimageuploadandroid.utils.getRealPathFromURI

@Composable
fun PhotoPickerDemoScreen(localContext: Context) {
    val imageUri by remember { mutableStateOf<Uri?>(null) }
    val placeholderImageId = R.drawable.pick_image

    val painterTest = imageUri?.let {
        painterResource(id = placeholderImageId)
    }

    val painterTest2 = painterResource(id = placeholderImageId)




    //The URI of the photo that the user has picked
    var photoUri: Uri? by remember { mutableStateOf(null) }

    //The launcher we will use for the PickVisualMedia contract.
    //When .launch()ed, this will display the photo picker.
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        //When the user has selected a photo, its URI is returned here
        Log.d("PhotoPickerScreen", "Selected URI: $uri")
        if (uri != null) {
            Log.d("PhotoPickerScreenDemo", "Selected FilePath: ${getRealPathFromURI(uri, localContext)}")
        }
        photoUri = uri
    }


    Column {
        Button(
            onClick = {
                //On button press, launch the photo picker
                launcher.launch(
                    PickVisualMediaRequest(
                    //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                    //Or use .VideoOnly if you only want videos.
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
                )
            }
        ) {
            Text("Select Photo")
        }
            
        if (photoUri != null) {
            //Use Coil to display the selected image
            val painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = photoUri)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .border(6.0.dp, Color.Gray),
                contentScale = ContentScale.Crop
            )
            

        }else{
            Image(
                painter = painterTest2,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(5.dp)
                    .height(50.dp)
                    .border(6.0.dp, Color.Gray),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(20.dp)
        ){
            Button(
                onClick = {
                    val filePath = photoUri?.let { getRealPathFromURI(it, localContext) }
                    if (filePath != null) {
                        uploadToCloudinary2(filePath)
                        uploadToCloudinary(localContext, filePath)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Upload")
            }
        }
    }
}