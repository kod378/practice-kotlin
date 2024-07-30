package org.apple.db.user

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ContextConfiguration
internal class UserRepositoryTest
    @Autowired constructor(val userRepository: UserRepository
    ): BehaviorSpec({

    Given("사용자가 없는 상황"){
        When("사용자를 불러오면"){
            val user = userRepository.findUserById(1)
            Then("null을 반환"){
                user shouldBe null
            }
        }
    }
})