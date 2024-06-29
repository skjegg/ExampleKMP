import com.darkrockstudios.libraries.mpfilepicker.PlatformFile

expect class UploadFile(selectedFile: PlatformFile?)
expect fun UploadFile.toByteArray(): ByteArray?