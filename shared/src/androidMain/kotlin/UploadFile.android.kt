import android.content.ContentResolver
import androidx.core.net.toFile
import com.darkrockstudios.libraries.mpfilepicker.PlatformFile

actual class UploadFile actual constructor(selectedFile: PlatformFile?){    val pf = selectedFile}

actual fun UploadFile.toByteArray(): ByteArray? {
       // val contenr = ContentResolver.aquire

        return pf?.uri?.toFile()?.inputStream().use { it?.buffered()?.readBytes() }

}