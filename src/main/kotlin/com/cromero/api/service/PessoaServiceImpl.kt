package com.cromero.api.service

import com.cromero.api.repository.Pessoa
import com.cromero.api.repository.PessoaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class PessoaServiceImpl @Autowired constructor(val repository: PessoaRepository) : PessoaService {

    override fun salvarPessoa(pessoa: PessoaDTO): PessoaDTO {

        triagemSalvarPessoa(pessoa)

        return this.repository.save(pessoa.creatPessoaEntity()).let { p -> PessoaDTO.creatFromUserEntity(p) }


    }

    override fun listarTodasPessoas(): List<PessoaDTO> {


        return this.repository.findAll().map { it -> PessoaDTO.creatFromUserEntity(it) }
    }

    override fun buscarPorId(id: Long): PessoaDTO {

        if (id == null)
            throw RegraNegocioException("Pessoa não localizada")

        return this.repository.findById(id)?.let { optional ->

            if (optional.isPresent) {

                return PessoaDTO.creatFromUserEntity(optional.get())

            } else {

                throw RegraNegocioException("Pessoa não localizada")
            }
        }


    }

    override fun listarComFiltros(page: Pageable, query: Specification<Pessoa?>?): Page<PessoaDTO> {


        return this.repository.findAll(query, page).map { it -> PessoaDTO.creatFromUserEntity(it) }
            ?: throw RegraNegocioException("Nenhuma Pessoa localizada")
    }

    override fun deletar(id: Long) {


        if (id == null)
            throw RegraNegocioException("Pessoa não informada")

        this.repository.deleteById(id)
    }


    fun triagemSalvarPessoa(pessoa: PessoaDTO) {

        if (pessoa == null)
            throw RegraNegocioException("Formulario não preenchido")


        if (pessoa.nome.isNullOrBlank())
            throw RegraNegocioException("Operação não permitida. Não é possivel salvar uma pessoa sem nome")

        if (pessoa.setor == null)
            throw RegraNegocioException("Não permitido salvar pessoa sem informar um Setor")


    }

}