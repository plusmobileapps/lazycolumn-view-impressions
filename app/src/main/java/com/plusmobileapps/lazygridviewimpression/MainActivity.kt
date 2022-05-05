package com.plusmobileapps.lazygridviewimpression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plusmobileapps.lazygridviewimpression.ui.theme.LazyColumnViewImpressionTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnViewImpressionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumnImpressionSample(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun LazyColumnImpressionSample(viewModel: MainViewModel) {
    val state = viewModel.state.collectAsState()
    ListView(
        people = state.value,
        onItemViewed = viewModel::onPersonViewed,
        onDeleteClicked = viewModel::onDeleteClicked
    )
}

@Composable
fun ListView(
    people: List<Person>,
    onDeleteClicked: (Person) -> Unit,
    onItemViewed: (Person) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(people.size, key = { people[it].key }) {
            val person = people[it]
            PersonRow(lazyListState, person, onDeleteClicked, onItemViewed)
        }
    }
}

@Composable
fun PersonRow(lazyListState: LazyListState, person: Person, onDeleteClicked: (Person) -> Unit, onItemViewed: (Person) -> Unit) {
    ItemImpression(key = person.key, lazyListState = lazyListState) {
        onItemViewed(person)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = person.name)

            IconButton(onClick = { onDeleteClicked(person) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun ItemImpression(key: Any, lazyListState: LazyListState, onItemViewed: () -> Unit) {
    val isItemWithKeyInView by remember {
        derivedStateOf {
            lazyListState.layoutInfo
                .visibleItemsInfo
                .any { it.key == key }
        }
    }
    if (isItemWithKeyInView) {
        LaunchedEffect(Unit) { onItemViewed() }
    }
}