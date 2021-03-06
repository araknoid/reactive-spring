package dao

import data.Sensor
import data.SensorType
import kotlinx.coroutines.experimental.reactor.flux
import kotlinx.coroutines.experimental.time.delay
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ControlUnit {

    fun getSensors() = flux {
        delay(latency)
        for (i in 1..33) {
            for ((type, contexts) in sensorsTypes) {
                for (context in contexts)
                    send(Sensor(i, type, context))
            }
        }
    }

    private companion object {
        val latency: Duration = Duration.ofMillis(600)
        val sensorsTypes = mapOf(
                SensorType.SALINITY to listOf("FRESH", "BRACKISH", "SALT"),
                SensorType.TEMPERATURE to listOf("POLAR", "TEMPERATE", "TROPICAL")
        )
    }
}
