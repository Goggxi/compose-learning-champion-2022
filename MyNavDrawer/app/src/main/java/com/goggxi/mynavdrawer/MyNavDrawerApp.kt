package com.goggxi.mynavdrawer

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goggxi.mynavdrawer.ui.theme.MyNavDrawerTheme
import kotlinx.coroutines.launch

@Composable
fun MyNavDrawerApp() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context =  LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopBar(
                onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            MyDrawerContent(
                onItemSelected = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                            val snackBarResult =  scaffoldState.snackbarHostState.showSnackbar(
                                message = context.resources.getString(R.string.coming_soon, it),
                                actionLabel = context.resources.getString(R.string.subscribe_question),
                            )

                            if (snackBarResult == SnackbarResult.ActionPerformed) {
                                Toast.makeText(
                                    context,
                                    context.resources.getString(R.string.subscribed_info),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                },
                onBackPress = {
                    if (scaffoldState.drawerState.isOpen) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            )
        },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) {

    }
}

@Composable
fun MyTopBar(onMenuClick : () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onMenuClick()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
        title = {
            Text(stringResource(R.string.app_name))
        },
    )
}

data class MenuItem(
    val title:String,
    val icon:ImageVector
)

@Composable
fun MyDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title:String) -> Unit,
    onBackPress: () -> Unit,
){
    val items = listOf(
        MenuItem(title = stringResource(id = R.string.home), icon = Icons.Default.Home),
        MenuItem(title = stringResource(id = R.string.favourite), icon = Icons.Default.Favorite),
        MenuItem(title = stringResource(id = R.string.profile), icon = Icons.Default.AccountCircle)
    )
    
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )
        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable { onItemSelected(item.title) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, style = MaterialTheme.typography.subtitle2)
            }
        }
        Divider()
    }

    BackPressHandler {
        onBackPress()
    }
    // OR
    BackHandler() {
        onBackPress()
    }
}

@Composable
fun BackPressHandler(
    enable:Boolean = true,
    onBackPressed: () -> Unit,
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
    val backCallback = remember {
        object : OnBackPressedCallback(enable){
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enable
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyNavDrawerAppPreview() {
    MyNavDrawerTheme {
        MyNavDrawerApp()
    }
}