package com.target.targetcasestudy.ui.main_navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.core.theme.AppTheme
import com.target.targetcasestudy.ui.main_navigation.navigation.MainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainNavigator()
            }
        }
    }
}
