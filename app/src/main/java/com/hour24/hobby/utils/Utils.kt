package com.hour24.hobby.utils

import org.apache.commons.lang3.RandomStringUtils


object Utils {

    fun getRandomId(): String = RandomStringUtils.random(15, true, true)

}