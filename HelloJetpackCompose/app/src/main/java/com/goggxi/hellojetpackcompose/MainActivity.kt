package com.goggxi.hellojetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goggxi.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

private val sampleName = listOf(
    "Andre",
    "Desta",
    "Parto",
    "Wendy",
    "Komeng",
    "Raffi Ahmad",
    "Andhika Pratama",
    "Vincent Ryan Rompies",
    "Moh Rifkan"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloJetpackComposeTheme {
                HelloJetpackComposeApp()
            }
        }
    }
}

@Composable
fun HelloJetpackComposeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        ListNames(names = sampleName)
    }
}

@Composable
fun ListNames(names: List<String>) {
    if (names.isNotEmpty()) {
//        Column() {
//            for (name in names) {
//                ItemName(name = name)
//            }
//        }
        LazyColumn() {
            items(names) { name ->
                ItemName(name = name)
            }
        }
    } else {
        Text(
            text = "No people to great :(",
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun ItemName(name: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
    )

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = "Jetpack Compose Background",
                modifier = Modifier.size(animatedSizeDp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Welcome to Jetpack Compose",
                    style = MaterialTheme.typography.body1.copy(
                        fontStyle = FontStyle.Italic
                    )

                )
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show Less" else "Show More"
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    group = "greeting",
    showSystemUi = true,
    device = Devices.PIXEL_3A,
)

@Preview(
    showBackground = true,
    group = "greeting",
    showSystemUi = true,
    device = Devices.PIXEL_3A,
    uiMode = UI_MODE_NIGHT_YES,
)

@Composable
fun NamesIsNotEmptyPreview() {
    HelloJetpackComposeTheme {
        ListNames(names = sampleName)
    }
}


@Preview(
    showBackground = true,
    group = "greeting",
    showSystemUi = true,
    device = Devices.PIXEL_3A,
)

@Composable
fun NamesIsEmptyPreview() {
    HelloJetpackComposeTheme {
        ListNames(names = listOf<String>())
    }
}
