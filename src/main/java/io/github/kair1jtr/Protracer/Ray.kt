package io.github.kair1jtr.Protracer

class Ray(val orig: Vector3, val dir: Vector3) {

    fun origin(): Vector3 = orig

    fun direction(): Vector3 = dir

    fun at(t: Double): Vector3 = orig + dir * t;
}
