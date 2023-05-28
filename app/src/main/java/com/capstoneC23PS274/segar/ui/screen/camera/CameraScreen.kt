package com.capstoneC23PS274.segar.ui.screen.camera

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView


private var imageCapture: ImageCapture? = null

@Composable
fun CameraScreen (
    application: Application,
    toResult : () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCamPermission by remember {
        mutableStateOf(
            REQUIRED_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(context, it) ==
                        PackageManager.PERMISSION_GRANTED
            }
        )
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { granted ->
            hasCamPermission =granted.size == 2
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }

    Box {
        if (hasCamPermission) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { startCameraPreviewView(context, lifecycleOwner) }
            )
            Button(
                onClick = {
                    takePhoto(
                        application = application,
                        context = context,
                        toResult = toResult
                    )
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "foto")
            }
        }
    }
}

private fun startCameraPreviewView(context: Context, owner : LifecycleOwner): PreviewView {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    val previewView = PreviewView(context)
    val preview = androidx.camera.core.Preview.Builder().build().also {
        it.setSurfaceProvider(previewView.surfaceProvider)
    }

    imageCapture = ImageCapture.Builder().build()

    val camSelector =
        CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
    try {
        cameraProviderFuture.get().bindToLifecycle(
            owner,
            camSelector,
            preview,
            imageCapture
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return previewView
}

private fun takePhoto(application: Application, context: Context, toResult : () -> Unit) {
    val imageCapture = imageCapture ?: return

    val photoFile = createFile(application)

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(
                    context,
                    "Berhasil mengambil gambar.",
                    Toast.LENGTH_SHORT
                ).show()
                toResult()
            }

            override fun onError(exception: ImageCaptureException) {
                Log.d("camera error : ", exception.message.toString())
                Toast.makeText(
                    context,
                    "Gagal mengambil gambar.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun CameraScreenPreview() {
    SegarTheme {
//        CameraScreen(application = getApplication)
    }
}