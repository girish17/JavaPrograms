import java.lang.*
import java.util.*

/*This is a Java program to find out whether an integer is a palindrome or not*/
internal object IntegerPalindrome {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            var num = Integer.parseInt(args[0])
            var palNum = num
            val numOfDigits = numberOfDigits(num)
            var pow = 1;
            var isPalindrome = true
            for (i in 1..numOfDigits) {
                pow = power(10, numOfDigits - i)
                if (num / pow != palNum % 10) {
                    println("Not a palindrome")
                    isPalindrome = false
                    break
                }
                num = num % pow
                palNum = palNum / 10
            }
            if (isPalindrome)
                println("Palindrome")
        } catch (ex: Exception) {
            println("\nException: " + ex.toString())
        }

    }

    fun numberOfDigits(num: Int): Int {
        var num = num
        var numDigits = 1
        while (num / 10 != 0) {
            num = num / 10
            numDigits++
        }
        return numDigits
    }

    fun power(num: Int, exp: Int): Int {
        var num = num
        if (exp == 0)
            return 1

        for (i in 1 until exp)
            num = num * 10

        return num
    }
}
