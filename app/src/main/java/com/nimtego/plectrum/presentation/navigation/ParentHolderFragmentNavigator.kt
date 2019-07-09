package com.nimtego.plectrum.presentation.navigation

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class ParentHolderFragmentNavigator(
        activity: AppCompatActivity,
        fragmentManager: FragmentManager?,
        container: Int,
        private val parentRouter: Router
) : SupportAppNavigator(activity, fragmentManager, container) {
//    override fun unknownScreen(command: Command) {
//        when (command) {
//            is Forward -> this.parentRouter.navigateTo(command.screen)
//            is Back -> this.parentRouter.exit()
//            is Replace -> this.parentRouter.replaceScreen(command.screen)
//            is BackTo -> this.parentRouter.backTo(command.screen)
//        }
//    }
//
//    override fun exit() {
//        this.parentRouter.exit()
//    }

}