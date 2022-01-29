package ksp_compiler.generator

import appendText
import com.google.devtools.ksp.symbol.KSDeclaration
import ksp_compiler.metadata.DIMetaData
import java.io.OutputStream
import java.util.*



fun OutputStream.generateClassDeclarationDefinition(def: DIMetaData.Definition.ClassDeclarationDefinition) {
    val param =
        if (def.constructorParameters.filter { it.type == DIMetaData.ConstructorParameterType.PARAMETER_INJECT }
                .isEmpty()) "" else " params ->"
    val ctor =
        if (def.constructorParameters.isEmpty()) "" else generateConstructor(def.constructorParameters)
    //TODO: add text
}

fun generateClassModule(classFile: OutputStream, module: DIMetaData.Container) {

    val generatedField = "${module.name}ConfigContainer"
    val classModule = "${module.packageName}.${module.name}"
    //TODO: add some header

    module.definitions.filterIsInstance<DIMetaData.Definition.ClassDeclarationDefinition>().forEach { def ->
        classFile.generateClassDeclarationDefinition(def)
    }
    //TODO: add some end
    classFile.flush()
    classFile.close()
}

fun generateConstructor(constructorParameters: List<DIMetaData.ConstructorParameter>): String {
    return constructorParameters.joinToString(prefix = "", separator = ",", postfix = "") {
        if (it.type == DIMetaData.ConstructorParameterType.DEPENDENCY) "resolve(${it.name}::class) as? ${it.name}" else "params.get()"
    }
}