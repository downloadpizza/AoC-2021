package utils

typealias Matrix<T> = List<List<T>>

inline fun <T> readMatrix(input: String, convert: (Char) -> T): Matrix<T> =
    input.split("\n").map { it.toCharArray().map(convert) }

/**
 * Transforms a matrix by calling [transform] with each item and creating a matrix with the outputs
 */
inline fun <T, R> Matrix<T>.mapMatrix(transform: (T) -> R): Matrix<R> = this.map { it.map(transform) }

/**
 * Returns a view of the matrix within the ranges [x] and [y], which are inclusive-exclusive
 */
fun <T> Matrix<T>.subMatrix(x: IntRange, y: IntRange): Matrix<T> = this.subList(y.first, y.last).map { it.subList(x.first, x.last) }

/**
 * Returns the highest element of a Matrix or throws if no elements exist
 */
fun <T : Comparable<T>> List<List<T>>.matrixMax(): T = this.mapNotNull { it.maxOrNull() }.maxOrNull()!!

/**
 * Returns the lowest element of a Matrix or throws if no elements exist
 */
fun <T : Comparable<T>> List<List<T>>.matrixMin(): T = this.mapNotNull { it.minOrNull() }.minOrNull()!!