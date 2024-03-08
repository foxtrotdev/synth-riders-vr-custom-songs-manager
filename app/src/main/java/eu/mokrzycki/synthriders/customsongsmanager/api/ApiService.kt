package eu.mokrzycki.synthriders.customsongsmanager.api

import eu.mokrzycki.synthriders.customsongsmanager.api.beatmaps.id.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/beatmaps/{id}")
    fun getBeatmapById(
        @Path("id") currency: Int,
    ): Call<MusicResponse>
}