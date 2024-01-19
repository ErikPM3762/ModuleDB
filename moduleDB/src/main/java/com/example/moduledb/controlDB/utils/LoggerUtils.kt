package com.example.moduledb.controlDB.utils

import java.util.logging.Logger

object LoggerUtils {
    private val logger = Logger.getLogger(LoggerUtils::class.java.name)

    fun logInfo(message: String) {
        logger.info(message)
    }
}