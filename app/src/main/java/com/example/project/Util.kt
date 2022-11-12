package com.example.project

import kotlin.math.*

 class Util{

    /*
fun main(args: Array<String>) {

    //step1 complete
    */
/*    val c = borrowZero("10101010", "11001100")
        val a = c[0]
        val b = c[1]
        println("$a  $b")
        println("SUM:  " + binaryAdd(a, b))*//*

//    println(binaryToDecimal("1100"))
//    decimalToBinary(293)
//    println(binaryToHexa("11001000000"))
//    println(binaryToOcta("11001000000"))
//    println(binarySub("10101010","11001100"))
//    println(binaryMul("10101010", "11001100"))
 */
/*   val temp = binaryDiv("1010101001011", "10101010")
    println("${temp[0]} Reminder : ${temp[1]}")*//*

//    println(binaryToGray("01101"))
//    println(hexaToBinary("A2B"))
//   println(octalToBinary("205"))

}
*/

    //Binary to Decimal
    fun binaryToDecimal(s: String): Double {
        //11001100

        return binaryRecursion(s.length, s)
    }

    //Decimal to Binary
    fun decimalToBinary(no: Int): String {
        //100
        return decimalRecursion(no)

    }

    //Binary to Hexa
    fun binaryToHexa(s: String): String {

        /* val no = binaryToDecimal(s)
         val temp = no / 16
         val split = temp.toString().split(".")
         return split[0] + hexTable((split[1].toDouble() * 0.016).toInt())*/

        val split = s.reversed()
        val no = ArrayList<String>()
        var index = 0
        while (split.length - 4 >= index) {
            no.add(split.substring(index, min(index + 4, split.length)))
            index += 4
        }
        no.add(split.substring(index))

        var temp: String = ""
        repeat(no.size) {
            val temp1 = numbetToHexa(binaryToDecimal(no[it].reversed()).toInt())
            temp = temp1 + temp
        }
        return temp
    }

    //Hexa to Binary
    fun hexaToBinary(s:String):String{

        return decimalToBinary(hexaRecursion(s.length,s).toInt())
    }

    fun hexaRecursion(count:Int,s: String): Double {
        return if (count == 0) {
            0.0
//        (no[no.length - 1].toString().toDouble())
        } else {
            val power = (16.0.pow(count.toDouble() - 1)) * hexaToNumber(s[s.length - count].toString())

            (power + hexaRecursion(count - 1, s))
        }
    }

    //Binary to Octa
    fun binaryToOcta(s: String): String {

        /* val no = binaryToDecimal(s)
         val temp = no / 16
         val split = temp.toString().split(".")
         return split[0] + hexTable((split[1].toDouble() * 0.016).toInt())*/

        val split = s.reversed()
        val no = ArrayList<String>()
        var index = 0
        while (split.length - 3 >= index) {
            no.add(split.substring(index, min(index + 3, split.length)))
            index += 3
        }
        no.add(split.substring(index))

        var temp: String = ""
        repeat(no.size) {
            val temp1 = numbetToHexa(binaryToDecimal(no[it].reversed()).toInt())
            temp = temp1 + temp
        }
        return temp
    }

    //Octal to Binary
    fun octalToBinary(s:String):String{

        return decimalToBinary(octalRecursion(s.length,s).toInt())
    }

    fun octalRecursion(count:Int,s: String): Double {
        return if (count == 0) {
            0.0
//        (no[no.length - 1].toString().toDouble())
        } else {
            val power = (8.0.pow(count.toDouble() - 1)) * s[s.length - count].toString().toInt()

            (power + octalRecursion(count - 1, s))
        }
    }
    //Common
    fun numbetToHexa(no: Int): String {
        return when (no) {
            10 -> "A"
            11 -> "B"
            12 -> "C"
            13 -> "D"
            14 -> "E"
            15 -> "F"
            else -> no.toString()
        }
    }
    fun hexaToNumber(s: String): Int {
        return when(s){
            "A"-> 10
            "B"-> 11
            "C"-> 12
            "D"-> 13
            "E"-> 14
            "F"-> 15
            else -> s.toInt()
        }
    }

    fun borrowZero(s: String, s1: String): ArrayList<String> {
        var l: Int = 0
        var l1: Int = 0
        var a = s
        var b = s1
        if (s.length >= s1.length) {
            l = s.length
            l1 = s1.length
            repeat(l - l1) {
                b = "0$b"
            }
        } else {
            l = s1.length
            l1 = s.length
            repeat(l - l1) {
                a = "0$a"
            }
        }


        return arrayListOf(a, b)

    }

    fun binaryRecursion(count: Int, no: String): Double {

        return if (count == 0) {
            0.0
//        (no[no.length - 1].toString().toDouble())
        } else {
            val power = (2.0.pow(count.toDouble() - 1)) * no[no.length - count].toString().toInt()
            (power + binaryRecursion(count - 1, no))
        }
    }

    fun decimalRecursion(no: Int): String {
        return if (no < 2)
            "$no"
        else {
            val tem = if (no % 2 == 0) "0" else "1"
            decimalRecursion(no / 2) + tem
        }
    }

    //101110110
//Binary Addition (+)
    fun binaryAdd(s: String, s1: String): String {
        var carry = 0
        var i = s.length
        var sum = ""
        while (i > 0) {
            when {
                (s[i - 1].toString() == "1") && (s1[i - 1].toString() == "1") -> {
                    sum = if (carry == 1)
                        "1$sum"
                    else
                        "0$sum"
                    carry = 1
                }

                (s[i - 1].toString() == "0") && (s1[i - 1].toString() == "0") -> {
                    sum = if (carry == 1)
                        "1$sum"
                    else
                        "0$sum"
                    carry = 0
                }

                else -> {
                    sum = if (carry == 1) {
                        carry = 1
                        "0$sum"
                    } else {
                        carry = 0
                        "1$sum"
                    }
                }
            }
            --i
        }
        if (carry == 1)
            sum = "1$sum"
        return sum

    }

    //Binary Subtraction (-)
    fun binarySub(s: String, s1: String): String {
        val n1 = binaryToDecimal(s)
        val n2 = binaryToDecimal(s1)
        val temp = (n1 - n2)

        return if (temp < 0) "-${decimalToBinary(abs(temp).toInt())}" else decimalToBinary(abs(temp).toInt())
    }

    //Binary Multiplication (*)
    fun binaryMul(s: String, s1: String): String {
        val n1 = binaryToDecimal(s)
        val n2 = binaryToDecimal(s1)
        val temp = (n1 * n2)

        return decimalToBinary(temp.toInt())
    }

    //Binary Division (/)
    fun binaryDiv(s: String, s1: String): Array<String> {
        val n1 = binaryToDecimal(s)
        val n2 = binaryToDecimal(s1)
        val temp = n1 / n2
        val split = temp.toString().split(".")

        return arrayOf(
            "${decimalToBinary(split[0].toInt())}",
            "${decimalToBinary((ceil(("0." + split[1]).toDouble() * n2)).toInt())}"
        )
    }

    //Binary to Gray Code
    fun binaryToGray(s: String): String {
        return s[0]+toGrayRecursion(1, s)
    }

    fun toGrayRecursion(i: Int, s: String): String {

        return if (i == s.length)
            ""
        else {
            val previous = s[i - 1].toString()
            val current = s[i].toString()
            when {
                (previous == "0" && current == "0") ||
                        (previous == "1" && current == "1") -> "0" + toGrayRecursion(i + 1, s)

                else -> "1" + toGrayRecursion(i + 1, s)
            }

        }
    }
}





