package teka.android.cloudinaryimageuploadandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cloudinary.android.MediaManager
import teka.android.cloudinaryimageuploadandroid.features.image_upload_feature.presentation.PhotoPickerDemoScreen
import teka.android.cloudinaryimageuploadandroid.ui.theme.CloudinaryImageUploadAndroidTheme

class MainActivity : ComponentActivity() {
    var config: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config.put("cloud_name", "dwfeiytuo")
        config.put("api_key", "259155485653347")
        config.put("api_secret", "2o0yOFVPYdy1qpp6kEOntVX03kA")
        MediaManager.init(this, config)


        setContent {
            CloudinaryImageUploadAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    ImageUploadScreen()
                    PhotoPickerDemoScreen(LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CloudinaryImageUploadAndroidTheme {
        Greeting("Android")
    }
}