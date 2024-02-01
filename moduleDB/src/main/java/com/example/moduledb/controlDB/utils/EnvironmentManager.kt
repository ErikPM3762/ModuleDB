package com.example.moduledb.controlDB.utils



object EnvironmentManager {

    private val environmentSelected = Environment.QA_ORACLE

    val uriApi: String = when (environmentSelected) {
        Environment.DEV_ORACLE -> getUriApiDev()
        Environment.DEV_AWS -> getUriApiDevAws()

        Environment.QA_ORACLE -> getUriApiPre()
        Environment.QA_AWS -> getUriApiPreAws()

        Environment.PROD_ORACLE -> getUriApiPro()
        Environment.PROD_AWS -> getUriApiProAws()
    }

    val authorization: String = when (environmentSelected) {
        Environment.DEV_ORACLE -> getAutorizationDev()
        Environment.DEV_AWS -> getAutorizationDevAws()

        Environment.QA_ORACLE -> getAutorizationPre()
        Environment.QA_AWS -> getAutorizationPreAws()

        Environment.PROD_ORACLE -> getAutorizationPro()
        Environment.PROD_AWS -> getAutorizationProAws()
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
    DEV_AWS,
    QA_AWS,
    PROD_AWS,
    DEV_ORACLE,
    QA_ORACLE,
    PROD_ORACLE
}
