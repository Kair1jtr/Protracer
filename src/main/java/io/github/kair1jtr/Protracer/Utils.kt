package io.github.kair1jtr.Protracer

inline fun dot(v1: Vector3, v2: Vector3): Double = v1.e[0] * v2.e[0] + v1.e[1] * v2.e[1] + v1.e[2] * v2.e[2]

inline fun cross(v1: Vector3, v2: Vector3): Vector3 = Vector3(
        v1.e[1] * v2.e[2] - v2.e[1] * v1.e[2],
        v1.e[2] * v2.e[0] - v2.e[2] * v1.e[0],
        v1.e[0] * v2.e[1] - v2.e[0] * v1.e[1]
    )

inline fun unit_vector(v : Vector3) : Vector3 = v / v.length();