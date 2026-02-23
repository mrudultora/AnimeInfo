package com.mrudultora.animeinfo.usecases

import com.mrudultora.animeinfo.network.AnimeInfoRepository
import com.mrudultora.animeinfo.network.models.AnimeInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import com.mrudultora.animeinfo.network.Result

class GetAnimeDetailsWithCharactersUseCase @Inject constructor(
    private val repository: AnimeInfoRepository
) {
    suspend operator fun invoke(animeId: Int): Result<AnimeInfo> {
        return try {
            coroutineScope {
                val detailsDeferred = async {
                    repository.getAnimeDetails(animeId)
                }

                val charactersDeferred = async {
                    repository.getAnimeCharacters(animeId)
                }

                val details = detailsDeferred.await()
                val characters = charactersDeferred.await()

                Result.Success(
                    AnimeInfo(
                        animeDetails = details,
                        animeCharacters = characters
                    )
                )
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something went wrong")
        }
    }
}