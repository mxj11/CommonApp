//
// Created by lxz on 2017/9/28 0028.
//
#include <stdlib.h>
#include <jni.h>
#include <string.h>
#include <android/log.h>
#define  LOG_TAG    "myNdk"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
//非静态native方法：jobject 代表native方法所属的对象
JNIEXPORT jstring JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_helloFromC(JNIEnv *env, jobject jobj){

    char* text = "我是来自C的非静态native方法";
    return (*env)->NewStringUTF(env,text);

}
//静态native方法：jclass 代表native方法所属类的class对象(JNI.classes)
JNIEXPORT jstring JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_helloFromC1 (JNIEnv *env, jclass jcls){
    char* text = "我是来自C的静态native方法";
    return (*env)->NewStringUTF(env,text);
}
//基本数据
//Java基本数据类型与JNI数据类型的映射关系
//Java类型->JNI类型->C类型
/*
boolean jboolean
byte jbyte;
char jchar;
short jshort;
int jint;
long jlong;
float jfloat;
double jdouble;
void void
*/

//引用类型(对象)
//String jstring
//object jobject
//数组,基本数据类型的数组
//byte[] jByteArray
//对象数组
//object[](String[]) jobjectArray

//C/C++访问Java的成员

//1.C访问java属性
//修改非静态属性key并修改属性值
JNIEXPORT jstring JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_accessField(JNIEnv *env, jobject jobj){
    jclass cls = (*env)->GetObjectClass(env,jobj);
    //属性名称key，属性签名Ljava/lang/String;
    jfieldID fid = (*env)->GetFieldID(env,cls,"key","Ljava/lang/String;");
    jstring  jstr = (*env)->GetObjectField(env, jobj, fid);
    //将jsrt转为c字符串
    char* c_str = (*env)->GetStringUTFChars(env,jstr,JNI_FALSE);
    //拼接得到新的字符串
    char text[5000] = "super ";
    strcat(text,c_str);
    //将c字符串转为jstring字符串
    jstring new_str = (*env)->NewStringUTF(env,text);
    //修改key
    (*env)->SetObjectField(env,jobj,fid,new_str);
    return new_str;
}

//访问静态属性
JNIEXPORT jint JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_accessStaticField (JNIEnv *env, jobject jobj){
    jclass jcls = (*env)->GetObjectClass(env, jobj);
    jfieldID fid = (*env)->GetStaticFieldID(env,jcls,"count","I");
    jint j_count = (*env)->GetStaticIntField(env,jcls,fid);
    j_count++;
    (*env)->SetStaticIntField(env,jcls,fid,j_count);
    return j_count;
}

//2.C访问java方法
//非静态方法
JNIEXPORT jint JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_accessMethod (JNIEnv *env, jobject jobj){
    jclass jcls = (*env)->GetObjectClass(env,jobj);
    jmethodID mid = (*env)->GetMethodID(env,jcls,"genRandomInt","(I)I");
    jint radom = (*env)->CallIntMethod(env,jobj,mid,200);
    return radom;
}

//静态方法
JNIEXPORT jstring JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_accessStaticMethod (JNIEnv *env, jobject jobj){
    jclass jcls = (*env)->GetObjectClass(env,jobj);
    jmethodID mid = (*env)->GetStaticMethodID(env,jcls,"getUUID","()Ljava/lang/String;");
    jstring uuid = (*env)->CallStaticObjectMethod(env,jcls,mid);
    char* c_uuid = (*env)->GetStringUTFChars(env,uuid,JNI_FALSE);
    char text[5000] = "pingjie ";
    strcat(text,c_uuid);
    jstring new_uuid = (*env)->NewStringUTF(env,text);
    return new_uuid;
}
//3、访问构造方法
//
//获取方法的签名：javap -s -p java.util.Date
JNIEXPORT jobject JNICALL Java_com_lxz_common_second_ndk_jninative_JNI_accessConstructor (JNIEnv *env, jobject jobj) {
    //获取Date类的jclass对象
    jclass cls = (*env)->FindClass(env,"java/util/Date");
    //获取Date对象构造方法的methodId，访问构造方法必须加"<init>"，最后一个参数为构造方法的签名:""
    jmethodID con_mid = (*env)->GetMethodID(env,cls,"<init>","()V");
    //实例化一个Date对象
    jobject date_obj = (*env)->NewObject(env,cls,con_mid);
    //获取getTime()方法的methodId，
    jmethodID mid = (*env)->GetMethodID(env,cls,"getTime","()J");
    //调用Date对象的getTime()方法
    jlong time = (*env)->CallLongMethod(env,date_obj,mid);
    printf("time:%lld\n",time);
    return date_obj;
}

//c访问java父类方法
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_accessParentMethod(JNIEnv *env, jobject jobj) {
    //获取jclass对象
        jclass jcls = (*env)->GetObjectClass(env,jobj);
        //获取jfieldId
        jfieldID fid = (*env)->GetFieldID(env,jcls,"human","Lcom/lxz/common/second/ndk/test/Human;");
        //获取Human对象
        jobject human_obj = (*env)->GetObjectField(env,jobj,fid);
        //执行say方法
        //获取Human的jclass对象
        jclass human_cla = (*env)->FindClass(env,"com/lxz/common/second/ndk/test/Human");
        //获取say的jMethodId
        jmethodID say_mid = (*env)->GetMethodID(env,human_cla,"say","()V");

        //调用子类say方法
        //(*env)->CallVoidMethod(env,human_obj,say_mid);
        //调用父类say方法
        (*env)->CallNonvirtualVoidMethod(env,human_obj,human_cla,say_mid);
        LOGE("我是来自jni的日志打印");
    }
