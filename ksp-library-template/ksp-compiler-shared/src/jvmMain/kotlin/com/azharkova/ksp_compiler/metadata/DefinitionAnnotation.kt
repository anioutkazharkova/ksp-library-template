package ksp_compiler.metadata
import com.azharkova.ksp_annotation.Test
import com.azharkova.ksp_annotation.*
import kotlin.reflect.KClass

val DEFINITION_ANNOTATION_LIST = listOf<KClass<*>>(
    Test::class

)

enum class DefinitionAnnotation {
    Test
    ;

    companion object {
        private val allValues : List<String> = values().map { it.toString() }
        fun isValidAnnotation(s : String) : Boolean = s in allValues
    }
}
