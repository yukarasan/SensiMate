package com.example.sensimate.data

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.data.Database.fetchListOfEvents
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore

data class EventScreenState(
    val events: MutableList<Event>? = null
)

class EventDataViewModel: ViewModel() {
    val state = mutableStateOf(EventScreenState())

    init {
        getListOfEvents()
    }

    private fun getListOfEvents() {
        viewModelScope.launch {
            val eventList = fetchListOfEvents()
            state.value = state.value.copy(events = eventList)
        }
    }
}


object Database{

    fun login(){} //TODO: Hussein
    fun signUserUp(){} //TODO: Hussein
    fun deleteProfile(){} //TODO: Hussein

    fun signOut() {

    } //TODO: Yusuf

    fun editUserProfile() {

    } //TODO: Yusuf

    suspend fun fetchListOfEvents(): MutableList<Event> {
        val eventReference = db.collection("events")
        val eventList: MutableList<Event> = mutableListOf()

        try {
            eventReference.get().await().map {
                val result = it.toObject(Event::class.java)
                eventList.add(result)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getListOfEvents: $e")
        }

        Log.d("lenght", eventList.size.toString())

        return eventList
    } //TODO: Yusuf



    /*
    item.forEach { document ->
                    val eventTitle = document.get("title").toString()
                    val eventAddress = document.get("address").toString()
                    val eventDescription = document.get("description").toString()
                    val eventDistance = document.get("distanceToEvent").toString()

                    val event = Event(
                        title = eventTitle,
                        address = eventAddress,
                        description = eventDescription,
                        distanceToEvent = eventDistance
                    )
                    events.add(event)
                }
     */

    fun createEvent(){} //TODO: Ahmad
    fun editEvent(){} //TODO: Ahmad

    fun getOneEvent(Eventreference : Int){
        val tryoutdata = hashMapOf(
            "name" to "sabirin"
        )
        db.collection("hej").add(tryoutdata)

        val docRef = db.collection("tryout").document("try") //ID
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "FoUND DOCUMENT: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }

    } //TODO: Sabirin

    /*
    title
    discription
    event code
    Allergens
    Location
     */

    fun deleteEvent(){} //TODO: Sabirin
    fun getEmployeeProfiles(){} //TODO: Sabirin

    fun createEmployee(){} //TODO: Anshjyot
    fun getSurvey(){} //TODO: Anshjyot
    fun answerQuestion(){} //TODO: Anshjyot

    fun exportToExcel(){} //TODO: LATER

}