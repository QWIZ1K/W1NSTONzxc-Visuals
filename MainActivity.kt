package com.winston.visuals

import android.app.Activity
import android.os.Bundle
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {
    private val background = Color.rgb(13, 10, 24)
    private val purple = Color.rgb(151, 91, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = background
        window.navigationBarColor = background

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 28, 22, 24)
            setBackgroundColor(background)
        }

        root.addView(TextView(this).apply {
            text = "𝙒1𝙉𝙎𝙏𝙊𝙉𝙯𝙭𝙘\n𝙑𝙞𝙨𝙪𝙖𝙡𝙨"
            textSize = 30f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        })

        root.addView(TextView(this).apply {
            text = "BEDROCK VISUAL LAUNCHER  •  1.26.33.1"
            textSize = 11f
            setTextColor(0xFFB9A8D8.toInt())
            setPadding(0, 8, 0, 26)
        })

        root.addView(actionCard("QUICK LAUNCH", "Open Minecraft Bedrock") {
            launchPackage("com.mojang.minecraftpe", "Minecraft Bedrock не найден")
        })

        root.addView(actionCard("ATLAS CLIENT", "Try opening installed Atlas") {
            val candidates = listOf("com.atlas.client", "com.atlasclient")
            val intent = candidates.firstNotNullOfOrNull {
                packageManager.getLaunchIntentForPackage(it)
            }
            if (intent != null) startActivity(intent)
            else Toast.makeText(this, "Atlas не найден. Установи его отдельно.", Toast.LENGTH_LONG).show()
        })

        root.addView(section("VISUAL SETTINGS"))
        listOf("Particles", "RGB accent", "Smooth HUD").forEach { label ->
            root.addView(Switch(this).apply {
                text = label
                textSize = 15f
                setTextColor(Color.WHITE)
                buttonTintList = ColorStateList.valueOf(purple)
                isChecked = true
                setPadding(4, 12, 4, 12)
            })
        }

        root.addView(section("NOTICE"))
        root.addView(TextView(this).apply {
            text = "Переключатели пока демонстрационные. Приложение запускает установленные приложения, но не внедряется в Minecraft и не меняет файлы игры. Поддержку реальных ресурс-паков добавим отдельно."
            textSize = 12f
            setTextColor(0xFFB9B1C8.toInt())
        })

        setContentView(ScrollView(this).apply { addView(root) })
    }

    private fun launchPackage(packageName: String, errorMessage: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) startActivity(intent)
        else Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun section(label: String) = TextView(this).apply {
        text = label
        textSize = 12f
        setTextColor(0xFFB78CFF.toInt())
        typeface = Typeface.DEFAULT_BOLD
        setPadding(0, 26, 0, 10)
    }

    private fun actionCard(title: String, detail: String, action: () -> Unit): LinearLayout {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 16, 18, 16)
            setBackgroundColor(0xFF211832.toInt())
        }
        box.addView(TextView(this).apply {
            text = title
            textSize = 12f
            setTextColor(0xFFB78CFF.toInt())
            typeface = Typeface.DEFAULT_BOLD
        })
        box.addView(TextView(this).apply {
            text = detail
            textSize = 16f
            setTextColor(Color.WHITE)
            setPadding(0, 8, 0, 12)
        })
        box.addView(Button(this).apply {
            text = "OPEN"
            setTextColor(Color.WHITE)
            backgroundTintList = ColorStateList.valueOf(purple)
            setOnClickListener { action() }
        })
        box.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { bottomMargin = 14 }
        return box
    }
}
