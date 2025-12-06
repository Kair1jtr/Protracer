package io.github.kair1jtr.Protracer

class Vector3 {
    val e = doubleArrayOf();
    constructor(e0 : Double,e1 : Double, e2: Double) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public fun x(): Double = e[0]

    public fun y(): Double = e[1]

    public fun z(): Double = e[2]

    public fun length_squared() : Double = e[0] * e[0] + e[1] * e[1] + e[2] * e[2]

    public fun length(): Double = java.lang.Math.sqrt(length_squared())


    inline operator fun plus(other: Vector3 ) = Vector3(e[0] + other.e[0],e[1] + other.e[1],e[2] + other.e[2])
    inline operator fun minus(other: Vector3 ) = Vector3(e[0] - other.e[0],e[1] - other.e[1],e[2] - other.e[2])
    inline operator fun times(other: Vector3 ) = Vector3(e[0] * other.e[0],e[1] * other.e[1],e[2] * other.e[2])
    inline operator fun div(other: Vector3 ) = Vector3(e[0] / other.e[0],e[1] / other.e[1],e[2] / other.e[2])

    inline operator fun plus(other: Double ) = Vector3(e[0] + other,e[1] + other,e[2] + other)
    inline operator fun minus(other: Double ) = Vector3(e[0] - other,e[1] - other,e[2] - other)
    inline operator fun times(other: Double ) = Vector3(e[0] * other,e[1] * other,e[2] * other)
    inline operator fun div(other: Double ) = Vector3(e[0] / other,e[1] / other,e[2] / other)
}