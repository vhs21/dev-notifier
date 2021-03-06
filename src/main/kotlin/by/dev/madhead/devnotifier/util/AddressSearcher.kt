package by.dev.madhead.devnotifier.util

import hudson.model.Cause
import hudson.model.Run
import hudson.model.User
import hudson.tasks.Mailer

fun findJenkinsUserAddressById(userId: String?) =
        if (userId != null) User.get(userId).getProperty(Mailer.UserProperty::class.java).address
        else null

fun findJenkinsBuilderAddress(run: Run<*, *>) =
        findJenkinsUserAddressById(run.getCause(Cause.UserIdCause::class.java)?.userId)

