package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.ImageSaveRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageSaveRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    ImageSaveRepository {

    override fun saveImageToGallery(imageModel: ImageModel): Boolean {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageModel.landscapeUrl)
            .target { drawable ->
                val bitmap = drawable.toBitmap()
                saveBitmapToGallery(bitmap, context)
            }
            .build()
        imageLoader.enqueue(request)
        return true
    }
    private fun saveBitmapToGallery(bitmap: Bitmap, context: Context) : Boolean {
        val displayName = "image_${System.currentTimeMillis()}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        val contentResolver = context.contentResolver
        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        return if (imageUri != null) {
            contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }
            true
        } else {
            false
        }
    }
}