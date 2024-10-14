package com.omidmohebbise.firstappkt.firstappkotlin.entity


import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity
class PersonEO (
    var name: String,
    var family: String,
    var age: Int,
    @Id    @GeneratedValue  var id: Long? = null
)