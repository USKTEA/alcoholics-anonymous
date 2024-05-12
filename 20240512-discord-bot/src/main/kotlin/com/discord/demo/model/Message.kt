package com.discord.demo.model

abstract class Message {
    fun content(): String = "@everyone\n" +
            "안녕하세요 여러분\n" +
            "\n" +
            dayOfWeek() + " 모각코가 8시에  곧 시작합니다!\n" +
            "\n" +
            "퇴근후 모각코, 메가그라운드로 바로 입장: \n" +
            "https://zep.us/play/8LK5ed"

    abstract fun dayOfWeek(): String
}

class MondayMessage : Message() {
    override fun dayOfWeek(): String {
        return "월요일"
    }
}

class TuesdayMessage : Message() {
    override fun dayOfWeek(): String {
        return "화요일"
    }
}

class WednesdayMessage : Message() {
    override fun dayOfWeek(): String {
        return "수요일"
    }
}

class ThursdayMessage : Message() {
    override fun dayOfWeek(): String {
        return "목요일"
    }
}

class FridayMessage : Message() {
    override fun dayOfWeek(): String {
        return "금요일"
    }
}
