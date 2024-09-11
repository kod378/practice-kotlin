package org.delivery.common.log

inline fun <reified T> T.logger() = org.slf4j.LoggerFactory.getLogger(T::class.java)!!