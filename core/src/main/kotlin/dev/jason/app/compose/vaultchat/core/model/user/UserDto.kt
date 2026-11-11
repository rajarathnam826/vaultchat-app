package dev.jason.app.compose.vaultchat.core.model.user

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserDto(
    val uid: String,
    val displayName: String,
    val profilePictureUrl: String,
    val status: Status,
    val lastSeen: String
) {
    @Serializable
    enum class Status {
        Offline, Online
    }
}

fun UserDto.toUser() = User(
    uid = uid,
    displayName = displayName,
    profilePictureUrl = profilePictureUrl,
    status = status.toStatus(),
    lastSeen = LocalDateTime.parse(lastSeen)
)

fun User.toDto() = UserDto(
    uid = uid,
    displayName = displayName,
    profilePictureUrl = profilePictureUrl,
    status = status.toStatus(),
    lastSeen = lastSeen.toString()
)

private fun UserDto.Status.toStatus() = User.Status.valueOf(this.name)
private fun User.Status.toStatus() = UserDto.Status.valueOf(this.name)
