package com.aicontent.memomate.contact.data

import com.aicontent.memomate.contact.domain.Contact
import com.aicontent.memomate.core.data.ImageStorage

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}