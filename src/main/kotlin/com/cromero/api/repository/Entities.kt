package com.cromero.api.repository

import javax.persistence.*

@Entity
data class UserEntity(
    @Id @GeneratedValue val id: Long? = null,
    val name: String,
    val age: Int,
    val favoriteNumber: String
)

@Entity
data class PetEntity(@Id @GeneratedValue val id: Long? = null, val name: String, val age: Int)

@Entity
data class Setor(@Id @GeneratedValue val id: Long? = null, val nome: String)


@Entity
data class Pessoa(
    @Id @GeneratedValue var id: Long? = null,

    var nome: String,

    var sobreNome: String,

    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "contato_id") var contato: Contato?,

    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "setor_id") var setor: Setor?

)


@Entity
data class Contato(@Id @GeneratedValue var id: Long? = null, var numero: String, var email: String)