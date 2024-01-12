#include <jni.h>
#include <string>

#include <jni.h>
#include <string>

/**
 * Aqui almacenaremos los strings que se utilizan para el BaseInterceptor
 */

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getUriApiDev(JNIEnv *env, jobject) {
    std::string tokenName = "https://apidev.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getUriApiPre(JNIEnv *env, jobject) {
    std::string tokenName = "https://apiqa.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getUriApiPro(JNIEnv *env, jobject) {
    std::string tokenName = "https://api.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getAutorizationDev(JNIEnv *env, jobject) {
    std::string tokenName = "Basic ZDVjM2VjMTJhZTMwNDg0MDhiN2E4OGY5NDRhMTg2Zjc6NGFhMTY3NjMtOTMwOC00NDE3LWJhYjYtOTcyNWFmOGJiNDgx";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getAutorizationPre(JNIEnv *env, jobject) {
    std::string tokenName = "Basic NjhmMjMwOTdiOWZjNDJiNzgxZGQ2NDQ5OWE0YTYwOWM6NDY2MTkyMDEtMmY5Yi00NDRhLWEwM2EtOGUxZGNiMjI1MzBh";
    return env->NewStringUTF(tokenName.c_str());
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_data_remote_server_BaseInterceptor_getAutorizationPro(JNIEnv *env, jobject) {
    std::string tokenName = "Basic YTY4N2Q5NjMyNjdkNDMzYThlMjY5ZGMzMWVkYzgwOGU6YzMxZGMwMWEtY2M1NS00MDJkLWJiMjctYjMzNWEzNTAxY2Iz";
    return env->NewStringUTF(tokenName.c_str());
}

/**
 * Aqui almacenaremos los strings que se utilizan para el ModuleInject
 */



extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_modules_ModuleInject_getUriApiDev(JNIEnv *env, jobject) {
    std::string tokenName = "https://apidev.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_modules_ModuleInject_getUriApiPre(JNIEnv *env, jobject) {
    std::string tokenName = "https://apiqa.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_moduledb_controlDB_modules_ModuleInject_getUriApiPro(JNIEnv *env, jobject) {
    std::string tokenName = "https://api.ecommerce.mobilityado.com/";
    return env->NewStringUTF(tokenName.c_str());
}

