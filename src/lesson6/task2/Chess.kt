@file:Suppress("UNUSED_PARAMETER")

package lesson6.task2

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
val word = listOf("", "a", "b", "c", "d", "e", "f", "g", "h")

data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        if (inside() == false) return ""
        val word = listOf("", "a", "b", "c", "d", "e", "f", "g", "h")
        return word[column] + row
    }
}

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    val word = " abcdefgh"
    if (!notation.matches(Regex("""[a-h][1-8]"""))) throw IllegalArgumentException()
    return Square(word.indexOf(notation[0].toString()), notation[1].toString().toInt())
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
    var n = 0
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    if (start == end) return 0
    if (Math.abs(start.row - end.row) != 0) {
        n++
    }
    if (start.column != end.column) {
        n++
    }
    return n
}

/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> {
    val list = mutableListOf(start)
    return when (rookMoveNumber(start, end)) {
        0 -> list
        1 -> list + end
        else -> list + Square(start.column, end.row) + end
    }
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    return when {
        start == end -> return 0
        start.column - start.row == end.column - end.row || start.column + start.row == end.column + end.row -> 1
        start.column + start.row % 2 == end.column + end.row % 2 -> 2
        else -> -1
    }
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    val list = mutableListOf(start)
    val tmp = (Math.abs(end.column - start.column) + Math.abs(end.row - start.row)) / 2
    return when (bishopMoveNumber(start, end)) {
        0 -> list
        1 -> list + end
        2 -> when {
            (start.column + tmp in 1 until 8) && (start.row + tmp in 1 until 8) ->
                list + Square(start.column + tmp, start.row + tmp) + end
            (start.column + tmp in 1 until 8) && (start.row + tmp !in 1 until 8) ->
                list + Square(start.column + tmp, start.row - tmp) + end
            (start.column + tmp !in 1 until 8) && (start.row + tmp in 1 until 8) ->
                list + Square(start.column - tmp, start.row + tmp) + end
            else -> list + Square(start.column - tmp, start.row - tmp) + end
        }
        else -> listOf()
    }
}

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || (!end.inside())) throw IllegalArgumentException()
    if (start == end) return 0
    val coorX = Math.abs(start.column - end.column)
    val coorY = Math.abs(start.row - end.row)
    return when {
        start.column == end.column -> coorY
        start.row == end.row -> coorX
        else -> if (coorX > coorY) coorX
        else coorY
    }
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> {
    val list = mutableListOf(start)
    if (end == start) return list
    when {
        start.row == end.row -> {
            for (i in 1..Math.abs(start.column - end.column)) {
                list.add(Square(start.column + i, start.row))
                print(Square(start.column + i, start.row))
            }
        }
        start.column == end.column -> {
            for (i in 1..Math.abs(start.row - end.row)) {
                list.add(Square(start.column, start.row + i))
            }
        }
        else -> {
            var x = 0
            val count = Math.min(Math.abs(start.column - end.column), Math.abs(start.row - end.row))
            while (x != count) {
                x++
                when {
                    (end.column - start.column > 0) && (end.row - start.row > 0) -> {
                        if (Square(start.column + x, start.row + x).inside()) {
                            list.add(Square(start.column + x, start.row + x))
                        }
                    }
                    (end.column - start.column > 0) && (end.row - start.row < 0) -> {
                        if (Square(start.column + x, start.row - x).inside()) {
                            list.add(Square(start.column + x, start.row - x))
                        }
                    }
                    (end.column - start.column < 0) && (end.row - start.row > 0) -> {
                        if (Square(start.column - x, start.row + x).inside()) {
                            list.add(Square(start.column - x, start.row + x))
                        }
                    }
                    (end.column - start.column < 0) && (end.row - start.row < 0) -> {
                        if (Square(start.column - x, start.row - x).inside()) {
                            list.add(Square(start.column - x, start.row - x))
                        }
                    }
                }
            }
            if (list.last().row != end.row)
                for (i in 1..Math.abs(list.last().row - end.row))
                    list.add(Square(end.column, list.last().row + 1))
            else for (i in 1..Math.abs(list.last().column - end.column)) {
                if (list.last().column - end.column > 0)
                    list.add(Square(list.last().column - 1, end.row))
                else list.add(Square(list.last().column + 1, end.row))
            }
        }

    }
    return list
}

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int = TODO()

/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> = TODO()
