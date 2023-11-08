#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nkdkey_Native_getMapsKey(JNIEnv *env, jobject /* this */) {
    std::string mapsKey = "Key for maps";
    return env->NewStringUTF(mapsKey.c_str());
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_example_nkdkey_Native_calculateSquare(JNIEnv *env, jobject obj, jlong width, jlong height) {
    // Create a Rectangle object and call the calculateSquare method
    jclass rectangleClass = env->FindClass("com/example/nkdkey/Rectangle");
    jmethodID constructor = env->GetMethodID(rectangleClass, "<init>", "(JJ)V");
    jobject rectangleObj = env->NewObject(rectangleClass, constructor, width, height);

    jmethodID calculateSquareMethod = env->GetMethodID(rectangleClass, "calculateSquare", "()J");
    jlong result = env->CallLongMethod(rectangleObj, calculateSquareMethod);

    return result;
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nkdkey_Native_getEncryptedMapsKey(JNIEnv *env, jobject /* this */) {
    std::string mapsKey = "zbh66z6d5TcoYdojnWKeDfhAWD+BFo4ypPI03l3RDozNJJaw11zCWw==";
    return env->NewStringUTF(mapsKey.c_str());
}