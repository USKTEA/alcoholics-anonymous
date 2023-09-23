package com.example.demo.controller

import com.example.demo.services.GetCommendService
import com.example.demo.services.AddMinusService
import com.example.demo.services.AddPlusService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

private const val SIMPLE = "simple"
private const val NOT_SIMPLE = "not simple"

@WebMvcTest(CommandController::class)
class CommandControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpyBean
    private lateinit var addMinusService: AddMinusService

    @SpyBean
    private lateinit var addPlusService: AddPlusService

    @SpyBean
    private lateinit var getCommendService: GetCommendService

    @Test
    fun simpleCommands() {
        mockMvc.get("/commands?command=$SIMPLE") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }.andExpect {
            content {
                string("simple")
            }
        }
    }

//    @Test
//    fun notSimpleCommands() {
//        mockMvc.get("/commands?command=$NOT_SIMPLE") {
//            contentType = MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk() }
//        }.andExpect {
//            content {
//                string("not simple+-")
//            }
//        }
//    }
}

