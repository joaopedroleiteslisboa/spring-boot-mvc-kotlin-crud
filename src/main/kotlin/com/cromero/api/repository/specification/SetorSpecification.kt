package com.cromero.api.repository.specification

import com.cromero.api.repository.Setor
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class SetorSpecification {

    companion object {

        fun porID(id: Long?): Specification<Setor?>? {
            return if (id == null) {
                null
            } else {
                Specification { root: Root<Setor?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    cb.equal(
                        root.get<Any>(
                            "id"
                        ), id
                    )
                }
            }
        }

        fun porNome(nome: String?): Specification<Setor?>? {
            return if (nome == null) {
                null
            } else {
                Specification { root: Root<Setor?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%")
                }
            }
        }
    }
}
