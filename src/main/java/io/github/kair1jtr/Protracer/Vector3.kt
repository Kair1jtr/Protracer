package io.github.kair1jtr.Protracer

import kotlin.time.measureTime

class Vector3(val x: Double, val y: Double, val z: Double) {

    fun length_squared(): Double = x * x + y * y + z * z

    fun length(): Double = Math.sqrt(length_squared())

    fun normalize(): Vector3 = this * (1.0 / length())

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

    operator fun unaryMinus() : Vector3 = Vector3(-x,-y,-z)
}

typealias Point3 = Vector3
typealias Color = Vector3