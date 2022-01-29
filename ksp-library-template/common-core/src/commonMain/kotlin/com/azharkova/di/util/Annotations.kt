package com.azharkova.ksp_annotation

import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION)
annotation class Test(val binds: Array<KClass<*>> = [], val createdAtStart: Boolean = false)

@Target(AnnotationTarget.CLASS)
annotation class Container()
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
annotation class ComponentScan(val value: String = "")