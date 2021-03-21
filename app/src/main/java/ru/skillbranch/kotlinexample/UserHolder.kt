package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting

object UserHolder {

    private val map = mutableMapOf<String, User>()
    private val phoneFormat = Regex("""^[+][\d]{11}""")

    fun registerUser(fullName: String, email: String, password: String):
            User = User.makeUser(fullName, email = email, password = password)
        .also { user -> if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists") }
        .also { user -> map[user.login] = user }

    fun loginUser(login: String, password: String): String? {
        return map[login.formatLogin()]?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    fun registerUserByPhone(fullName: String, rawPhone: String): User {
        return User.makeUser(fullName, phone = rawPhone).also { user ->
            when {
                map.containsKey(user.login) -> throw IllegalArgumentException("A user with this phone already exists")
                !user.phone!!.matches(phoneFormat) -> throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
                else -> map[user.login] = user
            }
        }
    }

//    fun registerUserByPhone(fullName: String, rawPhone: String):
//            User = User.makeUser(fullName, phone = rawPhone)
////        .also { user -> if (map.containsKey(user.login)) throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits") }
//        .also { user -> if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this phone already exis]ts") }
//        .also { user -> map[user.login] = user }

    fun requestAccessCode(login: String) {
        map[login.formatLogin()]?.updateAccessCode()
    }

    private fun User.updateAccessCode() {
        accessCode = generateAccessCode()
    }

    private fun String.formatLogin(): String {
        return if (first() == '+') replace(Regex("[^+\\d]"), "").trim()
        else this
    }

}

