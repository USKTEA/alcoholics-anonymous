package com.example.demo.clients

import com.example.demo.properties.FirebaseProperties
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import jakarta.annotation.PostConstruct
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.context.annotation.Bean

@Component
class FirebaseClient(
    private val firebaseProperties: FirebaseProperties
) {

    @PostConstruct
    fun init() {
        val serviceAccount = ClassPathResource("${firebaseProperties.account}").inputStream
        val options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build()
        FirebaseApp.initializeApp(options)
    }

    @Bean
    fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}