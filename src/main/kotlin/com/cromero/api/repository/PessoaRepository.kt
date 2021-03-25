package com.cromero.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository: JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {


}