package com.semnazi.composecleanarch2.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semnazi.composecleanarch2.domain.model.User
import com.semnazi.composecleanarch2.presentation.intent.MainIntent
import com.semnazi.composecleanarch2.presentation.ui.theme.ComposeCleanArch2Theme
import com.semnazi.composecleanarch2.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCleanArch2Theme {
                MainScreens()
            }
        }
    }
}

@Composable
fun MainScreens(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(MainIntent.LoadUsers)
    }
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }

                    state.users.isNotEmpty() -> {
                        UserList(users = state.users)
                    }

                    state.error != null -> {
                        Text(
                            text = state.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    )
}
@Composable
fun UserList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            UserRow(user)
        }
    }
}

@Composable
fun UserRow(user: User) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${user.name}")
        Text(text = "Username: ${user.username}")
        Text(text = "Email: ${user.email}")
        HorizontalDivider()
    }
}
