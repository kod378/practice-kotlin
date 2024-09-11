//package org.delivery.api.domain.user.service
//
//import io.kotest.assertions.throwables.shouldThrow
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.matchers.shouldBe
//import org.delivery.db.user.UserRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.ActiveProfiles
//
//@SpringBootTest
//@ActiveProfiles("test")
//class UserServiceTest
//    @Autowired constructor(
//        val userService: UserService
//    )
//    : BehaviorSpec({
//
//    Given("사용자가 없는 상태"){
//        When("사용자 불러오기 하면"){
//            Then("에러 처리"){
//                shouldThrow<RuntimeException> {
//                    userService.getUserWithThrow(1)
//                }
//            }
//        }
//    }
//})