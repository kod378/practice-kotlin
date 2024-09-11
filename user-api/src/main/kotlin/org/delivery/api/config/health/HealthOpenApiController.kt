package org.delivery.api.config.health

import org.delivery.common.log.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api")
class HealthOpenApiController {

    val log = logger()

    @GetMapping("/health")
    fun health() {
        log.info("health call")
    }
}