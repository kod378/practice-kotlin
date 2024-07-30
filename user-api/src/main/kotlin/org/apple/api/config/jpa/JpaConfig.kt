package org.apple.api.config.jpa

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["org.apple.db"])
@EnableJpaRepositories(basePackages = ["org.apple.db"])
class JpaConfig {
}