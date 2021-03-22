package com.cromero.api.repository

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface SetorRepository : JpaRepository<Setor, Long>, JpaSpecificationExecutor<Setor> {

        fun findByName(name: String): Optional<Setor>
}
