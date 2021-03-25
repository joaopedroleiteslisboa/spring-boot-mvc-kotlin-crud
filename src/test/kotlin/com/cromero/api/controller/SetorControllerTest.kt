package com.cromero.api.controller

import com.cromero.api.repository.Setor
import com.cromero.api.repository.SetorRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class SetorControllerTest @Autowired constructor(

    private val repository: SetorRepository,
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper

) {

    val path_setor = "/setor"

    @Test
    fun `Valida retorno da lista de setores`() {

        val setores = arrayListOf<Setor>(
            Setor(nome = "SETOR 1"),
            Setor(nome = "SETOR 2"),
            Setor(nome = "SETOR 3"),
            Setor(nome = "SETOR 4"),
            Setor(nome = "SETOR 5")

        )

        this.repository.saveAll(setores)

        mockMvc.get(path_setor).andDo { println() }
            .andExpect {
                status { isOk }

                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun `criar novos setores`() {

        var setores = Setor(nome = "SETOR 1")

        var json = objectMapper.writeValueAsString(setores)

        mockMvc.post(path_setor) {

            content = json
            contentType = MediaType.APPLICATION_JSON
        }.andDo { print() }

            .andExpect { status { isCreated } }
    }

    @Test
    fun `apagar um setor`() {

        val setores = arrayListOf<Setor>(
            Setor(nome = "SETOR 1"),
            Setor(nome = "SETOR 2"),
            Setor(nome = "SETOR 3"),
            Setor(nome = "SETOR 4"),
            Setor(nome = "SETOR 5")

        )

        this.repository.saveAll(setores)

        this.mockMvc.delete("$path_setor/1")
            .andDo { print() }
            .andExpect { status { isOk } }
    }



}
