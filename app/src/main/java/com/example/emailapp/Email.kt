package com.example.emailapp

class Email {
    var from:String
    var subjectLine:String
    var emailText:String
    var timeReceived:String
    var isImportant:Boolean = false

    constructor(
        from: String,
        subjectLine: String,
        emailText: String,
        timeReceived: String
    ) {
        this.from = from
        this.subjectLine = subjectLine
        this.emailText = emailText
        this.timeReceived = timeReceived
    }

    override fun toString(): String {
        return "Email(from='$from', subjectLine='$subjectLine', emailText='$emailText', timeReceived='$timeReceived', isImportant=$isImportant)"
    }
}
