package com.marcospb.justocallenge.domain.mappers

import com.marcospb.justocallenge.data.model.User
import com.marcospb.justocallenge.domain.models.UserDomain


fun mapUserToDomain(data: User): UserDomain {
    return UserDomain(
        photo = data.picture?.medium ?: "",
        username = data.login?.username ?: "",
        email = data.email ?: "",
        phone = data.phone ?: "",
        dir = "${data.location?.city}",
        postalCode = data.location?.postcode ?: "",
        city = data.location?.city ?: "",
        state = data.location?.state ?: ""
    )
}