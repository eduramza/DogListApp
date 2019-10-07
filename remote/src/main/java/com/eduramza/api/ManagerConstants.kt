package com.eduramza.api

class Routes {
    companion object{
        const val BASE_URL = "https://api-iddog.idwall.co/"
        const val SIGNUP_ROUTE = "signup"
        const val FEED_ROUTE = "feed"
    }
}

//region - Error
const val ERROR_INVALID_EMAIL = "HTTP 400 "
const val ERROR_EMAIL_IS_EMPTY = ""

const val ERROR_INVALID_EMAIL_RESPONSE = "E-mail inválido!"
const val ERROR_EMAIL_IS_EMPTY_RESPONSE = "Este campo deve ser preenchido"
//endregion

//region - breeds
const val PUG = "pug"
const val HUSKY = "husky"
const val LABRADOR = "labrador"
const val HOUND = "hound"
//endregion

const val PATH = "path"

const val ABOUT_DEV = "Que tal me convidar para um café?"

//region - fragments
const val FRAGMENT_HOME = "home"
const val FRAGMENT_LIST = "list"
//endregion