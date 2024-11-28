plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.apkmelali_test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apkmelali_test"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.android.car.ui:car-ui-lib:2.6.0")
    implementation("androidx.room:room-runtime:2.4.0")
    implementation("com.google.ar:core:1.45.0")
    implementation("androidx.test:core:1.6.1")
    annotationProcessor("androidx.room:room-compiler:2.4.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation("org.mockito:mockito-inline:4.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("org.robolectric:robolectric:4.6.1") // Use the latest version available
}
