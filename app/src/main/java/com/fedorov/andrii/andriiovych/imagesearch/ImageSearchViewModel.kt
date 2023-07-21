package com.fedorov.andrii.andriiovych.imagesearch

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.data.Image
import com.fedorov.andrii.andriiovych.imagesearch.data.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    var imageRepository: ImageRepository = App.container.imageRepository,
    private val context: App = App.context
) :
    ViewModel() {

    init {
        searchImage("Автомобили")
    }

    var searchState = mutableStateOf("Автомобили")
    var imageState = mutableStateOf(Image("", 0))
    var listImageState = mutableStateOf<List<Image>>(emptyList())
    var allSizeState = mutableStateOf(0)
    var toastState = mutableStateOf("")

    fun nextImage() {
        var count = imageState.value.id
        if (count == allSizeState.value - 1) return
        else {
            count++
            imageState.value = listImageState.value[count]
        }
    }

    fun lastImage() {
        var count = imageState.value.id
        if (count == 0) return
        else {
            count--
            imageState.value = listImageState.value[count]
        }
    }

    fun searchImage(name: String) = viewModelScope.launch(Dispatchers.IO) {
        var listImage = imageRepository.getSearchImage(name)
        if (listImage.isNotEmpty()) {
            listImageState.value = listImage
            allSizeState.value = listImage.size
        }
    }

    fun saveImageToGallery(image: Image = imageState.value) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(image.url)
            .target { drawable ->
                val bitmap = drawable.toBitmap()
                saveBitmapToGallery(bitmap, context)
            }
            .build()
        val disposable = imageLoader.enqueue(request)
    }

    private fun saveBitmapToGallery(bitmap: Bitmap, context: Context) {
        val displayName = "image_${System.currentTimeMillis()}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val contentResolver = context.contentResolver
        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (imageUri != null) {
            contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }
            toastState.value = "Изображение сохранено"
        } else {
            toastState.value = "Не удалось сохранить изображение"
        }
    }
}
