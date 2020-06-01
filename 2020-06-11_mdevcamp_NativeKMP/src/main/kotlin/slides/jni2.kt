package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import styled.styledH1
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni2() = slide(stateCount = 12) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 2: "
        }
        s {
            +"C/C++ bridge"
        }
    }

    slideCode(props.state, "C++", """
        «ff:2-5«extern "C" JNIEXPORT jstring JNICALL
            «f:3«Java_org_example_nativeb64_cpp_NativeBase64Jvm_encode(»
                «f:4«JNIEnv *env, jobject», «f:5«jbyteArray jBytes, jboolean url»)» {
        «ff:6«    auto length = env->GetArrayLength(jBytes);
            int resultMaxLen = base64_max_encoded_len(length);
            auto resultChars = (char*) malloc(resultMaxLen * sizeof(char) + 1);»

        «ff:7-9«    auto bytes = (jbyte*) env->«f:8«GetPrimitiveArrayCritical»(jBytes, nullptr);

            int resultLen;
            «f:9«char* error = base64_encode((const char*)bytes, length, url,
                                        resultChars, resultMaxLen, &resultLen);»

            env->«f:8«ReleasePrimitiveArrayCritical»(jBytes, bytes, JNI_ABORT);»
        
        «ff:10«    if (error != nullptr) {
                env->ThrowNew(env->FindClass(EXCEPTION_CLASS), error);
                free(error);
                return nullptr;
            }»
        
        «ff:11«    resultChars[resultLen] = 0;
            jstring jResult = env->NewStringUTF(resultChars);
            free(resultChars);
        
            return jResult;»
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.6.em
        }
    }

}
