@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

import java.util.*
import kotlin.collections.LinkedHashMap

//import jdk.nashorn.internal.runtime.regexp.RegExp

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
//fun main(args: Array<String>) {
//
////    println("Введите время в формате ЧЧ:ММ:СС")
////    val line = readLine()
////    if (line != null) {
////        val seconds = timeStrToSeconds(line)
////        if (seconds == -1) {
////            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
////        }
////        else {
////            println("Прошло секунд с начала суток: $seconds")
////        }
////    }
////    else {
////        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
////    }
//
//}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val format = Regex("""^([0-9]{1,2})\s([а-я]+)\s([0-9]+)$""")
    if(!str.matches(format)) return "" // соответствие формату
    val parts = str.split(" ")
    val months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")
    val res = listOf(parts[0].toInt(), months.indexOf(parts[1]) + 1, parts[2].toInt())
    if (res[0] !in 1..31 || res[1] == 0) return "" // некорректное число || месяц
    return String.format("%02d.%02d.%d", res[0], res[1], res[2])
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String{
    val format = Regex("""^([0-9]{2}).([0-9]{2}).([0-9]+)$""")
    if(!digital.matches(format)) return "" // соответствие формату
    val parts = digital.split(".")
    val months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")
    if(parts[0].toInt() > 31 || parts[1].toInt() !in 1..12) return "" // некоррекное число || месяц
    val res = listOf(parts[0].toInt().toString(), months[parts[1].toInt() - 1], parts[2])
    return res.joinToString(separator = " ")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String{
    val res = Regex("""[\s-]""").replace(phone, "") // удаление " ", "-"
    val format = Regex("""^(\+[0-9]+)?(\([0-9]+\))?([0-9]+)$""")
    if(!res.matches(format)) return ""
    return Regex("""[()]""").replace(res, "")
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int{
    val format = Regex("""[0-9%\-\s]*""")
    val d = Regex("""[0-9]""")
    if(!jumps.matches(format) || !jumps.contains(d)) return -1 // не format || не содержит цифр
    val numeric = Regex("""%|-""").replace(jumps,"")
    val nums = Regex("""\s+""").split(numeric).toMutableList()
    val res = mutableListOf(0)
    for(e in nums)
        if(e != "") res.add(e.toInt())
    return res.max()!! // nullableInt to nonNullableInt
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val format = Regex("""[0-9%\+\-\s]*""")
    if(!jumps.matches(format)) return -1
    val parts = jumps.split(" ")
    val res = mutableListOf(0)
    for(i in parts.size - 1 downTo 0) // чтение "попыток" с конца
       if(parts[i].contains("+")) res.add(parts[i - 1].toInt()) // если высота взята (есть +), запись в res
    if(res.size == 1) return -1
    return res.max()!!
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val format = Regex("""^([0-9]+)(\s+(\+|-)\s+([0-9]+))*$""")
    if(!expression.matches(format)) throw IllegalArgumentException("for input string \"$expression\"")
    val parts = expression.split(" ")
    var res = parts[0].toInt()
    for(i in 1 until parts.size step 2){ // чтение символов операций
        if(parts[i] == "-") res -= parts[i + 1].toInt()
        else res += parts[i + 1].toInt()
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val parts = str.toLowerCase().split(" ")
    val indexes = mutableListOf(0) // индекс начала 1 слова = 0
    for(i in 0 until str.length)
        if(str[i] == ' ') indexes.add(i + 1) // индекс пробела + 1 = индекс начала след. слова
    for(i in 0 until parts.size - 1)
        if(parts[i] == parts[i + 1]) return indexes[i]
    return (-1)
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val format = Regex("""^([^;]+)\s([0-9]+(.[0-9]+)?)(;([^;]+)\s([0-9]+(.[0-9]+)?))*""")
    if(!description.matches(format)) return "incorrect"
    val parts = Regex("""\s|(;\s)""").split(description)
    var max = parts[1].toFloat()
    var max_index = 1
    for(i in 3 until parts.size step 2)
        if(parts[i].toFloat() > max){
            max = parts[i].toFloat()
            max_index = i
        }
    return parts[max_index - 1]
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val format = Regex("""[CDILMVX]+""")
    val dec = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100,
            'D' to 500, 'M' to 1000)
    var res = 0
    var bigger = 0
    if(!roman.matches(format)) return -1
    for(i in roman.length - 1 downTo 0){
        if(dec[roman[i]]!! < bigger)
            res -= dec[roman[i]]!!
        else {
            bigger = dec[roman[i]]!!
            res += bigger
        }
    }
    return res
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var sensor = cells / 2 // датчик
    var cmd_indexer = 0 // указатель на выполняемую команду
    var cmd_counter = limit // счетчик оставшихся команд
    val brackets = commandsChecking(commands)
    val brackets_r = brackets.map{it.value to it.key}.toMap() // brackets.invert()
    val conv = mutableListOf<Int>() // конвейер
    for(i in 0 until cells) conv.add(0)

    while(cmd_counter > 0 && cmd_indexer < commands.length){
        when(commands[cmd_indexer]){
            '+' -> conv[sensor]++
            '-' -> conv[sensor]--
            '[' -> if(conv[sensor] == 0) cmd_indexer = brackets[cmd_indexer]!! // переход к закрывающей скобке
            ']' -> if(conv[sensor] != 0) cmd_indexer = brackets_r[cmd_indexer]!! // возврат к открывающей скобке
            '>' -> sensor++
            '<' -> sensor--
        }
        if(sensor !in 0 until cells) throw IllegalStateException("shift commands\ncells = $cells ; sensor = $sensor")
        cmd_indexer++
        cmd_counter--
    }
    return conv
}
/*
* Проверка строки команд на корректность
* возвращает карту индексов скобок, в которой ключ - индекс открывающей скобки
* значение - индекс соответствующей ей закрывающей скобки
*/
fun commandsChecking(line: String): Map<Int, Int> {
    val format = Regex("""[<>\+\-\[\]\s]*""")
    if(!line.matches(format)) throw IllegalArgumentException("unacceptable symbols")
    val opening = mutableListOf<Int>() // список открывающих скобок (используется как стек)
    val brackets = mutableMapOf<Int, Int>() // для хранения индексов соответствующих скобок
    for(i in 0 until line.length){
        if(line[i] == '['){
            opening.add(i)
        }
        else if(line[i] == ']'){
            if(opening.isEmpty()) throw IllegalArgumentException("brackets") // закр. > откр.
            brackets.put(opening.removeAt(opening.size - 1), i)
        }
    }
    if(opening.isNotEmpty()) throw IllegalArgumentException("brackets") // откр. > закр.
    return brackets.toMap()
}