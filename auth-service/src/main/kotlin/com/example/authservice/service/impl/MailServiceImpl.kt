package com.example.authservice.service.impl

import com.example.authservice.domain.email.KindsOfEmailMessages
import com.example.authservice.domain.email.KindsOfSubjects
import com.example.authservice.service.MailService
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.nio.charset.StandardCharsets

@Service
class MailServiceImpl(
    private val mailSender: JavaMailSender,
    private val springTemplateEngine: SpringTemplateEngine
) : MailService {

    @Value("\${service.host}")
    private lateinit var host: String

    @Value("\${spring.mail.username}")
    private lateinit var from: String

    override fun activateUserByEmail(emailTo: String, username: String, activationCode: String) {
        val templateLocation = KindsOfEmailMessages.REGISTRATION_EMAIL.pathOfTemplate
        val subject = KindsOfSubjects.SUBJECT_FOR_REGISTRATION.subject
        val endpoint = "activate"
        val context = Context()
        context.setVariable("username", username)
        context.setVariable("activationLink", generateActivationLink(activationCode, host, endpoint))
        context.setVariable("subject", subject)
        sendEmail(emailTo, username, context, subject, templateLocation)
    }

    override fun recoveryPasswordByEmail(emailTo: String, username: String, activationCode: String) {
        val templateLocation = KindsOfEmailMessages.RECOVERY_EMAIL.pathOfTemplate
        val subject = KindsOfSubjects.SUBJECT_FOR_PASSWORD_RECOVERY.subject
        val endpoint = "recovery"
        val context = Context()
        context.setVariable("username", username)
        context.setVariable("activationLink", generateActivationLink(activationCode, host, endpoint))
        context.setVariable("subject", subject)
        sendEmail(emailTo, username, context, subject, templateLocation)
    }

    private fun sendEmail(emailTo: String, username: String, context: Context, subject: String, templateLocation: String) {
        val mailMessage = mailSender.createMimeMessage()
        val mimeMessageHelper = MimeMessageHelper(
            mailMessage,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name()
        )
        val emailContent = springTemplateEngine.process(templateLocation, context)
        mimeMessageHelper.setFrom(from)
        mimeMessageHelper.setSubject(subject)
        mimeMessageHelper.setTo(emailTo)
        mimeMessageHelper.setText(emailContent, true)
        mailSender.send(mailMessage)
    }

    private fun generateActivationLink(activationCode: String, host: String?, endpoint: String): String {
        return String.format("%s/%s/%s", host, endpoint, activationCode)
    }
}
