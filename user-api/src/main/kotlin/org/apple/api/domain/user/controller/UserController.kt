package org.apple.api.domain.user.controller

import org.apple.api.domain.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping("/getUser")
    fun getUser() : String {
        val user = userService.getUserWithThrow(1)
        return "123"
    }
}