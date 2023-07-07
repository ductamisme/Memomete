package com.twoup.personalfinance.remote.person

import com.twoup.personalfinance.model.person.remote.PersonDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface PersonApi {
    @GET("people/{id}/")
    suspend fun getPersonByIdResponse(@Path("id") peopleId: Int): PersonDto
}