package com.plusmobileapps.lazygridviewimpression

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plusmobileapps.lazygridviewimpression.ui.theme.LazyGridViewImpressionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyGridViewImpressionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListView()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyGridViewImpressionTheme {
        Greeting("Android")
    }
}

@Composable
fun ListView() {
    val lazyListState = rememberLazyListState()
    people.forEach { person ->
        ItemImpression(key = person.first, lazyListState = lazyListState) {
            Log.d("Analytics Tracker", "Item was viewed: ${person.second}")
        }
    }
    LazyColumn(state = lazyListState) {
        items(people.size, key = { people[it].first }) {
            val (key, name) = people[it]
            PersonRow(name)
        }
    }
}

@Composable
fun ItemImpression(key: String, lazyListState: LazyListState, onItemViewed: () -> Unit) {
    var isItemViewed by remember {
        mutableStateOf(false)
    }
    val isItemWithKeyInView by remember {
        derivedStateOf {
            lazyListState.layoutInfo
                .visibleItemsInfo
                .any { it.key == key }
                    && !isItemViewed
        }
    }
    if (isItemWithKeyInView) {
        LaunchedEffect(Unit) {
            isItemViewed = true
            onItemViewed()
        }
    }
}

@Composable
fun PersonRow(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(text = name)
    }
}

private val people = listOf(
    "analytics-key1" to "Joe",
    "analytics-key2" to "John",
    "analytics-key3" to "Annie",
    "analytics-key4" to "Andy",
    "analytics-key5" to "Connor",
    "analytics-key6" to "Chris",
    "analytics-key7" to "Jeffrey",
    "analytics-key8" to "Mike",
    "analytics-key9" to "Trevor",
    "analytics-key10" to "Ryan",
    "analytics-key11" to "Rosie",
    "analytics-key12" to "Emily",
    "analytics-key13" to "Justice",
    "analytics-key14" to "Rob",
    "analytics-key15" to "Lynn",
    "analytics-key16" to "Oliver",
    "analytics-key17" to "Paula",
    "analytics-key18" to "Kendall",
    "analytics-key19" to "Eddie",
    "analytics-key20" to "Jeremy",
)