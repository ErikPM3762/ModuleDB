#include <jni.h>
#include <string>

#include <jni.h>
#include <string>

/**
 * Aqui almacenaremos los strings que se utilizan para el EnviromentManager
 */

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiDev(JNIEnv *env, jobject) {
    std::string url = "https://apidev.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiPre(JNIEnv *env, jobject) {
    std::string url = "https://apiqa.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiPro(JNIEnv *env, jobject) {
    std::string url = "https://api.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationDev(JNIEnv *env, jobject) {
    std::string tokenName = "Basic ZDVjM2VjMTJhZTMwNDg0MDhiN2E4OGY5NDRhMTg2Zjc6NGFhMTY3NjMtOTMwOC00NDE3LWJhYjYtOTcyNWFmOGJiNDgx";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationPre(JNIEnv *env, jobject) {
    std::string tokenName = "Basic NjhmMjMwOTdiOWZjNDJiNzgxZGQ2NDQ5OWE0YTYwOWM6NDY2MTkyMDEtMmY5Yi00NDRhLWEwM2EtOGUxZGNiMjI1MzBh";
    return env->NewStringUTF(tokenName.c_str());
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationPro(JNIEnv *env, jobject) {
    std::string tokenName = "Basic YTY4N2Q5NjMyNjdkNDMzYThlMjY5ZGMzMWVkYzgwOGU6YzMxZGMwMWEtY2M1NS00MDJkLWJiMjctYjMzNWEzNTAxY2Iz";
    return env->NewStringUTF(tokenName.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiDevAws(JNIEnv *env, jobject) {
    std::string url = "https://ap1.mgmt.q4.mobility-ado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiPreAws(JNIEnv *env, jobject) {
    std::string url = "https://ap1.mgmt.q4.mobility-ado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getUriApiProAws(JNIEnv *env, jobject) {
    std::string url = "https://auth-ap1.mgmt.q4.mobility-ado.com";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationDevAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic ZDVjM2VjMTJhZTMwNDg0MDhiN2E4OGY5NDRhMTg2Zjc6NGFhMTY3NjMtOTMwOC00NDE3LWJhYjYtOTcyNWFmOGJiNDgx";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationPreAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic NjhmMjMwOTdiOWZjNDJiNzgxZGQ2NDQ5OWE0YTYwOWM6NDY2MTkyMDEtMmY5Yi00NDRhLWEwM2EtOGUxZGNiMjI1MzBh";
    return env->NewStringUTF(tokenName.c_str());
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_services_utils_EnvironmentManager_getAutorizationProAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic YTY4N2Q5NjMyNjdkNDMzYThlMjY5ZGMzMWVkYzgwOGU6YzMxZGMwMWEtY2M1NS00MDJkLWJiMjctYjMzNWEzNTAxY2Iz";
    return env->NewStringUTF(tokenName.c_str());
}


/**
 * Eliminar posterior al update
 */

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiDev(JNIEnv *env, jobject) {
    std::string url = "https://apidev.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiPre(JNIEnv *env, jobject) {
    std::string url = "https://apiqa.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiPro(JNIEnv *env, jobject) {
    std::string url = "https://api.ecommerce.mobilityado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationDev(JNIEnv *env, jobject) {
    std::string tokenName = "Basic ZDVjM2VjMTJhZTMwNDg0MDhiN2E4OGY5NDRhMTg2Zjc6NGFhMTY3NjMtOTMwOC00NDE3LWJhYjYtOTcyNWFmOGJiNDgx";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationPre(JNIEnv *env, jobject) {
    std::string tokenName = "Basic NjhmMjMwOTdiOWZjNDJiNzgxZGQ2NDQ5OWE0YTYwOWM6NDY2MTkyMDEtMmY5Yi00NDRhLWEwM2EtOGUxZGNiMjI1MzBh";
    return env->NewStringUTF(tokenName.c_str());
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationPro(JNIEnv *env, jobject) {
    std::string tokenName = "Basic YTY4N2Q5NjMyNjdkNDMzYThlMjY5ZGMzMWVkYzgwOGU6YzMxZGMwMWEtY2M1NS00MDJkLWJiMjctYjMzNWEzNTAxY2Iz";
    return env->NewStringUTF(tokenName.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiDevAws(JNIEnv *env, jobject) {
    std::string url = "https://ap1.mgmt.q4.mobility-ado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiPreAws(JNIEnv *env, jobject) {
    std::string url = "https://ap1.mgmt.q4.mobility-ado.com/";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getUriApiProAws(JNIEnv *env, jobject) {
    std::string url = "https://auth-ap1.mgmt.q4.mobility-ado.com";
    return env->NewStringUTF(url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationDevAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic ZDVjM2VjMTJhZTMwNDg0MDhiN2E4OGY5NDRhMTg2Zjc6NGFhMTY3NjMtOTMwOC00NDE3LWJhYjYtOTcyNWFmOGJiNDgx";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationPreAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic NjhmMjMwOTdiOWZjNDJiNzgxZGQ2NDQ5OWE0YTYwOWM6NDY2MTkyMDEtMmY5Yi00NDRhLWEwM2EtOGUxZGNiMjI1MzBh";
    return env->NewStringUTF(tokenName.c_str());
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_utils_EnvironmentManager_getAutorizationProAws(JNIEnv *env, jobject) {
    std::string tokenName = "Basic YTY4N2Q5NjMyNjdkNDMzYThlMjY5ZGMzMWVkYzgwOGU6YzMxZGMwMWEtY2M1NS00MDJkLWJiMjctYjMzNWEzNTAxY2Iz";
    return env->NewStringUTF(tokenName.c_str());
}






