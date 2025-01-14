package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.domain.Repository
import javax.inject.Inject

class GetCountryByID @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getCountryByID(id)
}