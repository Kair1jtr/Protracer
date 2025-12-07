package io.github.kair1jtr.Protracer

object Utils {
    fun dot(v1: Vector3, v2: Vector3): Double =
        v1.x * v2.x + v1.y * v2.y + v1.z * v2.z

    fun cross(v1: Vector3, v2: Vector3): Vector3 = Vector3(
        v1.y * v2.z - v2.y * v1.z,
        v1.z * v2.x - v2.z * v1.x,
        v1.x * v2.y - v2.x * v1.y
    )

    fun unitVector(v: Vector3): Vector3 = v / v.length()

    fun degrees_to_radians(degrees: Double) : Double = degrees * pi / 180
}
