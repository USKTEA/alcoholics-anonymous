package com.quizbot.demo.controller

import org.springframework.ai.chat.ChatClient
import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val chatClient: ChatClient
) {

    @GetMapping("/")
    fun sample(): String {
        val response = chatClient.call(
            "백엔드 개발자 신입 개발면접 기술 질문 문제 하나 알려줘, 질문 형식은 아래와 같이. 그리고 해당 문제 키워드도 알려줘\n" +
                    "Quiz: {예상질문} \n"
        )

        return response
    }
}
