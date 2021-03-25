package com.cromero.api.repository.specification

import com.cromero.api.repository.Pessoa
import com.cromero.api.repository.Setor
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.Root


class PessoaSpecification {


    companion object {

        fun porID(id: Long?): Specification<Pessoa?>? {
            return if (id == null) {
                null
            } else {
                Specification { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    cb.equal(
                        root.get<Any>("id"), id
                    )
                }
            }
        }


        fun porSetorId(idSetor: Long?): Specification<Pessoa?>? {
            return if (idSetor == null) {
                null
            } else {
                Specification<Pessoa?> { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val join: Join<Any, Any> = root.join("setor")
                    cb.equal(join.get<Any>("id"), idSetor)
                }
            }
        }


        fun porContatoId(idContato: Long?): Specification<Pessoa?>? {
            return if (idContato == null) {
                null
            } else {
                Specification<Pessoa?> { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val join: Join<Any, Any> = root.join("contato")
                    cb.equal(join.get<Any>("id"), idContato)
                }
            }
        }


        fun porNumeroTelefone(numero: String?): Specification<Pessoa?>? {
            return if (numero == null) {
                null
            } else {
                Specification<Pessoa?> { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val Pessoa =
                        root.join<Any, Any>("contato")
                    cb.like(cb.upper(Pessoa.get("numero")), "%" + numero.toUpperCase() + "%")
                }
            }
        }


        fun porNomePessoa(nome: String?): Specification<Pessoa?>? {
            return if (nome == null) {
                null
            } else {
                Specification<Pessoa?> { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val Pessoa =
                        root.join<Any, Any>("nome")
                    cb.like(cb.upper(Pessoa.get("nome")), "%" + nome.toUpperCase() + "%")
                }
            }
        }


        fun porEmailPessoa(email: String?): Specification<Pessoa?>? {
            return if (email == null) {
                null
            } else {
                Specification<Pessoa?> { root: Root<Pessoa?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val Pessoa =
                        root.join<Any, Any>("contato")
                    cb.like(cb.upper(Pessoa.get("email")), "%" + email.toUpperCase() + "%")
                }
            }
        }


    }

}