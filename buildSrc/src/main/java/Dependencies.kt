object Deps {
    const val CLASSPATH_GRADLE_TOOLS_BUILD = "com.android.tools.build:gradle:${Versions.CLASSPATH_GRADLE_TOOLS_BUILD}"
    const val CLASSPATH_KOTLIN_GRADLE_PLUGIN ="org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.CLASSPATH_KOTLIN_GRADLE_PLUGIN}"
    const val CLASSPATH_KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Versions.CLASSPATH_KOTLIN_SERIALIZATION}"
    const val CLASSPATH_KTOR_CLIENT_CORE_JVM = "io.ktor:ktor-client-core-jvm:${Versions.CLASSPATH_KTOR_CLIENT_CORE_JVM}"
    const val CLASSPATH_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}"

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val ANDROIDX_COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val ANDROIDX_COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    const val ANDROIDX_COMPOSE_UI_TOOLING_PERVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIX_LIFECYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ANDROIDX_ACTIVITY_COMPOSE}"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.ANDROIDX_LIFECYCLE_VIEWMODEL_COMPOSE}"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE}"


    /**------------------ KTOR -----------------------*/
    const val KTOR_CLIENT_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR_CLIENT_ANDROID}"
    const val KTOR_CLIENT_SERIALIZATION = "io.ktor:ktor-client-serialization:${Versions.KTOR_CLIENT_SERIALIZATION}"
    const val KOTLINX_SERIALIAZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION_JSON}"
    const val KTOR_CLIENT_LOGGING = "io.ktor:ktor-client-logging-jvm:${Versions.KTOR_CLIENT_LOGGING}"
    const val KTOR_CLIENT_CORE_JVM ="io.ktor:ktor-client-core-jvm:${Versions.KTOR_CLIENT_CORE_JVM}"

    /**-------------------EITHER----------------------*/
    const val ARROW_CORE = "io.arrow-kt:arrow-core:${Versions.ARROW_CORE}"

    /**------------------- HILT -----------------------*/
    const val HILT_ANDROID =  "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT_VERSION}"
    const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE}"

    /**------------------- COROUTINES -----------------*/
    const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_COROUTINES}"

    /**------------------- GLIDE --------------------------*/
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    /**------------------ COIL ----------------------------*/
    const val COIL ="io.coil-kt:coil-compose:${Versions.COIL}"

    /**----------------- SYSTEM_UI_CONTROLLER -------------------------------*/
    const val SYSTEM_UI_CONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:${Versions.SYSTEM_UI_CONTROLLER}"

}


object TestDeps {
    const val JUNIT = "junit:junit:${TestVersion.JUNIT}"
    const val JUNIT_ANDROIDX_TEST_EXT = "androidx.test.ext:junit:${TestVersion.JUNIT_ANDROIDX_TEST_EXT}"
    const val ANDROIDX_TEST_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${TestVersion.ANDROIDX_TEST_ESPRESSO_CORE}"
    const val ANDROIDX_COMPOSE_UI_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
    const val ANDROID_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
}

