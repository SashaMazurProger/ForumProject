package com.example.sasham.testproject.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.*

/**
 * Created by terrakok 27.11.16
 */
class LocalCiceroneHolder {
    private val containers: HashMap<String, Cicerone<Router>>

    init {
        containers = HashMap()
    }

    fun getCicerone(containerTag: String): Cicerone<Router> {
        if (!containers.containsKey(containerTag)) {
            containers[containerTag] = Cicerone.create()
        }
        return containers[containerTag] as Cicerone<Router>
    }
}