//java传递中文字符串给C乱码问题
JNIEXPORT jstring JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_chineseChar(JNIEnv *env, jobject jobj,jstring in) {
    char *c_para_str = (*env)->GetStringUTFChars(env,in,JNI_FALSE);
//    char new_str[5000] = "我是";
//    strcat(new_str,c_para_str);
    char *return_str;
    if(strcmp(c_para_str,"刘向昭") == 0){//0表示两个字符串一样
        return_str = "您输入正确的";
    }else{
        return_str = "您输入错误的";
    }
    jstring jstr = (*env)->NewStringUTF(env,return_str);
    return jstr;
}

int compare(int *a,int *b){
    return (*a)-(*b);
}

//获取java数组
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_giveArray(JNIEnv *env, jobject jobj,jintArray array) {
    //获取jint指针
    jint *elems = (*env)->GetIntArrayElements(env,array,NULL);
    //获取jint数组长度
    int len = (*env)->GetArrayLength(env,array);
    //排序
    qsort(elems,len, sizeof(jint),compare);
    //同步
    //0表示java数组进行更新，并且释放c/c++数组
    //JNI_ABORT表示java数组不进行更新，但是释放c/c++数组
    //JNI_COMMIT表示java数组进行更新，但是不释放c/c++数组(但是函数执行完后还是会释放)
    (*env)->ReleaseIntArrayElements(env,array,elems,0);

}

//获取java数组
JNIEXPORT jintArray JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_getArray(JNIEnv *env, jobject jobj,jint len) {
    jintArray arr = (*env)->NewIntArray(env,len);
    jint *elems = (*env)->GetIntArrayElements(env,arr,NULL);
    int i =0;
    for(;i<len;i++){
        elems[i] = i;
    }
    //同步
    (*env)->ReleaseIntArrayElements(env,arr,elems,0);
    return arr;
}

//手动释放局部变量
//1、访问一个很大的java对象，使用完后还要进行复杂的耗时操作
//2、创建了大量的局部引用，占用太多内存，而这些局部引用跟后面操作没什么关联性

JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_localRef(JNIEnv *env, jobject jobj) {
    int i = 0;
    for(;i<5;i++){
        jclass  jcs = (*env)->FindClass(env,"java/util/Date");
        jmethodID con_mid = (*env)->GetMethodID(env,jcs,"<init>","()V");
        jobject obj = (*env)->NewObject(env,jcs,con_mid);
        //......

        //释放
        (*env)->DeleteLocalRef(env,obj);
        LOGE("释放引用了");
        //......
    }
}


//全局引用
jstring global_str;
//
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_createGlobalRef(JNIEnv *env, jobject jobj) {
    jobject obj = (*env)->NewStringUTF(env,"This is Global Ref");
    global_str = (*env)->NewGlobalRef(env,obj);
    LOGE("创建全局引用");
}

JNIEXPORT jstring JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_getGlobalRef(JNIEnv *env, jobject jobj) {
    LOGE("获取全局引用");
    return global_str;
}

JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_deleteGlobalRef(JNIEnv *env, jobject jobj) {
    (*env)->DeleteGlobalRef(env,global_str);
    LOGE("释放全局引用");
}

//弱全局引用
//节省内存，在内存不足时可以释放所引用的对象
//可以引用一个不常用的对象，如果为NULL，临时创建
//创建:NewWeakGlobalRef;销毁：DeleteGlobalWeakRef

//异常处理
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_exception(JNIEnv *env, jobject jobj) {
    jclass cls = (*env)->GetObjectClass(env,jobj);
    jfieldID fid = (*env)->GetFieldID(env,cls,"key2","Ljava/lang/String;");
    //检测，在这个位置可能发生异常
    jthrowable exception = (*env)->ExceptionOccurred(env);
    if(exception != NULL){
        //让java代码继续运行
        //清空异常信息
        (*env)->ExceptionClear(env);
        //补救措施
        fid = (*env)->GetFieldID(env,cls,"key","Ljava/lang/String;");
    }
    //获取属性的值
    jstring jstr = (*env)->GetObjectField(env,jobj,fid);
    char *str = (*env)->GetStringUTFChars(env,jstr,NULL);

    //对比属性合法值
    if(strcmp(str,"HelloWorld11")!= 0){
        //人为抛出异常，给java层处理
        jclass newExcCls = (*env)->FindClass(env,"java/lang/IllegalArgumentException");
        (*env)->ThrowNew(env,newExcCls,"The key is invalid");
    }
}

//缓存策略之局部静态变量
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_cached(JNIEnv *env, jobject jobj) {
    jclass cls = (*env)->GetObjectClass(env,jobj);
    //局部静态变量，只能在局部引用，不能全局引用，但是生命周期是整个程序的生命周期
    static jfieldID keyid;
    if(keyid == NULL){
        keyid = (*env)->GetFieldID(env,cls,"key","Ljava/lang/String;");
        LOGE("获取jfieldId");
    }
}

//缓存策略之初始化全局变量：动态库加载完成之后，立刻缓存起来
jfieldID key_fid;
jmethodID random_mid;
JNIEXPORT void JNICALL
Java_com_lxz_common_second_ndk_jninative_JNI_initIds(JNIEnv *env, jclass cls) {
    key_fid = (*env)->GetFieldID(env,cls,"key","Ljava/lang/String;");
    random_mid = (*env)->GetMethodID(env,cls,"genRandomInt","(I)I");
    LOGE("初始化全局变量");
}









