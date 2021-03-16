package com.cromero.api.service

import com.cromero.api.repository.Setor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface SetorService {




    fun salvarSetor(setor: Setor): Setor

    fun listarSetores():List<Setor>


     fun buscarPorId(id: Long): Setor

     fun listarComFiltros(page: Pageable, query: Specification<Setor?>?): Page<Setor>


}
