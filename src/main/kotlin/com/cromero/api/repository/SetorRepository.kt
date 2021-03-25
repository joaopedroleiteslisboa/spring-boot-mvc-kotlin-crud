package com.cromero.api.repository

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SetorRepository : JpaRepository<Setor, Long>, JpaSpecificationExecutor<Setor> {

        @Query(value = "select * from Setor s where upper(s.nome) = upper(?1) " ,nativeQuery = true)
        fun findByNome(nome: String): Optional<Setor>
}
