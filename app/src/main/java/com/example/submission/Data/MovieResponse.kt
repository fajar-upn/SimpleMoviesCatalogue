package com.example.submission.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
	val dates: Dates? = null,
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: ArrayList<ResultsItem?>? = null,
	val totalResults: Int? = null
) : Parcelable

@Parcelize
data class Dates(
	val maximum: String? = null,
	val minimum: String? = null
) : Parcelable

@Parcelize
data class ResultsItem(
	val overview: String? = null,
	val original_language: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,
	val poster_path: String? = null,
	val backdrop_path: String? = null,
	val release_date: String? = null,
	val popularity: Double? = null,
	val vote_average: Double? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
) : Parcelable
