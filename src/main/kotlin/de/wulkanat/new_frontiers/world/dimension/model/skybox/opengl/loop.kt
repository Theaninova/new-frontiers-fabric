package de.wulkanat.new_frontiers.world.dimension.model.skybox.opengl

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun loop(window: Long) {
    GL.createCapabilities()

    glClearColor(1F, 0F, 0F, 0F)

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(0.0, 800.0, 0.0, 600.0, 1.0, -1.0)
    glMatrixMode(GL_MODELVIEW)

    while(!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        // draw quad
        glColor3f(0.5f, 0.5f, 1.0f)
        glBegin(GL_QUADS)
        glVertex2f(100f, 100f)
        glVertex2f(300f, 100f)
        glVertex2f(300f, 300f)
        glVertex2f(100f, 300f)
        glEnd()

        glfwSwapBuffers(window)

        glfwPollEvents()
    }
}