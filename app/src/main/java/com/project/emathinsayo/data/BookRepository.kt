package com.project.emathinsayo.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import com.project.emathinsayo.common.DataStorageHelper
import com.project.emathinsayo.data.entity.Favorites
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize
import timber.log.Timber

interface BookRepository {
    val user: StateFlow<UserProfile?>
    suspend fun updateUserProfile(name: String, @DrawableRes profilePic: Int)
    suspend fun getUserProfile(): UserProfile?
    suspend fun addToFavorites(title: String):Long
    suspend fun removeToFavorites(title: String)
    fun getFavorites(): Flow<List<Any>>
    fun getFavoriteTitles(): Flow<List<String>>

    suspend fun updateScore(score: Int, level: String)
    suspend fun getScore(level: String): Int?
    suspend fun getAllScore(): Map<String, Int?>
}

class BookRepositoryImpl(private val dataStorageHelper: DataStorageHelper, private val database: AppDatabase): BookRepository {
    override val user = MutableStateFlow<UserProfile?>(null)

    override suspend fun updateUserProfile(name: String, @DrawableRes profilePic: Int) {
        dataStorageHelper.apply {
            saveValue("UserName", name)
            saveValue("UserProfile", profilePic)
        }
        Timber.d("updateUserProfile -> $profilePic")
        user.emit(UserProfile(name,profilePic))
    }



    override suspend fun getUserProfile(): UserProfile? {
        val userName = dataStorageHelper.getValue<String>("UserName") ?: return null
        val profile = dataStorageHelper.getValue<Int>("UserProfile") ?: return null
        Timber.d("getUserProfile -> $profile")
        user.emit(UserProfile(userName,profile))
        return UserProfile(userName, profile)
    }

    override suspend fun addToFavorites(title: String): Long {
        return database.favoriteDao().insert(Favorites(title = title))
    }

    override suspend fun removeToFavorites(title: String) {
        return database.favoriteDao().deleteByTitle(title)
    }

    override fun getFavoriteTitles(): Flow<List<String>> = database.favoriteDao().allIds()

    override suspend fun updateScore(score: Int, level: String) {
        dataStorageHelper.apply {
            saveValue("$level", score)
        }
    }

    override suspend fun getScore(level: String): Int? {
        return dataStorageHelper.getValue<Int?>("$level")
    }

    override fun getFavorites(): Flow<List<Favorites>> = database.favoriteDao().all()

    override suspend fun getAllScore(): Map<String, Int?> {
        return mapOf(
            "Addition" to dataStorageHelper.getValue<Int?>("Addition"),
            "Subtraction" to dataStorageHelper.getValue<Int?>("Subtraction"),
            "Multiplication" to dataStorageHelper.getValue<Int?>("Multiplication"),
            "Dividing" to dataStorageHelper.getValue<Int?>("Dividing"),
            "Addingf" to dataStorageHelper.getValue<Int?>("Addingf"),
            "Subtractingf" to dataStorageHelper.getValue<Int?>("Subtractingf"),
            "Multiplyingf" to dataStorageHelper.getValue<Int?>("Multiplyingf"),
            "Dividingf" to dataStorageHelper.getValue<Int?>("Dividingf"),
            "Addsubd" to dataStorageHelper.getValue<Int?>("Addsubd"),
            "Multiplyingd" to dataStorageHelper.getValue<Int?>("Multiplyingd"),
            "Dived" to dataStorageHelper.getValue<Int?>("Dived"),
            "Takefinalquiz" to dataStorageHelper.getValue<Int?>("Takefinalquiz")
        )
    }

}

@Parcelize
data class UserProfile(
    @SerializedName("name") val name: String? = null,
    @SerializedName("profilePic") val profilePic: Int? = null
): Parcelable