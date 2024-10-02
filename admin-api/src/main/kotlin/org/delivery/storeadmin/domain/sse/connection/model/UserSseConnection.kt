package org.delivery.storeadmin.domain.sse.connection.model

import com.fasterxml.jackson.databind.ObjectMapper
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

class UserSseConnection private constructor(
    val uniqueKey: String,
    private val connectionPoolIfs: ConnectionPoolIfs<String, UserSseConnection>,
    private val objectMapper: ObjectMapper
) {
    val sseEmitter = SseEmitter(1000 * 60 * 30)  // ms, 30분

    init {
        sseEmitter.onTimeout {
            sseEmitter.complete()
        }

        sseEmitter.onCompletion {
            // connection pool remove
            connectionPoolIfs.onCompletionCallback(this)
        }

        connectionPoolIfs.addSession(uniqueKey, this)

        // onopen 메시지
        sendMessage(data = connected, eventName = "open")
    }

    companion object {
        fun connect(
            uniqueKey: String,
            connectionPoolIfs: ConnectionPoolIfs<String, UserSseConnection>,
            objectMapper: ObjectMapper
        ): UserSseConnection {
            return UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper)
        }
    }

    fun sendMessage(data: Any, eventName: String? = null) {
        try {
            val json = objectMapper.writeValueAsString(data)
            val event = SseEmitter.event().apply {
                eventName?.let { name(it) }
                data(json)
            }
            sseEmitter.send(event)
        } catch (e: Exception) {
            sseEmitter.completeWithError(e)
        }
    }

    override fun toString(): String {
        return "UserSseConnection(uniqueKey='$uniqueKey')"
    }

    override fun hashCode(): Int {
        return uniqueKey.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserSseConnection) return false

        return uniqueKey == other.uniqueKey
    }
}

const val connected: Boolean = true