@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

//import jdk.nashorn.internal.ir.annotations.Immutable
import lesson1.task1.discriminant
/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) =
        list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double{
    var abs = 0.0
    for(e in v) abs += Math.pow(e, 2.0)
    return Math.sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double{
    var aver = 0.0
    if(list.isNotEmpty()) {
        for (e in list) aver += e
        return aver / list.size
    }
    return aver
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double>{
    val aver = mean(list)
    for(i in 0 until list.size) list[i]-=aver
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double{
    var mul = 0.0
    for(i in 0 until a.size) mul+= a[i]*b[i]
    return mul
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var res = 0.0
    if(p.isNotEmpty())
        for(i in 0 until p.size)
            res += p[i] * Math.pow(x, i.toDouble())
    return res
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double>{
    if(list.isNotEmpty()) {
        for (i in 1 until list.size)
            list[i] += list[i-1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int>{
    var list = mutableListOf<Int>()
    var num = n
    var fact = 2
    while(num > 1){
        if(num % fact == 0) {
            list.add(fact)
            num /= fact
        }
        else fact++
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String =
    factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int>{
    val list = mutableListOf<Int>()
    var num = n
    if(n != 0) {
        while (num > 0) {
            list.add(num % base)
            num /= base
        }
        return list.reversed()
    }
    else return listOf(0)
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String{
    val list = convert(n, base)
    var buf = StringBuilder(list.size)
    if(n == 0) return "0"
    for (i in 0 until list.size) {
        if (list[i] > 9) buf.append((87 + list[i]).toChar()) // a = 97, b = 98, c = 99 ...
        else buf.append(list[i])
    }
    return buf.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int{
    var pow = digits.size
    var res = 0
    for(e in digits){
        pow--
        res += e * intPow(base, pow)
    }
    return res
}

fun intPow(num: Int, pow: Int): Int{ // = Math.pow(Int)
    var res = 1
    for(i in 1..pow)
        res *= num
    return res
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int{
    var pow = str.length
    var res = 0
    var i: Int
    for(c in str){
        i = c.toInt()
        if(i <= 57) i -= 48 // {0-9} = {48-57}
        else i -= 87 // {a-z} = {97-122}      (a-87=10, b-87=11 и т.д.)
        pow--
        res += i * intPow(base, pow)
    }
    return res
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String{
    val arab = arrayOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val rome = arrayOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    var buf = StringBuilder()
    var num = n
    var i = rome.size - 1 // указатель на последний элемент rome
    while(num > 0){ //
        if(num < arab[i]) i--
        else {
            buf.append(rome[i])
            num -= arab[i]
        }
    }
    return buf.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String{
    var num = n
    val res = mutableListOf<String>()
    val words = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь",
            "девять", "десять", "одиннадцать", "двенадцать", "триннадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать",
            "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто",
            "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот")
    // инициализация списка чисел, соответствующих списку их прописных обозначений
    val numbers = mutableListOf<Int>()
    for(i in 1..20) numbers.add(i)
    for(i in 30..100 step 10) numbers.add(i)
    for(i in 200..900 step 100) numbers.add(i)

    if(num >= 1000){
        res += getHundredsWords(num / 1000, words, numbers, true)
        res.add(getThousandsWords(num / 1000))
        num %= 1000
    }
    res += getHundredsWords(num, words, numbers)

    return res.joinToString(separator = " ")
}

fun getThousandsWords(n: Int): String {
    var num = n
    if(num % 100 > 19) num %= 10
    return when(num % 100){
        1 -> "тысяча"
        in 2..4 -> "тысячи"
        else -> "тысяч"
    }
}

fun getHundredsWords(n: Int, words: List<String>, numbers: MutableList<Int>): List<String>{
    var res = mutableListOf<String>()
    var num = n
    var i = numbers.size - 1
    while(num > 0){
        if(num < numbers[i]) i--
        else{
            res.add(words[i])
            num -= numbers[i]
        }
    }
    return res
}

fun getHundredsWords(n: Int, words: List<String>, numbers: MutableList<Int>, flag: Boolean): List<String>{
    var res = mutableListOf<String>()
    var num = n
    var i = numbers.size - 1
    while(num > 0){
        if(num < numbers[i]) i--
        else{
            when(num) {
                1 ->  res.add("одна")
                2 -> res.add("две")
                else -> res.add(words[i])
            }
            num -= numbers[i]
        }
    }
    return res
}