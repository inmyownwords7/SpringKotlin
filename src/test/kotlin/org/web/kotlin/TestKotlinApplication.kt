package org.web.kotlin

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<KotlinApplication>().with(TestcontainersConfiguration::class).run(*args)
}
