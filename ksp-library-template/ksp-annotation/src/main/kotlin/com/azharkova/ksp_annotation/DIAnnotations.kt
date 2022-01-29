package com.azharkova.ksp_annotation

import kotlin.reflect.KClass



@Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION)
public annotation class Test(val binds: Array<KClass<*>> = [])

@Target(AnnotationTarget.CLASS)
public annotation class Container()
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
public annotation class ComponentScan(val value: String = "")