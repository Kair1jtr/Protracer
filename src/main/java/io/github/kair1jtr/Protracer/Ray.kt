package io.github.kair1jtr.Protracer

class Ray(val orig: Vector3, val dir: Vector3) {
    fun at(t: Double): Vector3 = orig + dir * t;
}
