package com.example.myapp012aimagetoapp
//import android.net.Uri
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapp012aimagetoapp.databinding.ActivityMainBinding
//import android.graphics.Canvas
//import android.graphics.Paint
//import android.graphics.Typeface
//import android.content.Intent
//import android.graphics.Bitmap
//import android.provider.MediaStore
//import android.widget.Button
//import android.widget.ImageView
//
//import android.os.Environment
//import android.util.Log
//import android.widget.Toast
//import java.io.File
//import java.io.FileOutputStream
//import java.io.OutputStream
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private var imageUri: Uri? = null
//    private lateinit var imageView: ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Inicializace pro viewBinding
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
//                uri: Uri? -> binding.ivImage.setImageURI(uri)
//        }
//        binding.btnTakeImage.setOnClickListener {
//            getContent.launch("image/*")
//        }
//        binding.buttonAddText.setOnClickListener {
//            imageUri?.let {
//                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
//                val bitmapWithText = addTextToBitmap(bitmap, "Tvůj text")
//                if (bitmapWithText != null) {
//                    imageView.setImageBitmap(bitmapWithText)
//                    saveBitmapToGallery(bitmapWithText, "ImageWithText")
//                } else {
//                    Toast.makeText(this, "Error processing image", Toast.LENGTH_SHORT).show()
//                }
//            } ?: run {
//                Toast.makeText(this, "Obrázek nebyl vybrán", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//    }
//
//    private fun addTextToBitmap(bitmap: Bitmap?, text: String): Bitmap? {
//        if (bitmap == null) {
//            Log.e("addTextToBitmap", "Bitmap is null")
//            return null
//        }
//
//        return try {
//            // Use a default configuration if the bitmap's config is null
//            val config = bitmap.config ?: Bitmap.Config.ARGB_8888
//            val newBitmap = bitmap.copy(config, true)
//            val canvas = Canvas(newBitmap)
//            val paint = Paint()
//            paint.textSize = 64f
//            paint.color = android.graphics.Color.RED
//            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//            canvas.drawText(text, 50f, 50f, paint)
//            newBitmap
//        } catch (e: Exception) {
//            Log.e("addTextToBitmap", "Error processing bitmap", e)
//            null
//        }
//    }
//
//
//
//    private fun saveBitmapToGallery(bitmap: Bitmap, title: String) {
//        val filename = "$title.jpg"
//        val file = File(Environment.getExternalStorageDirectory().absolutePath, filename)
//        val outputStream: OutputStream = FileOutputStream(file)
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//        outputStream.flush()
//        outputStream.close()
//
//        MediaStore.Images.Media.insertImage(contentResolver, file.absolutePath, file.name, file.name)
//    }
//
//
//}
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp012aimagetoapp.databinding.ActivityMainBinding
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imageUri: Uri? = null
    private var bitmapWithText: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializace pro viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace tlačítek a imageView
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                binding.ivImage.setImageURI(it)
                Log.d("MainActivity", "Image URI: $imageUri")
            }
        }

        binding.btnTakeImage.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.buttonAddText.setOnClickListener {
            imageUri?.let {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                    Log.d("MainActivity", "Bitmap loaded successfully")
                    bitmapWithText = addTextToBitmap(bitmap, binding.editTextInput.text.toString())

                    if (bitmapWithText != null) {
                        binding.ivImage.setImageBitmap(bitmapWithText)
                        Log.d("MainActivity", "Text added to image")
                    } else {
                        Toast.makeText(this, "Error processing image", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error adding text to bitmap", e)
                    Toast.makeText(this, "Error processing image", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "Obrázek nebyl vybrán", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSaveImage.setOnClickListener {
            bitmapWithText?.let {
                saveBitmapToGallery(it, "ImageWithText")
            } ?: run {
                Toast.makeText(this, "Nejprve přidejte text na obrázek", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTextToBitmap(bitmap: Bitmap?, text: String): Bitmap? {
        if (bitmap == null) {
            Log.e("addTextToBitmap", "Bitmap is null")
            return null
        }

        return try {
            // Use a default configuration if the bitmap's config is null
            val config = bitmap.config ?: Bitmap.Config.ARGB_8888
            val newBitmap = bitmap.copy(config, true)
            val canvas = Canvas(newBitmap)
            val paint = Paint()
            paint.textSize = 64f
            paint.color = android.graphics.Color.RED
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            canvas.drawText(text, 50f, 50f, paint)
            Log.d("addTextToBitmap", "Text added to bitmap")
            newBitmap
        } catch (e: Exception) {
            Log.e("addTextToBitmap", "Error processing bitmap", e)
            null
        }
    }

//    private fun saveBitmapToGallery(bitmap: Bitmap, title: String) {
//        try {
//            val filename = "$title.jpg"
//            val file = File(Environment.getExternalStorageDirectory().absolutePath, filename)
//            val outputStream: OutputStream = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//            outputStream.flush()
//            outputStream.close()
//            MediaStore.Images.Media.insertImage(contentResolver, file.absolutePath, file.name, file.name)
//            Log.d("MainActivity", "Image saved to gallery")
//            Toast.makeText(this, "Obrázek uložen do galerie", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            Log.e("MainActivity", "Error saving image to gallery", e)
//            Toast.makeText(this, "Chyba při ukládání obrázku do galerie", Toast.LENGTH_SHORT).show()
//        }
//    }
private fun saveBitmapToGallery(bitmap: Bitmap, title: String) {
    try {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "$title.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyAppImages")
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            val outputStream = contentResolver.openOutputStream(it)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            outputStream?.flush()
            outputStream?.close()
            Log.d("MainActivity", "Image saved to gallery")
            Toast.makeText(this, "Obrázek uložen do galerie", Toast.LENGTH_SHORT).show()
        } ?: run {
            Log.e("MainActivity", "Failed to create URI")
            Toast.makeText(this, "Chyba při ukládání obrázku do galerie", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Log.e("MainActivity", "Error saving image to gallery", e)
        Toast.makeText(this, "Chyba při ukládání obrázku do galerie", Toast.LENGTH_SHORT).show()
    }
}


}


