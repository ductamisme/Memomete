package com.twoup.personalfinance.local.infor.avatar

import com.twoup.personalfinance.model.avatar.local.AvatarEntity

data class ContactListState(
    val contacts: List<AvatarEntity> = emptyList(),
    val recentlyAddedContacts: List<AvatarEntity> = emptyList(),
    val selectedContact: AvatarEntity? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)
