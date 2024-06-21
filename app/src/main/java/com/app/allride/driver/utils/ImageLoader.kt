package com.app.allride.driver.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import android.widget.ImageView
import com.squareup.picasso.Picasso


class ImageLoader {
    companion object {

        fun loadCircleImageFromUrl(
            @NonNull imageView: ImageView,
            @NonNull imageUrl: String,
            @DrawableRes fallbackImage: Int
        ) {
            if (TextUtils.isEmpty(imageUrl)) {
                imageView.setImageResource(fallbackImage)
            } else {
                val context = imageView.context

                Picasso.get()
                    .load(imageUrl)
                    .placeholder(fallbackImage)
                    .transform(CircleTransform())
                    .error(fallbackImage)
                    .into(imageView)
            }
        }

        fun loadImageFromUrl(
            @NonNull imageView: ImageView,
            @NonNull imageUrl: String,
            @DrawableRes fallbackImage: Int) {
            if (TextUtils.isEmpty(imageUrl)) {
                //ToDo
                imageView.setImageResource(fallbackImage)
            } else {
                val context = imageView.context
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(fallbackImage)
                    .into(imageView)
            }
        }

        private fun getCompatibleDrawable(
            @NonNull context: Context, @DrawableRes drawableRes: Int
        ): Drawable {
            return ContextCompat.getDrawable(context, drawableRes)!!.current
        }
    }
}