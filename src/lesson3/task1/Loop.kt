@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*

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
    for (m in 2..n / 2) {
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
fun digitNumber(n: Int): Int {
    var number = 0
    var x = n
    if (x == 0) return (1)
    while (x > 0) {
        number += 1
        x /= 10
    }
    return (number)
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var b = 0
    var c = 1
    var a = 0
    var i = 0
    while (n > i){
        a = c
        c = b
        b = a + b
        i ++}
    return(b)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val pr = (m * n)
    var a = m
    var b = n
    while (a !== b) {
        if (a > b) a -= b
        else b -= a
    }
    return (pr / a)


}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var x = 2
    while (n % x !== 0) {
        x += 1
    }
    return (x)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var x = 0
    var max = -1
    while (n > x) {
        x += 1
        if (((n % x) == 0) && (x > max) && (n !== x)) {
            max = x
        }
    }
    return (max)
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in (1..min(m, n))) {
        if (((((m % i) == 0) && ((n % i) == 0))) && (i !== 1)) return false
        else continue
    }
    return true
}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var x = 0
    var a = sqrt((m).toDouble())
    var b = sqrt((n).toDouble())
    for (i in a.toInt()..b.toInt()) {
        x += 1
    }
    if (x > 0) return true
    else return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = Math.sin(x) //шутка - минутка

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = Math.cos(x)

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var k = n
    var a = 0
    while (k > 0) {
        a = a * 10 + k % 10
        k = k / 10
    }
    return (a)
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    if (revert(n) == n) return true
    else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var x = n
    var residue = 0
    var k = 0 //количество одинаковых цифр
    var number = 0 //количество цифр
    //Проверка : все ли цифры одинаковые
    while ((x > 0) && (k == number)) {
        residue = x % 10
        x /= 10
        if ((x % 10) == residue) {
            k += 1
        }
        number += 1
    }
    if (n == 0) return false
    if (number == k) return false
    else return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 * решение =
 * ревернуть число
 * н - 6 while x/10
 *% 10
 */

fun squareSequenceDigit(n: Int): Int {
    var str = 0
    var number = 1
    var length = 0
    while (length < n) {
        str = number * number
        length += digitNumber(str)
        number++
    }
    if (length > n) {
        while (length != n) {
            str /= 10
            length--
        }
    }
    return str % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var str = 0
    var number = 1
    var length = 0
    while (length < n) {
        str = fib(number)
        length += digitNumber(str)
        number++
    }
    if (length > n) {
        while (length != n) {
            str /= 10
            length--
        }
    }
    return str % 10
}
