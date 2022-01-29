package ksp_compiler.generator


import appendText
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import ksp_compiler.generator.generateClassDeclarationDefinition
import ksp_compiler.generator.generateClassModule
import ksp_compiler.metadata.DIMetaData
import java.io.OutputStream

class DICodeGenerator(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) {

    fun generateModules(
        moduleMap: Map<String, DIMetaData.Container>,
        defaultModule: DIMetaData.Container
    ) {
        logger.warn("generate ${moduleMap.size} modules ...")
        moduleMap.values.forEachIndexed { index, module ->
            generateModule(module)
        }
    }

    private fun generateModule(module: DIMetaData.Container) {
        logger.warn("generate $module - ${module.type}")

            if (module.definitions.isNotEmpty()) {
                when (module.type) {
                    DIMetaData.ElementType.FIELD -> //add feild to class
                    DIMetaData.ElementType.CLASS -> {
                        val moduleFile = codeGenerator.getFile(fileName = "${module.name}Gen")
                        generateClassModule(moduleFile, module)
                    }
                }
            } else {

                if (module.name != "defaultModule") {
                    val moduleFile = codeGenerator.getFile(fileName = "${module.name}Gen")
                    generateClassModule(moduleFile, module)
                }
                logger.warn("no definition for $module")
            }
    }


    private fun OutputStream.generateFieldModule(module: DIMetaData.Container) {
        module.definitions.filterIsInstance<DIMetaData.Definition.ClassDeclarationDefinition>().forEach { def ->
            logger.warn("generate $def")
            generateClassDeclarationDefinition(def)
        }
    }
}

fun CodeGenerator.getFile(packageName: String = "com.azharkova.kmm_di.ksp.generated", fileName: String) = createNewFile(
    Dependencies.ALL_FILES,
    packageName,
    fileName)


