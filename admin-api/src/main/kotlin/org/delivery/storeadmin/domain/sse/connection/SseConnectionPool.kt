package org.delivery.storeadmin.domain.sse.connection

import org.delivery.common.log.logger
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class SseConnectionPool: ConnectionPoolIfs<String, UserSseConnection> {

    val log = logger()

    companion object {
        val connectionPool = ConcurrentHashMap<String, UserSseConnection>()
    }

    override fun addSession(uniqueKey: String, session: UserSseConnection) {
        connectionPool[uniqueKey] = session
    }
    override fun getSession(uniqueKey: String): UserSseConnection? {
        return connectionPool[uniqueKey]
    }

    override fun onCompletionCallback(session: UserSseConnection) {
        log.info("call back connection pool completion : $session")
        connectionPool.remove(session.uniqueKey)
    }
}