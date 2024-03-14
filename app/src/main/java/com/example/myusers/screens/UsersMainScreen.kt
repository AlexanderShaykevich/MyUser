@file:OptIn(ExperimentalMaterialApi::class)

package com.example.myusers.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myusers.viemodels.UserViewModel

@Composable
fun UsersScreen(viewModel: UserViewModel, navHostController: NavHostController) {
    val context = LocalContext.current
    val users by viewModel.data.collectAsState()
    val usersApiState by viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = usersApiState.isLoading,
        onRefresh = { viewModel.getUsers(random = true) }
    )

    if (usersApiState.isError) {
        Toast.makeText(context, "Sorry, something went wrong", Toast.LENGTH_LONG).show()
        ErrorScreen { viewModel.getUsers(random = false) }
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.pullRefresh(pullRefreshState)
        ) {
            users?.let {
                items(it.results) { user ->
                    UserItem(user = user, navHostController = navHostController)
                }
            }
        }
        PullRefreshIndicator(
            true,
            pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }
}

@Composable
fun ErrorScreen(reload: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { reload() }
        ) {
            Text(text = "Reload")
        }
    }
}


