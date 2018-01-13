package me.arakmmis.weatherproject.businesslogic.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList

class BufferForecast(

        @SerializedName("list")
        @Expose
        var list: List<ListWeather>,

        @SerializedName("city")
        @Expose
        var city: City,

        @SerializedName("cnt")
        var cnt: Int) {

    class City(
            @SerializedName("name")
            @Expose
            var name: String,

            @SerializedName("country")
            @Expose
            var country: String
    )

    class ListWeather(
            @SerializedName("dt")
            @Expose
            var dt: Int,

            @SerializedName("main")
            @Expose
            var main: Main,

            @SerializedName("weather")
            @Expose
            var weather: List<BufferWeather>,

            @SerializedName("wind")
            @Expose
            var wind: BufferWind
    )

    class Main(
            @SerializedName("temp")
            @Expose
            var temp: Double
    )

    class BufferWeather(
            @SerializedName("main")
            @Expose
            var main: String,

            @SerializedName("description")
            @Expose
            var desc: String
    )

    class BufferWind(
            @SerializedName("speed")
            @Expose
            var speed: Double
    )

    fun toForecast(): Forecast {
        val city = city.name + ", " + city.country.toUpperCase()

        val dt: RealmList<DateTime> = RealmList()
        val temp: RealmList<Temperature> = RealmList()
        val weather: RealmList<Weather> = RealmList()
        val wind: RealmList<Wind> = RealmList()

        for (i in 0 until cnt) {
            if (i == 0
                    || (i % 8 == 0 && dt.size < 4)
                    || i == cnt - 1) {
                dt.add(DateTime(list[i].dt))
                temp.add(Temperature(list[i].main.temp))
                weather.add(Weather(list[i].weather[0].main, list[i].weather[0].desc))
                wind.add(Wind(list[i].wind.speed))
            }
        }

        return Forecast(city = city,
                dt = dt,
                temp = temp,
                weather = weather,
                wind = wind)
    }
}
