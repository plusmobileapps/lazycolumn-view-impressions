package com.plusmobileapps.lazygridviewimpression

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val tracker = AnalyticsTracker()

    private var _state: MutableStateFlow<List<Person>> = MutableStateFlow(people)
    val state: StateFlow<List<Person>> get() = _state

    fun onDeleteClicked(person: Person) {
        _state.value = _state.value.toMutableList().also { it.remove(person) }
    }

    fun onPersonViewed(person: Person) {
        tracker.onPersonViewed(person)
    }
}

data class Person(val key: String, val name: String)

private val people = listOf(
    Person("analytics-key1", "Joe"),
    Person("analytics-key2", "John"),
    Person("analytics-key3", "Annie"),
    Person("analytics-key4", "Andy"),
    Person("analytics-key5", "Connor"),
    Person("analytics-key6", "Chris"),
    Person("analytics-key7", "Jeffrey"),
    Person("analytics-key8", "Mike"),
    Person("analytics-key9", "Trevor"),
    Person("analytics-key10", "Ryan"),
    Person("analytics-key11", "Rosie"),
    Person("analytics-key12", "Emily"),
    Person("analytics-key13", "Justice"),
    Person("analytics-key14", "Rob"),
    Person("analytics-key15", "Lynn"),
    Person("analytics-key16", "Oliver"),
    Person("analytics-key17", "Paula"),
    Person("analytics-key18", "Kendall"),
    Person("analytics-key19", "Eddie"),
    Person("analytics-key20", "Jeremy"),
)