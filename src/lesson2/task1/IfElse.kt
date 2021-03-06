@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import java.lang.Math.*
import lesson1.task1.discriminant
import lesson1.task1.sqr

fun sqr(x: Double) = x * x
/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    age % 10 == 1 && age % 100 != 11 -> "$age год"
    age % 100 == 11 || age % 100 in 12..14 || age % 10 == 0 || age % 100 in 15..19
            || age % 10 in 5..9 && age % 100 !in 15..19 -> "$age лет"
    else -> "$age года"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
/**решение
 * 1)найти путь 1 2 3
 * 2)найти весь путь поделить два
 * 3)конструкция when -> L/2 В 1 , 2 и 3 промежутке в зависимости от этого просчитать
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val way1 = t1 * v1
    val way2 = t2 * v2
    val way3 = t3 * v3
    val way = (way1 + way2 + way3) / 2.0
    return when {
        way < way1 -> way / v1
        way < way2 + way1 -> t1 + (way - way1) / v2
        else -> t1 + t2 + (way - way2 - way1) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1 = rookX1 == kingX || kingY == rookY1
    val rook2 = kingX == rookX2 || kingY == rookY2
    return when {
        rook1 && rook2 -> 3
        rook1 -> 1
        rook2 -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val rook = rookX == kingX || rookY == kingY
    val bishop = abs(bishopX - kingX) == abs(bishopY - kingY)
    return when {
        bishop && rook -> 3
        rook -> 1
        bishop -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val max = Math.max(Math.max(a, b), c)
    val min = Math.min(Math.min(a, b), c)
    val medium = a + b + c - min - max
    return when {
        max > min + medium -> -1
        sqr(max) < sqr(min) + sqr(medium) -> 0
        sqr(max) == sqr(min) + sqr(medium) -> 1
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
        when {
            a >= c && d >= b  -> b - a
            a <= c && d <= b -> d - c
            c in a..b -> b - c
            d in a..b -> d - a
            else -> -1
        }



