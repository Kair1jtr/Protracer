package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.raytracer.Camera
import io.github.kair1jtr.Protracer.raytracer.Point3
import io.github.kair1jtr.Protracer.raytracer.Utils
import io.github.kair1jtr.Protracer.raytracer.Vector3
import io.github.kair1jtr.Protracer.raytracer.pi
import processing.core.PApplet
import kotlin.math.cos
import kotlin.math.sin

class Player(var p : PApplet,point : Point3,size : Double) : GameObject(point,size){
    var hp = 5

    var ballets = mutableListOf<Ballet>()
    val aspect_ratio : Double = 16.0 / 9.0

    var cam = Camera(
        point,
        180.0,
        0.0,
        Vector3(0.0, 1.0, 0.0),
        pi /180 * 60,
        aspect_ratio
    )

    override fun move() {
        for (i in ballets.size - 1 downTo 0) {
            if (ballets[i].lifetime >= 20) {
                ballets.removeAt(i)
            }else {
                ballets[i].move()
            }
        }

        if (p.keyPressed) {
            if(p.key=='w'){
                move_forward(0.2);
            } else if(p.key=='s'){
                move_backward(0.2);
            } else if(p.key=='a'){
                move_rightward(0.3);
            } else if(p.key=='d'){
                move_leftward(0.3);
            }
        }
    }

    override fun hit_box(center: Vector3,player_size: Double): Boolean {
        return true
    }

    fun attack() {
        ballets.add(Ballet(Point3(cam.lookfrom.x, cam.lookfrom.y - 0.2, cam.lookfrom.z), 0.1,Utils.rotation_to_vector(cam.x_rot,cam.y_rot)))
    }

    fun rotateX(rot : Double) {
        cam = Camera(
            Point3(cam.lookfrom.x, cam.lookfrom.y, cam.lookfrom.z),
            cam.x_rot + rot* pi /180,
            cam.y_rot,
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }

    fun rotateY(rot : Double) {
        cam = Camera(
            Point3(cam.lookfrom.x, cam.lookfrom.y, cam.lookfrom.z),
            cam.x_rot,
            (cam.y_rot+rot* pi /180).coerceIn(-pi * 0.49, pi * 0.49),
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }

    fun move_forward(r : Double) {
        point = Point3(cam.lookfrom.x + cos(cam.y_rot)*sin(cam.x_rot)*r, cam.lookfrom.y, cam.lookfrom.z + cos(cam.y_rot)*cos(cam.x_rot)*r)

        cam = Camera(
            point,
            cam.x_rot,
            cam.y_rot,
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }

    fun move_backward(r : Double) {
        point = Point3(cam.lookfrom.x - cos(cam.y_rot)*sin(cam.x_rot)*r, cam.lookfrom.y, cam.lookfrom.z - cos(cam.y_rot)*cos(cam.x_rot)*r)

        cam = Camera(
            point,
            cam.x_rot,
            cam.y_rot,
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }

    fun move_rightward(r : Double) {
        point = Point3(cam.lookfrom.x + cos(cam.y_rot)*cos(cam.x_rot)*r, cam.lookfrom.y, cam.lookfrom.z + -cos(cam.y_rot)*sin(cam.x_rot)*r)
        cam = Camera(
            point,
            cam.x_rot,
            cam.y_rot,
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }

    fun move_leftward(r : Double) {
        point = Point3(cam.lookfrom.x - cos(cam.y_rot)*cos(cam.x_rot)*r, cam.lookfrom.y, cam.lookfrom.z - -cos(cam.y_rot)*sin(cam.x_rot)*r)
        cam = Camera(
            point,
            cam.x_rot,
            cam.y_rot,
            cam.vup,
            cam.vfov,
            cam.aspect_ratio
        )
    }
}