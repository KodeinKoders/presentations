package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val d1_whatIsReflection = listOf(
    Slide(
        name = "what-is-reflection"
    ) {
        H4 {
            Text("""
                Reflection is a feature [that] allows an executing Java program to examine or "introspect" upon itself,
                and manipulate internal properties of the program.
            """)
            Br()
            Br()
            Text("""
                For example, it's possible for a Java class to obtain the names of all its members.
            """)
        }
    },
    Slide(
        name = "reflection-members",
        stateCount = 3
    ) { state ->
        SourceCode("kotlin", """
            fun printMembers(«r:cls: Class<*>») {
              «r:cls.fields».forEach { println("field:${'$'}it") }
              «r:cls.methods».forEach { println("method:${'$'}it") }
            }
            
            fun main() {
              printMembers(String::class.java)
            }
        """.trimIndent()) {
            "r" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "reflection-proxy",
        stateCount = 4
    ) { state ->
        SourceCode("kotlin", """
            @Suppress("UNCHECKED_CAST")
            fun <T> createProxy(cls: Class<T>): T =
            «r:  Proxy.newProxyInstance(
                cls.classLoader, arrayOf(cls),
                { _, m, a ->
                  println(
                    "Calling ${'$'}{m.name} " +
                    "with ${'$'}{a.joinToString()}"
                  )
                }) as T
            »
            
            «p:interface Foo { fun bar(i: Int, c: Char) }
            fun main() {
              val proxy = createProxy(Foo::class.java)
              proxy.bar(42, 'S') // bar with 42, S
            }
            »
        """.trimIndent()) {
            "r" { zoomed(state == 1) }
            "p" { zoomed(state == 2) }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
