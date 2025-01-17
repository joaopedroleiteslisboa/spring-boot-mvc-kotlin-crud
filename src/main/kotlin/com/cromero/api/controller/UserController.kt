package com.cromero.api.controller

import com.cromero.api.service.Color
import com.cromero.api.service.UserNotFoundException
import com.cromero.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping
    fun findAll() = userService.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: User) =
        User.createFromUserModel(userService.addUser(user.toUserModel()))


    @GetMapping(value = ["/{name}"])
    fun getUser(@PathVariable("name") name: String) =
        userService.findByName(name)?.let { User.createFromUserModel(it) }
            ?: throw UserNotFoundException("user not found")


    @GetMapping(value = ["/color/{name}"])
    fun getColorTextByName(@PathVariable("name") name: String) =
        userService.findByName(name)?.let {
            val createFromUserModel = User.createFromUserModel(it)
            when (val color = createFromUserModel.color) {
                Color.BLACK -> "Your black color description is ${color.description}"
                Color.RED -> "Your red color description is ${color.description}"
                Color.GREEN -> "Your green color description is ${color.description}"
                Color.BLUE -> "Your blue color description is ${color.description}"
            }
        } ?: throw UserNotFoundException("user not found")


    @GetMapping(value = ["/legalAge/{name}"])
    fun isLegalAge(@PathVariable("name") name: String) =
        userService.findByName(name)?.let {
            val createFromUserModel = User.createFromUserModel(it)
            if (createFromUserModel.age in 0..18) {
                "is not legal age"
            } else {
                "is legal age"
            }
        } ?: throw UserNotFoundException("name not found")


    /**
     * Find all users containing {letters} in its name. It returns all users if letters is null
     */
    @GetMapping(value = ["/findAllContainsLetters", "/findAllContainsLetters/{letters}"])
    fun findAllContainsLetter(@PathVariable("letters") letters: String?) =
        userService.findAll().filter { user: com.cromero.api.service.User ->
            letters?.let { cad: String -> user.name.toUpperCase().contains(cad.toUpperCase()) }
                ?: true
        }.sortedBy { it.name }
}
