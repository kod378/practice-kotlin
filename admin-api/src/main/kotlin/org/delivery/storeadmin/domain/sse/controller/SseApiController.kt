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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


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

        // 30초마다 더미 데이터를 전송하는 스케줄러
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.scheduleAtFixedRate({
            try {
                // 클라이언트에 더미 데이터(keep-alive) 전송
                userSessionConnection.sseEmitter.send(SseEmitter.event()
                    .data("""{'keep-alive': true}""")
                    .name("keep-alive"))
            } catch (e: IOException) {
                // 예외 발생 시 SSE 연결 종료
                userSessionConnection.sseEmitter.completeWithError(e)
            }
        }, 0, 30, TimeUnit.SECONDS) // 30초마다 더미 데이터를 전송

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