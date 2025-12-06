package io.github.kair1jtr.Protracer

class Vector3(val x: Double, val y: Double, val z: Double) {

    public fun length_squared(): Double = x * x + y * y + z * z

    public fun length(): Double = Math.sqrt(length_squared())

    public fun normalize(): Vector3 = this * (1.0 / length())

    // ベクトル同士の演算
    operator fun plus(other: Vector3): Vector3 =
        Vector3(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector3): Vector3 =
        Vector3(x - other.x, y - other.y, z - other.z)

    operator fun times(other: Vector3): Vector3 =
        Vector3(x * other.x, y * other.y, z * other.z)

    operator fun div(other: Vector3): Vector3 =
        Vector3(x / other.x, y / other.y, z / other.z)

    // スカラーとの演算
    operator fun plus(scalar: Double): Vector3 =
        Vector3(x + scalar, y + scalar, z + scalar)

    operator fun minus(scalar: Double): Vector3 =
        Vector3(x - scalar, y - scalar, z - scalar)

    operator fun times(scalar: Double): Vector3 =
        Vector3(x * scalar, y * scalar, z * scalar)

    operator fun div(scalar: Double): Vector3 =
        Vector3(x / scalar, y / scalar, z / scalar)

    // 文字列表現（デバッグ用）
    override fun toString(): String = "($x, $y, $z)"

    // 等価比較
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector3) return false
        return x == other.x && y == other.y && z == other.z
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }
}