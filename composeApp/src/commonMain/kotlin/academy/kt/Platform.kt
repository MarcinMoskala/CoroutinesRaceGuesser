package academy.kt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform