package com.olamachia.simpleblogapp.util

interface EntityMapper<Entity, Domain> {

    fun mapFromEntity(entity : Entity) : Domain

    fun mapToEntity(domainModel : Domain) : Entity
}