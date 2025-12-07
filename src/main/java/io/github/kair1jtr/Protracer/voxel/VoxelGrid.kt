package io.github.kair1jtr.Protracer.voxel

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import kotlin.math.max
import kotlin.math.min

class VoxelGrid {
    val voxels = mutableListOf<Voxel>()

    fun addCube(centerX: Double, centerY: Double, centerZ: Double,
                size: Double, color: Color
    ) {
        val pos = Point3(centerX, centerY, centerZ)
        voxels.add(Voxel(pos, size, color))
    }

    fun hit(r: Ray): Pair<Double, Color>? {
        var closestT = Double.MAX_VALUE
        var hitColor: Color? = null

        for (voxel in voxels) {
            val halfSize = voxel.size / 2.0
            val min = Point3(
                voxel.position.x - halfSize,
                voxel.position.y - halfSize,
                voxel.position.z - halfSize
            )
            val max = Point3(
                voxel.position.x + halfSize,
                voxel.position.y + halfSize,
                voxel.position.z + halfSize
            )

            val t = hitAABB(min, max, r)
            if (t > 0 && t < closestT) {
                closestT = t
                hitColor = voxel.color
            }
        }

        return if (hitColor != null) Pair(closestT, hitColor!!) else null
    }

    private fun hitAABB(min: Point3, max: Point3, r: Ray): Double {
        var tmin = 0.0
        var tmax = Double.MAX_VALUE

        for (i in 0..2) {
            val axisMin = when(i) {
                0 -> min.x; 1 -> min.y; else -> min.z
            }
            val axisMax = when(i) {
                0 -> max.x; 1 -> max.y; else -> max.z
            }
            val rayOrig = when(i) {
                0 -> r.orig.x; 1 -> r.orig.y; else -> r.orig.z
            }
            val rayDir = when(i) {
                0 -> r.dir.x; 1 -> r.dir.y; else -> r.dir.z
            }

            if (Math.abs(rayDir) < 1e-8) {
                // レイが軸に平行な場合
                if (rayOrig < axisMin || rayOrig > axisMax) return -1.0
            } else {
                val t1 = (axisMin - rayOrig) / rayDir
                val t2 = (axisMax - rayOrig) / rayDir
                val tMinAxis = min(t1, t2)
                val tMaxAxis = max(t1, t2)

                tmin = max(tmin, tMinAxis)
                tmax = min(tmax, tMaxAxis)

                if (tmin > tmax) return -1.0
            }
        }

        return if (tmin > 0) tmin else tmax
    }
}