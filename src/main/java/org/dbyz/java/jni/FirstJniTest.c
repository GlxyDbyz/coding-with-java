#include <stdio.h>
#include "main.h"
JNIEXPORT jstring JNICALL
 /*此处方法名以_开头*/
 _Java_org_dbyz_java_jni_FirstJniTest_hello(JNIEnv *env, jobject objec){
      return (*env)->NewStringUTF(env,"hello I am a String from C");
  }
