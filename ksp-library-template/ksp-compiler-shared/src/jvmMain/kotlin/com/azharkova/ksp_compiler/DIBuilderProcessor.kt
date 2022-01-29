

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import ksp_compiler.generator.DICodeGenerator
import ksp_compiler.metadata.DIMetaData
import ksp_compiler.metadata.DIMetaDataScanner

class DIBuilderProcessor(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) : SymbolProcessor {

    val diCodeGenerator = DICodeGenerator(codeGenerator, logger)
    val metaDataScanner = DIMetaDataScanner(logger)


    override fun process(resolver: Resolver): List<KSAnnotated> {
        val defaultModule = DIMetaData.Container(
            packageName = "",
            name = "defaultModule"
        )
        logger.warn("Scan metadata ...")
        val (moduleMap, definitions) = metaDataScanner.scanAllMetaData(resolver, defaultModule)
        logger.warn("Code generation ...")
        if (moduleMap.isNotEmpty()) {
            logger.warn("Generate from modules metadata ...")
            diCodeGenerator.generateModules(moduleMap, defaultModule)
        } else {
            logger.warn("Generate nothing ...")
        }
        return emptyList()
    }
}

class DIBuilderProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment
    ): SymbolProcessor {
        return DIBuilderProcessor(environment.codeGenerator, environment.logger)
    }
}