package ru.skillbranch.kotlinexample

import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun anyTest(){
        val user = User("Roma", "Botan", "torex@inbox.ru")
        println(user.userInfo)
    }

    /**
    Добавьте метод в UserHolder для очистки значений UserHolder после выполнения каждого теста,
    это необходимо чтобы тесты можно было запускать одновременно

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder(){
    map.clear()
    }
     */
    @After
    fun after(){
        UserHolder.clearHolder()
    }


    @Test
    fun register_user_success() {
        val holder = UserHolder
        val user = UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: john_doe@unknown.com
            fullName: John Doe
            initials: J D
            email: John_Doe@unknown.com
            phone: null
            meta: {auth=password}
        """.trimIndent()

        assertEquals(expectedInfo, user.userInfo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_blank() {
        val holder = UserHolder
        UserHolder.registerUser("", "John_Doe@unknown.com", "testPass")
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_illegal_name() {
        val holder = UserHolder
        UserHolder.registerUser("John Jr Doe", "John_Doe@unknown.com", "testPass")
    }

    @Ignore
    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_illegal_exist() {
        val holder = UserHolder
        UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
//        UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
    }

//    @Test
//    fun register_user_by_phone_success() {
//        val holder = UserHolder
//        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971 11-11")
//        val expectedInfo = """
//            firstName: John
//            lastName: Doe
//            login: +79179711111
//            fullName: John Doe
//            initials: J D
//            email: null
//            phone: +79179711111
//            meta: {auth=sms}
//        """.trimIndent()
//
//        Assert.assertEquals(expectedInfo, user.userInfo)
//        Assert.assertNotNull(user.accessCode)
//        Assert.assertEquals(6, user.accessCode?.length)
//    }

//    @Test(expected = IllegalArgumentException::class)
//    fun register_user_by_phone_fail_blank() {
//        val holder = UserHolder
//        holder.registerUserByPhone("", "+7 (917) 971 11-11")
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun register_user_by_phone_fail_illegal_name() {
//        val holder = UserHolder
//        holder.registerUserByPhone("John Doe", "+7 (XXX) XX XX-XX")
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun register_user_failby_phone_illegal_exist() {
//        val holder = UserHolder
//        holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
//        holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
//    }
//
//    @Test
//    fun login_user_success() {
//        val holder = UserHolder
//        UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
//        val expectedInfo = """
//            firstName: John
//            lastName: Doe
//            login: john_doe@unknown.com
//            fullName: John Doe
//            initials: J D
//            email: John_Doe@unknown.com
//            phone: null
//            meta: {auth=password}
//        """.trimIndent()
//
//        val successResult = UserHolder.loginUser("john_doe@unknown.com", "testPass")
//
//        Assert.assertEquals(expectedInfo, successResult)
//    }
//
//    @Test
//    fun login_user_by_phone_success() {
//        val holder = UserHolder
//        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
//        val expectedInfo = """
//            firstName: John
//            lastName: Doe
//            login: +79179711111
//            fullName: John Doe
//            initials: J D
//            email: null
//            phone: +79179711111
//            meta: {auth=sms}
//        """.trimIndent()
//
//        val successResult = UserHolder.loginUser("+7 (917) 971-11-11", user.accessCode!!)
//
//        Assert.assertEquals(expectedInfo, successResult)
//    }
//
//    @Test
//    fun login_user_fail() {
//        val holder = UserHolder
//        UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
//
//        val failResult = UserHolder.loginUser("john_doe@unknown.com", "test")
//
//        Assert.assertNull(failResult)
//    }
//
//    @Test
//    fun login_user_not_found() {
//        val holder = UserHolder
//        UserHolder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
//
//        val failResult = UserHolder.loginUser("john_cena@unknown.com", "test")
//
//        Assert.assertNull(failResult)
//    }
//
//    @Test
//    fun request_access_code() {
//        val holder = UserHolder
//        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
//        val oldAccess = user.accessCode
//        holder.requestAccessCode("+7 (917) 971-11-11")
//
//        val expectedInfo = """
//            firstName: John
//            lastName: Doe
//            login: +79179711111
//            fullName: John Doe
//            initials: J D
//            email: null
//            phone: +79179711111
//            meta: {auth=sms}
//        """.trimIndent()
//
//        val successResult = UserHolder.loginUser("+7 (917) 971-11-11", user.accessCode!!)
//
//        Assert.assertNotEquals(oldAccess, user.accessCode!!)
//        Assert.assertEquals(expectedInfo, successResult)
//    }
//

}

