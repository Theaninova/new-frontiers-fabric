package de.wulkanat.new_frontiers.world.dimension.model.skybox.opengl

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun loop(window: Long) {
    GL.createCapabilities()

    glClearColor(1F, 0F, 0F, 0F)

    while(!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        glfwSwapBuffers(window)

        glfwPollEvents()
    }
}