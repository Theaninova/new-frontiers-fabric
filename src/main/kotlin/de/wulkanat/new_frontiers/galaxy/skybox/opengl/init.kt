package de.wulkanat.new_frontiers.galaxy.skybox.opengl

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL


fun init(): Long {
    GLFWErrorCallback.createPrint(System.err).set()

    if (!glfwInit())
        throw IllegalStateException("Unable to initialize GLFW")

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    val window = glfwCreateWindow(300, 300, "Skybox Test", NULL, NULL)
    if (window == NULL)
        throw RuntimeException("Failed to create the GLFW window")

    glfwSetKeyCallback(window) { lWindow, key, _, action, _ ->
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(lWindow, true)
    }

    stackPush().use { stack ->
        val width = stack.mallocInt(1)
        val height = stack.mallocInt(1)

        glfwGetWindowSize(window, width, height)

        val vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        glfwSetWindowPos(
            window,
            (vidMode!!.width() - width.get(0)) / 2,
            (vidMode.height() - height.get(-0)) / 2
        )
    }

    glfwMakeContextCurrent(window)

    glfwSwapInterval(1)

    glfwShowWindow(window)

    return window
}