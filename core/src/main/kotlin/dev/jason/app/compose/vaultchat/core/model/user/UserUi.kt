package dev.jason.app.compose.vaultchat.core.model.user

import androidx.compose.runtime.Immutable
import dev.jason.app.compose.vaultchat.core.model.user.User.Status
import java.time.LocalDateTime

@Immutable
data class UserUi(
    val uid: String,
    val displayName: String,
    val profilePictureUrl: String,
    val status: Status,
    val lastSeen: LocalDateTime
)

fun User.toUi() = UserUi(
    uid = uid,
    displayName = displayName,
    profilePictureUrl = profilePictureUrl,
    status = status,
    lastSeen = lastSeen
)

fun UserUi.toUser() = User(
    uid = uid,
    displayName = displayName,
    profilePictureUrl = profilePictureUrl,
    status = status,
    lastSeen = lastSeen
)