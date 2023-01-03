package com.example.sensimate.ui.navigation

import android.util.Log

const val TITLE_OF_EVENT = "titleOfEvent"
const val TIME_OF_EVENT = "timeOfEvent"
const val LOCATION_OF_EVENT = "locationOfEvent"
const val ALLERGENS = "allergens"
const val DESCRIPTION_OF_EVENT = "descriptionOfEvent"

sealed class Screen(val route: String) {
    object CookieScreen : Screen(route = "cookie")
    object ChooseSignUpScreen : Screen(route = "chooseSignup")
    object SignUpWithMail : Screen(route = "mailSignup")
    object Login : Screen(route = "login")

    object EventScreen : Screen(route = "event")
    object ExtendedEventScreen :
        Screen(route = "extendedEvent/{$TITLE_OF_EVENT}/{$TIME_OF_EVENT}/{$LOCATION_OF_EVENT}/{$ALLERGENS}/{$DESCRIPTION_OF_EVENT}") {
        fun passArguments(
            title: String,
            time: String,
            location: String,
            allergens: String,
            description: String
        ): String {
            val test = "extendedEvent/$title/$time/$location/$allergens/$description"
            Log.d("navigation", test)
            return test
        }
    }


    object ProfileScreen : Screen(route = "profile")
    object CreateEventScreen : Screen(route = "createEvent")
    object QuestionPageScreen : Screen(route = "questionPage")
    object CreateMultpleChoiceQuestionScreen : Screen(route = "CreateMultpleChoiceQuestion")
    object EditEvent : Screen(route = "editEvent")
    object EditPage : Screen(route = "editPage")
    object EditSurvey : Screen(route = "editSurvey")
    object EditSurveyPage : Screen(route = "editSurveyPage")
    object EventScreenEmployee : Screen(route = "eventScreenEmployee")
    object Registerscreen : Screen(route = "register")
    object EditAgeScreen : Screen(route = "editAge")
    object EditEmailScreen : Screen(route = "editEmail")
    object EditGenderScreen : Screen(route = "editGender")
    object EditPasswordScreen : Screen(route = "editPassword")
    object EditPostalScreen : Screen(route = "editPostal")
    object EditProfileScreen : Screen(route = "editProfile")
    object Survey : Screen(route = "survey1")
    object Survey2 : Screen(route = "survey2")
    object Survey3 : Screen(route = "survey3")
    object Survey4 : Screen(route = "survey4")
}