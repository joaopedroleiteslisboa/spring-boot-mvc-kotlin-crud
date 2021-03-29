package com.cromero.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.HashMap


@RestController
@RequestMapping("/cep")
class CepController {


    @GetMapping("/{cep}")
    fun getEndereco(@PathVariable cep: String): ResponseEntity<EnderecoDTO> {

        val uri = "http://viacep.com.br/ws/{cep}/json/";

        val params = mapOf("cep" to cep)

        var endereco: ResponseEntity<EnderecoDTO>? = null

        endereco = RestTemplate().getForEntity(uri, EnderecoDTO::class.java, params)

        println(endereco.toString())


        var contador = 0;

        while(contador < 10){

            contador++



            println("vai fazer 10 vezes")

        }

        return endereco;
    }


}