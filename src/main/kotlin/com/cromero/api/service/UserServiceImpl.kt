package com.cromero.api.service

import com.cromero.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (val userRepository: UserRepository) :UserService  {


    override fun findAll(): List<User> =userRepository.findAll().map { it.convertToUserModel() }

    override fun findByName(name: String): User? = userRepository.findByName(name)?.convertToUserModel()


    override fun addUser(user: User): User {
        val userEntity = user.toUserEntity()
        val savedEntity = userRepository.save(userEntity)
        //TODO CREATE A MAPPER FROM POJO TO DATAMODEL
        return savedEntity.convertToUserModel()
    }
}