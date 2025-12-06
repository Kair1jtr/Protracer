package io.github.kair1jtr.Protracer

class Vector3(val x: Double, val y: Double, val z: Double) {

    public fun length_squared(): Double = x * x + y * y + z * z

    public fun length(): Double = Math.sqrt(length_squared())

    public fun normalize(): Vector3 = this * (1.0 / length())

    //ベクトル量とベクトル量での計算
    operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Vector3): Vector3 = Vector3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Vector3): Vector3 = Vector3(x / other.x, y / other.y, z / other.z)

    //ベクトル量とスカラー量での計算
    operator fun plus(scalar: Double): Vector3 = Vector3(x + scalar, y + scalar, z + scalar)
    operator fun minus(scalar: Double): Vector3 = Vector3(x - scalar, y - scalar, z - scalar)
    operator fun times(scalar: Double): Vector3 = Vector3(x * scalar, y * scalar, z * scalar)
    operator fun div(scalar: Double): Vector3 = Vector3(x / scalar, y / scalar, z / scalar)

}

typealias Point3 = Vector3
typealias Color = Vector3