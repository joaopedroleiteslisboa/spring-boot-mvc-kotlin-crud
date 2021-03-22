package com.cromero.api.controller

import com.cromero.api.repository.Setor
import com.cromero.api.repository.specification.SetorSpecification
import com.cromero.api.service.RegraNegocioException
import com.cromero.api.service.SetorService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/setor")
class SetorController(val service: SetorService) {

    @GetMapping
    fun listarComFiltros(
        @RequestParam(value = "id", required = false) id: Long?,
        @RequestParam(value = "name", required = false) nome: String?
    ): Page<Setor> {

        println("caiiiuu aqq")

        val sortedByPriceDesc: Pageable = PageRequest.of(0, 3, Sort.by("name").descending())

        val query: Specification<Setor?>? = Specification.where(
            SetorSpecification.porID(id)
        )?.and(SetorSpecification.porNome(nome))

        var retorno = this.service.listarComFiltros(sortedByPriceDesc, query)

        if (retorno.isEmpty)
            throw RegraNegocioException("Não foi localizado nenhum Registro")

        return retorno
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun salvarSetor(@RequestBody setor: Setor): Setor = this.service.salvarSetor(setor)

    @GetMapping(value = ["/all"])
    fun listarSetores(): List<Setor> = this.service.listarSetores()

    @GetMapping(value = ["/{id}"])
    fun buscarPorId(@PathVariable("id") id: Long): Setor = this.service.buscarPorId(id)

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    fun deletar(@PathVariable("id") id: Long) {

        this.service.deletar(id)
    }

    @ResponseStatus(HttpStatus.OK)
    fun testar(): String {

        return "ola mundo do teste"
    }
}
