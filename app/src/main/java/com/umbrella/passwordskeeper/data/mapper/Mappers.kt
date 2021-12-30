package com.umbrella.passwordskeeper.data.mapper

import com.umbrella.passwordskeeper.data.model.PasswordDTO
import com.umbrella.passwordskeeper.domain.entities.Password

fun PasswordDTO.toDomainModel() = Password(site, password)
fun List<PasswordDTO>.toDomainModel() = this.map { it.toDomainModel() }
fun Password.toDataModel() = PasswordDTO(site, password)