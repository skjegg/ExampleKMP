import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import examplekmp.composeapp.generated.resources.Res
import examplekmp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.*
import kotlin.time.measureTime


@Composable
@Preview
fun App() {
    val api= ServerApi()
    val scope = rememberCoroutineScope()
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me hard!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
            val fileType = listOf("jpg", "png")
            FilePicker(show = showContent, fileExtensions = fileType) { platformFile ->
                //showFilePicker = false
                // do something with the file


                val time = measureTime {
                        val res = scope.launch { api.uploadFile(UploadFile(platformFile))}
                    }
                    print(time)
                }


        }
    }
}