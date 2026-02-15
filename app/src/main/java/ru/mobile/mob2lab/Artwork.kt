package ru.mobile.mob2lab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val authorRes: Int,
    @StringRes val descriptionRes: Int // Для TalkBack (Accessibility)
)