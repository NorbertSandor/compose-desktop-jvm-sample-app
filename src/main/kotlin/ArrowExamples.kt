package com.example.domain

import arrow.optics.optics

@optics
data class Street(val number: Int, val name: String) {
    companion object {}

    override fun toString() = "$name $number."
}

@optics
data class Address(val city: String, val street: Street) {
    companion object {}

    override fun toString() = "$city, $street"
}

@optics
data class Company(val name: String, val address: Address) {
    companion object
}

@optics
data class Employee(val name: String, val company: Company?) {
    companion object
}
