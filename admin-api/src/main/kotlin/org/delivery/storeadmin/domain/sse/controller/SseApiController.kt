package org.delivery.storeadmin.domain.sse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.annotations.Parameter
import org.delivery.common.log.logger
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter

@RestController
@RequestMapping("/api/sse")
class SseApiController(
    private val sseConnectionPool: SseConnectionPool,
    private val objectMapper: ObjectMapper
) {
    val log = logger()

    @GetMapping(path = ["/connect"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun connect(
        @Parameter(hidden = true)
        @AuthenticationPrincipal userSession: UserSession
    ): ResponseBodyEmitter {
        log.info("login user $userSession")

        val userSessionConnection = UserSseConnection.connect(
//            userSession.storeId.toString(),
            userSession.storeResponse?.id.toString(),
            sseConnectionPool,
            objectMapper
        )

        return userSessionConnection.sseEmitter
    }

    @GetMapping("/push-event")
    fun pushEvent(
        @Parameter(hidden = true)
        @AuthenticationPrincipal userSession: UserSession
    ) {
        val userSseConnection = sseConnectionPool.getSession(userSession.storeResponse?.id.toString())

        userSseConnection?.sendMessage("hello world")
    }
}