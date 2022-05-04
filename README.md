# Jetpack Compose Lazy Column Analytics

Jetpack Compose sample of tracking items viewed in a lazy column exactly once for view impression analytics

```kotlin
@Composable
fun ListView() {
    val people: List<Pair<String, String>> = TODO()
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
```

## Resources

* [Stackoverflow answer](https://stackoverflow.com/a/70951303/7900721)