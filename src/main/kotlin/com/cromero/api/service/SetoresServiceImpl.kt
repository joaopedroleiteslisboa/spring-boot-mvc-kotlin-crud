package com.cromero.api.service

import com.cromero.api.repository.Setor
import com.cromero.api.repository.SetorRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class SetoresServiceImpl(val repository: SetorRepository) : SetorService {


    override fun salvarSetor(setor: Setor): Setor {


        if (setor.name.isNullOrBlank())
            throw RegraNegocioException("Setor com nome Invalido")


        if (this.repository.findByName(setor.name).isPresent)
            throw RegraNegocioException("Setor informado já existente")


        return this.repository.save(setor)

    }

    override fun listarSetores(): List<Setor> {
        return this.repository.findAll()
    }

    override fun buscarPorId(id: Long): Setor {
        if(id == null)
            throw RegraNegocioException("Id não informado")

        return this.repository.findById(id)?.let { optional ->

            if(!optional.isPresent)
                throw RegraNegocioException("Setor não localizado")
            else
               return  optional.get()
        }

    }

    override fun listarComFiltros(page: Pageable, query: Specification<Setor?>?): Page<Setor> {



        return this.repository.findAll(query, page)
    }
}
