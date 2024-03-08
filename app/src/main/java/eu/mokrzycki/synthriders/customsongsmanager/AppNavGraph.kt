/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.mokrzycki.synthriders.customsongsmanager

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.mokrzycki.synthriders.customsongsmanager.composables.MainSurface
import eu.mokrzycki.synthriders.customsongsmanager.db.AppDatabase
import eu.mokrzycki.synthriders.customsongsmanager.api.ApiService

@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.SCREENS_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            AppDestinations.SCREENS_ROUTE,
        ) { _ ->
            val viewModelFactory = MainViewModelFactory(
                MusicRepository(AppDatabase.getInstance(LocalContext.current).musicDao()),
                RetrofitBuilder.retrofit.create(ApiService::class.java)
            )

            val mainViewModel = ViewModelProvider(
                checkNotNull(LocalViewModelStoreOwner.current),
                viewModelFactory
            )[MainViewModel::class.java]
            
            MainSurface(
                mainViewModel = mainViewModel
            )
        }
    }
}

// Keys for navigation
const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3
