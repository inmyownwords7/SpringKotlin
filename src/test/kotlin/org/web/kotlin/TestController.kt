package org.web.kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/public/hello")
    fun publicEndpoint(): String {
        return "This is a public endpoint!"
    }

    @GetMapping("/private/hello")
    fun privateEndpoint(): String {
        return "This is a private endpoint, only accessible to authenticated users!"
    }
}
