package eu.mokrzycki.synthriders.customsongsmanager.composables

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
    permissionBlock: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    val manageExternalStoragePermissionState = rememberPermissionState(
        Manifest.permission.MANAGE_EXTERNAL_STORAGE
    )

    val manageStorageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (Environment.isExternalStorageManager()) {
            Log.d("synthrider", "all files access granted")
            permissionBlock.invoke(true)
        } else {
            Log.w("synthrider", "all files access not granted")
            permissionBlock.invoke(false)
        }
    }

    LaunchedEffect(manageExternalStoragePermissionState) {
        if (Environment.isExternalStorageManager()) {
            Log.d("synthrider", "all files access granted")
            permissionBlock.invoke(true)
        } else {
            Log.d("synthrider", "granting MANAGE_EXTERNAL_STORAGE permission")
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            manageStorageLauncher.launch(intent)
        }
    }
}