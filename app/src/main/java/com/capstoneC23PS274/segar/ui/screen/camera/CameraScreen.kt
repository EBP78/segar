package com.capstoneC23PS274.segar.ui.screen.camera

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.CameraFAB
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import java.io.File
import com.capstoneC23PS274.segar.R


private var imageCapture: ImageCapture? = null
private val cameraSelector : CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

@Composable
fun CameraScreen (
    application: Application,
    toResult : (String) -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: CameraViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
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

    val loading by viewmodel.loading
    val errMess by viewmodel.errorMessage
    val errShow by viewmodel.errorShow

    LaunchedEffect(key1 = true) {
        launcher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }
    Box(modifier = modifier.fillMaxSize()){
        viewmodel.checkResult.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    if (hasCamPermission) {
                        AndroidView(
                            modifier = Modifier
                                .fillMaxSize()
                                .testTag(stringResource(id = R.string.camera_view)),
                            factory = { startCameraPreviewView(context, lifecycleOwner) }
                        )
                        CameraFAB(onClick = {
                            takePhoto(
                                application = application,
                                context = context,
                                toResult = { file, isBackCamera ->
                                    rotateFile(file, isBackCamera)
                                    viewmodel.checkImage(file)
                                },
                                isFailed = { errMess ->
                                    viewmodel.showError(errMess)
                                }
                            )
                        },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                        )
                    }
                }
                is UiState.Success -> {
                    if (uiState.data.data != null){
                        toResult(uiState.data.data.id.toString())
                    } else {
                        viewmodel.showError(uiState.data.message.toString())
                    }
                    viewmodel.isFinished()
                }
                is UiState.Error -> {
                    viewmodel.showError(uiState.errorMessage)
                }
            }
        }
        LoadingAnimation(
            isDisplayed = loading,
            modifier = Modifier.align(Alignment.Center)
        )
        ErrorModal(
            message = errMess,
            isDisplayed = errShow,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private fun startCameraPreviewView(context: Context, owner : LifecycleOwner): PreviewView {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    val previewView = PreviewView(context)
    val preview = androidx.camera.core.Preview.Builder().build().also {
        it.setSurfaceProvider(previewView.surfaceProvider)
    }

    imageCapture = ImageCapture.Builder().build()

    try {
        cameraProviderFuture.get().bindToLifecycle(
            owner,
            cameraSelector,
            preview,
            imageCapture
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return previewView
}

private fun takePhoto(application: Application, context: Context, toResult : (File, Boolean) -> Unit, isFailed : (String) -> Unit) {
    val imageCapture = imageCapture ?: return

    val photoFile = createFile(application)
    val isBackCamera = cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                toResult(photoFile, isBackCamera)
            }

            override fun onError(exception: ImageCaptureException) {
                isFailed("Gagal Mengambil Foto")
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