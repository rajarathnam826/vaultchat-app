package dev.jason.app.compose.vaultchat.core

import dev.jason.app.compose.vaultchat.core.model.device.Device
import dev.jason.app.compose.vaultchat.core.model.user.User
import dev.jason.app.compose.vaultchat.core.model.user.UserUi
import dev.jason.app.compose.vaultchat.core.model.user.toUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Global application state holder managing user sessions, device information,
 * and app visibility/connectivity state.
 */
@Suppress("ObjectPropertyName")
object AppState {

    internal val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    internal val _otherUser = MutableStateFlow<UserUi?>(null)
    val otherUser = _otherUser.asStateFlow()

    internal val _isOtherUserBlocked = MutableStateFlow(false)
    val isOtherUserBlocked = _isOtherUserBlocked.asStateFlow()

    internal val _isOtherUserConnected = MutableStateFlow<Boolean?>(null)
    val isOtherUserConnected = _isOtherUserConnected.asStateFlow()

    internal val _currentDevice = MutableStateFlow<Device?>(null)
    val currentDevice = _currentDevice.asStateFlow()

    internal val _isOnline = MutableStateFlow(true)
    val isOnline = _isOnline.asStateFlow()

    internal val _isAppInForeground = MutableStateFlow(false)
    val isAppInForeground = _isAppInForeground.asStateFlow()
}

// --- State Mutators ---

fun AppState.updateCurrentUser(user: User?) {
    _currentUser.value = user
}

fun AppState.updateOtherUser(user: UserUi?) {
    _otherUser.value = user
}

fun AppState.updateIsOtherUserBlocked(blocklist: List<User>) {
    val activeOtherUser = _otherUser.value?.toUser()
    _isOtherUserBlocked.value = activeOtherUser in blocklist
}

fun AppState.updateCurrentDevice(device: Device?) {
    _currentDevice.value = device
}

fun AppState.updateIsOtherUserConnected(isConnected: Boolean?) {
    _isOtherUserConnected.update { isConnected }
}

fun AppState.deviceOnline() {
    _isOnline.value = true
}

fun AppState.deviceOffline() {
    _isOnline.value = false
}

fun AppState.appInForeground() {
    _isAppInForeground.value = true
}

fun AppState.appInBackground() {
    _isAppInForeground.value = false
}
