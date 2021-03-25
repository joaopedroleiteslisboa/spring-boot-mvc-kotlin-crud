package com.cromero.api.service

import com.cromero.api.repository.Pessoa
import com.cromero.api.repository.Setor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface PessoaService {


    fun salvarPessoa(pessoa: PessoaDTO): PessoaDTO

    fun listarTodasPessoas(): List<PessoaDTO>

    fun buscarPorId(id: Long): PessoaDTO

    fun listarComFiltros(page: Pageable, query: Specification<Pessoa?>?): Page<PessoaDTO>

    fun deletar(id: Long)
}


