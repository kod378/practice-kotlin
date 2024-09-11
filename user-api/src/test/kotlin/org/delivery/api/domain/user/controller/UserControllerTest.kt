//package org.delivery.api.domain.user.controller
//
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.core.spec.style.DescribeSpec
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class UserControllerTest(
//    @Autowired private val mockMvc: MockMvc
//) : DescribeSpec({
//
//    describe("사용자 등록 테스트") {
//        it("사용자 등록 성공") {
//            mockMvc.perform(
//                post("/api/user")
//                    .contentType("application/json")
//                    .content("""
//                        {
//                            "name": "홍길동",
//                            "email": "test@email.com"
//                        }
//                    """.trimIndent())
//            ).andExpect(status().isOk)
//        }
//    }
//})