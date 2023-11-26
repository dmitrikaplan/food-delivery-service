package com.example.authservice.service

import org.springframework.stereotype.Service

@Service
interface MailService {

    fun activateUserByEmail(emailTo: String, username: String, activationCode: String)

    fun recoveryPasswordByEmail(emailTo: String, username: String, activationCode: String)
}