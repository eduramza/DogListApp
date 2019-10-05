package com.eduramza.api

class Routes {
    companion object{
        const val BASE_URL = "https://api-iddog.idwall.co/"
        const val SIGNUP_ROUTE = "signup"
    }
}

//region - Error
const val ERROR_INVALID_EMAIL = "HTTP 400 "
const val ERROR_EMAIL_IS_EMPTY = ""

const val ERROR_INVALID_EMAIL_RESPONSE = "E-mail inválido!"
const val ERROR_EMAIL_IS_EMPTY_RESPONSE = "Este campo deve ser preenchido"
//endregion