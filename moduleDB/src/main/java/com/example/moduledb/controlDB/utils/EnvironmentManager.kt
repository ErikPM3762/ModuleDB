package com.example.moduledb.controlDB.utils



object EnvironmentManager {

    private val environmentSelected = Environment.QA

    val uriApiOracle: String = when (environmentSelected) {
        Environment.DEV -> getUriApiDev()
        Environment.QA -> getUriApiPre()
        Environment.PROD -> getUriApiPro()
        else -> {getUriApiDev()}
    }

    val uriApiAws: String = when (environmentSelected) {
        Environment.DEV -> getUriApiDevAws()
        Environment.QA -> getUriApiPreAws()
        Environment.PROD -> getUriApiProAws()
        else -> {getUriApiDevAws()}
    }

    val uriApiHardcode: String = getUriApiProAws()


    val authorizationOracle: String = when (environmentSelected) {
        Environment.DEV -> getAutorizationDev()
        Environment.QA -> getAutorizationPre()
        Environment.PROD -> getAutorizationPro()
        else -> {getAutorizationDev()}
    }

    val authorizationAws: String = when (environmentSelected) {
        Environment.DEV -> getAutorizationDevAws()
        Environment.QA -> getAutorizationPreAws()
        Environment.PROD -> getAutorizationProAws()
        else -> {getAutorizationDev()}
    }




    /**
     * Funciones externas en archivo C++ para obtener las URL base correspondientes
     * para la consulta de servicios
     */
    private external fun getUriApiDev():String
    private external fun getUriApiPre():String
    private external fun getUriApiPro():String

    private external fun getUriApiDevAws():String
    private external fun getUriApiPreAws():String
    private external fun getUriApiProAws():String

    private external fun getAutorizationDev(): String
    private external fun getAutorizationPre(): String
    private external fun getAutorizationPro(): String

    private external fun getAutorizationDevAws(): String
    private external fun getAutorizationPreAws(): String
    private external fun getAutorizationProAws(): String
}

enum class Environment {
    QA,
    DEV,
    PROD
}
