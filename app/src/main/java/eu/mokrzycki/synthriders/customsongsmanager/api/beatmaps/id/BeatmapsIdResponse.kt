package eu.mokrzycki.synthriders.customsongsmanager.api.beatmaps.id

import com.google.gson.annotations.SerializedName

 class MusicResponse(

     @field:SerializedName("cover_version")
	val coverVersion: Int? = null,

     @field:SerializedName("artist")
	val artist: String,

     @field:SerializedName("rating")
	val rating: String? = null,

     @field:SerializedName("description")
	val description: String? = null,

     @field:SerializedName("created_at")
	val createdAt: String? = null,

     @field:SerializedName("mapper")
	val mapper: String? = null,

     @field:SerializedName("title")
	val title: String,

     @field:SerializedName("duration")
	val duration: String,

     @field:SerializedName("score")
	val score: String? = null,

     @field:SerializedName("ost")
	val ost: Boolean? = null,

     @field:SerializedName("video_url")
	val videoUrl: String? = null,

     @field:SerializedName("updated_at")
	val updatedAt: String? = null,

     @field:SerializedName("download_url")
	val downloadUrl: String? = null,

     @field:SerializedName("collaborators")
	val collaborators: List<Any?>? = null,

     @field:SerializedName("id")
	val id: Int,

     @field:SerializedName("published_at")
	val publishedAt: String? = null,

     @field:SerializedName("bpm")
	val bpm: String? = null,

     @field:SerializedName("production_mode")
	val productionMode: Boolean? = null,

     @field:SerializedName("cover_url")
	val coverUrl: String? = null,

     @field:SerializedName("beat_saber_convert")
	val beatSaberConvert: Boolean? = null,

     @field:SerializedName("filename_original")
	val filenameOriginal: String? = null,

     @field:SerializedName("play_count_daily")
	val playCountDaily: Int? = null,

     @field:SerializedName("published")
	val published: Boolean? = null,

     @field:SerializedName("play_count")
	val playCount: Int? = null,

     @field:SerializedName("version")
	val version: Int? = null,

     @field:SerializedName("downvote_count")
	val downvoteCount: Int? = null,

     @field:SerializedName("download_count")
	val downloadCount: Int? = null,

     @field:SerializedName("explicit")
	val explicit: Boolean? = null,

     @field:SerializedName("filename")
	val filename: String? = null,

     @field:SerializedName("upvote_count")
	val upvoteCount: Int? = null,

     @field:SerializedName("difficulties")
	val difficulties: List<String>,

     @field:SerializedName("preview_url")
	val previewUrl: String? = null,

     @field:SerializedName("vote_diff")
	val voteDiff: Int? = null,

     @field:SerializedName("youtube_url")
	val youtubeUrl: String? = null,

     @field:SerializedName("user")
	val user: User? = null,

     @field:SerializedName("hash")
	val hash: String? = null
)

 class User(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("avatar_filename")
	val avatarFilename: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)
