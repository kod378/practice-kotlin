package org.delivery.api.config.health

import org.delivery.api.common.rabbitmq.Producer
import org.delivery.common.log.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api")
class HealthOpenApiController(
    private val producer: Producer
) {

    val log = logger()

    @GetMapping("/health")
    fun health() {
        log.info("health call")
        producer.producer("delivery.exchange", "delivery.key", "health call")
    }
}