package com.omidmohebbise.firstappkt.firstappkotlin.repository

import com.omidmohebbise.firstappkt.firstappkotlin.entity.PersonEO
import org.springframework.data.repository.CrudRepository

interface PersonJpaRepository : CrudRepository<PersonEO, Long> {
}