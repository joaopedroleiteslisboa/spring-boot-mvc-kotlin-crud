package com.cromero.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByName(name: String): UserEntity?
    fun findByAge(age: Int): UserEntity?
}

interface PetRepository : CrudRepository<PetEntity, Long> {
    fun findByName(name: String): PetEntity?
}


@Repository
interface ContatoRepository: JpaRepository<Contato, Long>, JpaSpecificationExecutor<Contato>{

}

