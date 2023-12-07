package teka.android.cloudinaryimageuploadandroid.features.image_upload_feature.presentation

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import teka.android.cloudinaryimageuploadandroid.R

@Composable
fun ImageUploadScreen() {
    val context = LocalContext.current
    var imageUri: Any? by remember { mutableStateOf(R.drawable.pick_image) }
    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }


    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            Log.d("PhotoPicker", "Selected URI: $it")
            imageUri = it
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2)
    ) {
        Log.d("PhotoPicker", "Selected URI: $it")
        selectedImageUris = it
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .size(250.dp)
                .clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
            model = ImageRequest.Builder(LocalContext.current).data(imageUri)
                .crossfade(enable = true).build(),
            contentDescription = "Avatar Image",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow {
            items(selectedImageUris) { uri ->
                AsyncImage(
                    modifier = Modifier.size(250.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(uri)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Button(onClick = {
                Toast.makeText(
                    context,
                    ActivityResultContracts.PickVisualMedia.isPhotoPickerAvailable().toString(),
                    Toast.LENGTH_LONG
                ).show()
            }) {
                Text(text = "Availability")
            }

            Spacer(modifier = Modifier.width(24.dp))
            Button(onClick = {
                multiplePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Text(text = "Pick multiple photo")
            }
        }
        Row(
            modifier = Modifier.padding(20.dp)
        ){
            Button(
                onClick = { /* Handle upload click */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Upload")
            }
        }

    }



















//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//
//    ) {
//        // Circular image view
//        Image(
//            imageVector = Icons.Default.ImageNotSupported,
//            contentDescription = null,
//            modifier = Modifier
//                .size(150.dp)
//                .background(MaterialTheme.colorScheme.background)
//        )
//
//        // Icon to add image
//        Icon(
//            imageVector = Icons.Default.ImageSearch,
//            contentDescription = null,
//            tint = LocalContentColor.current,
//            modifier = Modifier
//                .size(50.dp)
//                .clip(CircleShape)
//                .clickable { /* Handle add image click */ }
//                .padding(4.dp)
//        )
//
//        // TextView
//        Text(
//            text = "Please choose image then click\n upload",
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier
//                .padding(vertical = 8.dp),
//
//            )
//
//        // Button
//        Button(
//            onClick = { /* Handle upload click */ },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Upload")
//        }
//    }
}

@Composable
@Preview
fun MyProfileScreenPreview() {
    ImageUploadScreen()
}

