package com.plusmobileapps.lazygridviewimpression

import android.util.Log

class AnalyticsTracker {

    private val recordedPeople = hashSetOf<String>()

    fun onPersonViewed(person: Person) {
        if (recordedPeople.contains(person.key)) return
        recordedPeople.add(person.key)
        Log.d("Item Impression", person.toString())
    }
}