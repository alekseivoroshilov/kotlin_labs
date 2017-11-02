@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int{
    var num = n
    var i = 1
    while(num > 9){
        num /= 10
        i++
    }
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int{ // => fib(n) = fib(n-1) + fib(n-2)
    if(n<3) return 1
    return fib(n-1) + fib(n-2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int{
    var a = m
    var b = n
    while(a != b){ // нахождение НОД (алгоритм Евклида)
        if(a > b) a -= b
        else b -= a
    }
    return m*n/a  // НОК(m,n) = m*n/НОД(m,n)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int{
    var i = 2
    while(n % i != 0) i++
    return i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int{
    var i = n-1
    while(n % i != 0) i--
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean{
    var i = Math.min(m, n)
    while(m % i != 0 || n %i != 0) i--
    return i == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val sqrt = Math.sqrt(n.toDouble()).toInt() // целый корень от n
    val pow = Math.pow(sqrt.toDouble(), 2.0) // полный квадрат, ближайший к n
    return pow in m..n // если такого полн. квадрата нет в (m;n), то в этом промеж. вообще нет полн.кв.
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double{
    val arg = x % (Math.PI * 2)
    var member = x
    var sin = 0.0
    var pow = 1.0
    var xpow = arg
    var fact = 1.0
    while(Math.abs(member) > eps){
        member = xpow / fact
        sin += member
        pow += 2.0
        fact *= (pow - 1.0) * pow
        xpow = -xpow * arg * arg
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double{
    val arg = x % (Math.PI * 2)
    var member = x
    var cos = 1.0
    var pow = 2.0
    var xpow = -arg * arg
    var fact = 2.0
    while(Math.abs(member) > eps){
        member = xpow / fact
        cos += member
        pow += 2.0
        fact *= (pow - 1.0) * pow
        xpow = -xpow * arg * arg
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int{
    var i=n
    var res=0
    while(i>0){
        res = res*10 + i%10
        i/=10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean{
    var k=1; var l: Int; var r: Int
    var num = n
    while(num/k>=10) k*=10 // k= pow(10, кол-во_цифр - 1)
    while(num>=10) { // пока в num >1 цифры
        l = num/k // крайняя цифра слева
        r = num%10 // крайняя цифра справа
        if (l!=r) return false
        num = (num % k) / 10 // отсечение крайних цифр
        k/=100 // (т.к. кол-во цифр уменьшилось на 2)
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean{ // попарное сравнение символов
    var str = n.toString()
    for(i in 0..(str.length-2))
        if(str[i]!=str[i+1]) return true
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int{
    var i = 0
    var j = 0
    var buf: String
    while(j < n){ // пока число цифр не достигло требуемого
        i++
        j += digitNumber(i*i) // суммирование кол-ва цифр квадратов
    }
    buf = (i*i).toString()
    i = buf.length - (j - n + 1)
    return (buf[i].toInt() - 48)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int{
    var i = 0
    var j = 0
    var buf: String
    while(j < n){ // пока число цифр не достигло требуемого
        i++
        j += digitNumber(fib(i))
    }
    buf = fib(i).toString()
    i = buf.length - (j - n + 1)
    return (buf[i].toInt() - 48)
}