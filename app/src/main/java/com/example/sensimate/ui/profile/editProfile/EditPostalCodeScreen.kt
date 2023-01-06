package com.example.sensimate.ui.profile.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sensimate.data.Database
import com.example.sensimate.ui.appcomponents.editProfile.CheckBox
import com.example.sensimate.ui.appcomponents.editProfile.CustomTextField
import com.example.sensimate.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun EditPostalCodeScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel() ) {
    val profileState by profileViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to Color(83, 58, 134, 255),
                    0.7f to Color(22, 26, 30)
                )
            )
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            CheckBox(onClick = {
                navController.popBackStack()
                profileViewModel.updatePostalCode(profileState.postalCode)
            })
        }
        CustomTextField(
            text = profileState.postalCode,
            description = "Postal code",
            placeholder = "Enter your postal code here",
            onValueChange = {
                val pattern = "^[0-9]*\$".toRegex()
                if (it.length <= 4 && pattern.matches(it)) {
                    profileViewModel.updatePostalString(input = it)
                }
            }
        )
        Text(
            text = "To give more insightful information to the company, we would like to now about" +
                    " your postal code. If you would like, you can change it here.",
            color = Color.White,
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 30.dp)
        )
    }
}