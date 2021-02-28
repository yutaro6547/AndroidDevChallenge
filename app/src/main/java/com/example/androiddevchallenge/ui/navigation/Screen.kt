package com.example.androiddevchallenge.ui.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

sealed class Screen(val id: ScreenName) {
    object PuppiesList: Screen(ScreenName.PUPPIES_LIST)
    data class PuppiesDetail(val name: String, val imageResourceId: Int, val description: String    ): Screen(ScreenName.PUPPIES_DETAIL)

    companion object {
        private const val SCREEN_NAME = "screen_name"
        private const val ARGS_NAME = "args_name"
        private const val ARGS_IMAGE_RESOURCE_ID = "args_image_resource_id"
        private const val ARGS_DESCRIPTION = "args_description"

        fun Screen.toBundle(): Bundle {
            return bundleOf(SCREEN_NAME to id.name).also {
                if (this is PuppiesDetail) {
                    it.putString(ARGS_NAME, name)
                    it.putInt(ARGS_IMAGE_RESOURCE_ID, imageResourceId)
                    it.putString(ARGS_DESCRIPTION, description)
                }
            }
        }

        fun Bundle.toScreen(): Screen {
            val screenName = ScreenName.valueOf(requireNotNull(getString(SCREEN_NAME)) { "Missing key '$SCREEN_NAME' in $this" })
            return when (screenName) {
                ScreenName.PUPPIES_LIST -> PuppiesList
                ScreenName.PUPPIES_DETAIL -> {
                    val name = requireNotNull(getString(ARGS_NAME)) { "Missing key '$ARGS_NAME' in $this" }
                    val imageResourceId = getInt(ARGS_IMAGE_RESOURCE_ID)
                    val description = requireNotNull(getString(ARGS_DESCRIPTION)) { "Missing key '$ARGS_DESCRIPTION' in $this" }
                    PuppiesDetail(name,imageResourceId, description)
                }
            }
        }
    }
}
