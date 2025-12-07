package io.github.kair1jtr.Protracer.voxel

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Point3

data class Voxel(
    val position: Point3,
    val size: Double,
    val color: Color
)