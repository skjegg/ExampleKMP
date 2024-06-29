
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully

class ServerApi() {
     private val client: HttpClient = HttpClient()

     suspend fun uploadFile(file:UploadFile): HttpResponse {
         return client.submitFormWithBinaryData(
             url = "http://localhost:8080/upload",
             formData = formData {
                 append("description", "Uploaded Image")
                 append("image", buildPacket { file.toByteArray()?.let { writeFully(it) } }, Headers.build {
                     append(HttpHeaders.ContentType, "image/png")
                     append(HttpHeaders.ContentDisposition, "filename=\"upload.png\"")
                 })})

             /*
            formData {
                appendInput(key = "screenshot", headers = Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=uploadedFile.jpg")
                }) {
                    buildPacket { writeFully(file.toByteArray()) }
                }
                append(FormPart("screenshot", "uploadedFile.jpg"))
            }) {

            //apiUrl("$APPLICATIONS_BASE_URL/upload")
            */

        }

    companion object {
        const val APPLICATIONS_BASE_URL = "applications"
    }


 }