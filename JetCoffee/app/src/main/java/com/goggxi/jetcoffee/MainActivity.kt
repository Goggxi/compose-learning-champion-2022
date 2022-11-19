package com.goggxi.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goggxi.jetcoffee.models.Menu
import com.goggxi.jetcoffee.models.dummyBestSellerMenu
import com.goggxi.jetcoffee.models.dummyCategory
import com.goggxi.jetcoffee.models.dummyMenu
import com.goggxi.jetcoffee.ui.components.CategoryItem
import com.goggxi.jetcoffee.ui.components.MenuItem
import com.goggxi.jetcoffee.ui.components.SearchBar
import com.goggxi.jetcoffee.ui.components.SectionText
import com.goggxi.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme() {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())) {
        Banner()
        SectionText(stringResource(R.string.section_category))
        CategoryRow()
        SectionText(stringResource(R.string.section_favorite_menu))
        MenuRow(dummyMenu)
        SectionText(stringResource(R.string.section_best_seller_menu))
        MenuRow(dummyBestSellerMenu)
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun BannerPreview() {
    JetCoffeeTheme {
        Banner()
    }
}


@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryRowPreview() {
    JetCoffeeTheme {
        CategoryRow()
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu = menu)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MenuRowPreview() {
    JetCoffeeTheme {
        MenuRow(listMenu = dummyMenu)
    }
}

