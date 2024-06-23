package org.example.project
import io.ktor.http.content.*
import java.io.File

suspend fun handleIt(mpart: MultiPartData){

    mpart.forEachPart { part ->
        // if part is a file (could be form item)
        if(part is PartData.FileItem) {
            // retrieve file name of upload
            val name = part.originalFileName!!
            val file = File("/tmp/uploads/$name")

            // use InputStream from part to save file
            part.streamProvider().use { its ->
                // copy the stream to the file with buffering
                file.outputStream().buffered().use {
                    // note that this is blocking
                    its.copyTo(it)
                }
            }
        }
        // make sure to dispose of the part after use to prevent leaks
        part.dispose()

    }
}