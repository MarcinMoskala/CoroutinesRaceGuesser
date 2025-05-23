package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

class LevelLoader<K, T>(
    private val factory: suspend (K) -> T,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) {
    private var loaded = mutableMapOf<K, Deferred<T>>()

    fun startLoading(key: K) {
        loaded += key to scope.async {
            factory(key)
        }
    }

    suspend fun getOrLoad(key: K): T {
        loaded.remove(key)?.let {
            return it.await()
        }
        return factory(key)
    }
}

class LevelLoaderTest {

}
