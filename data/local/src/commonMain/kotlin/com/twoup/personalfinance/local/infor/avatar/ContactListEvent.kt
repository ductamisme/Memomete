package com.twoup.personalfinance.local.infor.avatar

import com.twoup.personalfinance.model.avatar.local.AvatarEntity

sealed interface ContactListEvent {
    object OnAddNewContactClick: ContactListEvent
    object DismissContact: ContactListEvent
    data class OnFirstNameChanged(val value: String): ContactListEvent
    data class OnLastNameChanged(val value: String): ContactListEvent
    data class OnEmailChanged(val value: String): ContactListEvent
    data class OnPhoneNumberChanged(val value: String): ContactListEvent
    class OnPhotoPicked(val bytes: ByteArray): ContactListEvent
    object OnAddPhotoClicked: ContactListEvent
    object SaveContact: ContactListEvent
    data class SelectContact(val avatarEntity : AvatarEntity): ContactListEvent
    data class EditContact(val avatarEntity: AvatarEntity): ContactListEvent
    object DeleteContact: ContactListEvent
}