package org.apple.api.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Service
annotation class Business(
    @get:AliasFor(annotation = Service::class)
    val value: String = ""
)
