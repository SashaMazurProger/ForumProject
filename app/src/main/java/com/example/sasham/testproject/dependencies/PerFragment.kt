package com.example.sasham.testproject.dependencies


import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment
