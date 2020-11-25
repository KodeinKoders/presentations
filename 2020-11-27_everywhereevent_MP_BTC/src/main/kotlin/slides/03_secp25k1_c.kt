package ws.slides

import kotlinx.css.*
import org.kodein.kpres.*
import styled.css
import styled.styledB
import styled.styledH2
import ws.charter.kodein
import ws.utils.medium
import ws.utils.titledSlide


fun PresentationBuilder.secp256k1_c() = slide(
        stateCount = 4,
        containerStyle = { justifyContent = JustifyContent.flexStart },
        inTransitions = Flip
) { props ->
    titledSlide("Secp256k1 C/Interop") {
        subSlide(0..0, props.state, Swipe) {
            sourceCode("kotlin",
                """
                class Secp256k1 {
                    fun pubKeyCreate(secKey: ByteArray): ByteArray
                }
            """.trimIndent()
            )
        }

        subSlide(1..1, props.state, Swipe) {
            sourceCode("kotlin",
                """
                    override fun pubkeyCreate(seckeyBytes: ByteArray): ByteArray {
                        memScoped {
                            seckeyBytes.usePinned { pinnedSeckeyBytes ->
                                val seckey = pinnedSeckeyBytes.addressOf(0)
                                val pubkey = alloc<secp256k1_pubkey>()
                                secp256k1_ec_pubkey_create(ctx, pubkey.ptr, seckey)
                                    .let { check(it == 1) }
                                
                                val serialized = allocArray<UByteVar>(65)
                                val outputLen = alloc<size_tVar>()
                                outputLen.value = 65.convert()
                                secp256k1_ec_pubkey_serialize(ctx, serialized, outputLen.ptr,
                                    pubkey.ptr, SECP256K1_EC_UNCOMPRESSED)
                                    .let { check(it == 1) }
                                return serialized.readBytes(outputLen.value.convert())
                            }
                        }
                    }
                """.trimIndent(),
                style = {
                    specific {
                        "code" {
                            fontSize = 0.55.em
                        }
                    }
                }
            )
        }

        subSlide(2..2, props.state, Swipe) {
            sourceCode("kotlin",
                """
                    JNIEXPORT jbyteArray JNICALL
                    Java_fr_acinq_secp256k1_Secp256k1CFunctions_secp256k1_1ec_1pubkey_1create
                      (JNIEnv *penv, jclass clazz, jlong jctx, jbyteArray jseckey)
                    {
                        secp256k1_context* ctx = (secp256k1_context *)jctx;
                        jbyte *seckey, *pubkey;
                        secp256k1_pubkey pub;
                        int result = 0;
                        size_t len = 65;
                        jbyteArray jpubkey = 0;
                    
                        seckey = (*penv)->GetByteArrayElements(penv, jseckey, 0);
                        jpubkey = (*penv)->NewByteArray(penv, 65);
                        pubkey = (*penv)->GetByteArrayElements(penv, jpubkey, 0);
                        result = secp256k1_ec_pubkey_create(ctx, &pub, (unsigned char*)seckey);
                        (*penv)->ReleaseByteArrayElements(penv, jseckey, seckey, 0);
                        (*penv)->ReleaseByteArrayElements(penv, jpubkey, pubkey, 0);
                        CHECKRESULT(!result, "secp256k1_ec_pubkey_create failed");
                        jpubkey = (*penv)->NewByteArray(penv, 65);
                        pubkey = (*penv)->GetByteArrayElements(penv, jpubkey, 0);
                        result = secp256k1_ec_pubkey_serialize(ctx,
                            (unsigned char*)pubkey,&len, &pub, SECP256K1_EC_UNCOMPRESSED);
                        (*penv)->ReleaseByteArrayElements(penv, jpubkey, pubkey, 0);
                        CHECKRESULT(!result, "secp256k1_ec_pubkey_serialize failed");
                        return jpubkey;
                    }
                """.trimIndent(),
                style = {
                    specific {
                        "code" {
                            fontSize = 0.42.em
                        }
                    }
                }
            )
        }

        subSlide(3..3, props.state, Swipe) {
            styledH2 {
                css {
                    color = Color.kodein.klycine
                    fontWeight = FontWeight.lighter
                }

                +"What about JNI "
                styledB {
                    css {
                        color = Color.kodein.korail
                        fontWeight = FontWeight.medium
                    }
                    +"extraction"
                }
                +" & "
                styledB {
                    css {
                        color = Color.kodein.korail
                        fontWeight = FontWeight.medium
                    }
                    +"distribution"
                }
                +"?"
            }
        }
    }
}
