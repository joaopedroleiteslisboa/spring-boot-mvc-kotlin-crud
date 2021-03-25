package com.cromero.api.controller


import com.cromero.api.repository.Pessoa
import com.cromero.api.repository.specification.PessoaSpecification
import com.cromero.api.service.PessoaDTO
import com.cromero.api.service.PessoaService
import com.cromero.api.service.RegraNegocioException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pessoa")
class PessoaController @Autowired constructor(val service: PessoaService) {


    @GetMapping
    fun listarComFiltros(


        @RequestParam(value = "nome", required = false) nome: String?,
        @RequestParam(value = "idPessoa", required = false) idPessoa: Long?,
        @RequestParam(value = "email", required = false) email: String?,
        @RequestParam(value = "idContato", required = false) idContato: Long?

    ): Page<PessoaDTO> {


        val sortedByPriceDesc: Pageable = PageRequest.of(0, 100, Sort.by("nome").descending())

        val query: Specification<Pessoa?>? = Specification.where(
            PessoaSpecification.porID(idPessoa)
        )?.and(PessoaSpecification.porNomePessoa(nome))
            ?.and(PessoaSpecification.porContatoId(idContato))
            ?.and(PessoaSpecification.porEmailPessoa(email))

        var retorno = this.service.listarComFiltros(sortedByPriceDesc, query)

        if (retorno.isEmpty)
            throw RegraNegocioException("Não foi localizado nenhum Registro")

        return retorno
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun salvarPessoa(@RequestBody form: PessoaDTO): PessoaDTO = this.service.salvarPessoa(form)

    @GetMapping(value = ["/all"])
    fun listarTodos(): List<PessoaDTO> = this.service.listarTodasPessoas()

    @GetMapping(value = ["/{id}"])
    fun buscarPorId(@PathVariable("id") id: Long): PessoaDTO = this.service.buscarPorId(id)

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    fun deletar(@PathVariable("id") id: Long) {

        this.service.deletar(id)
    }



}