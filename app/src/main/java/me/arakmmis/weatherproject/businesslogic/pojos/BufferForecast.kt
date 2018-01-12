package me.arakmmis.weatherproject.businesslogic.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BufferForecast(

        @SerializedName("list")
        @Expose
        var list: List<ListWeather>,

        @SerializedName("city")
        @Expose
        var city: City) {

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
            var weather: List<Weather>,

            @SerializedName("wind")
            @Expose
            var wind: Wind
    )

    class Main(
            @SerializedName("temp")
            @Expose
            var temp: Double
    )

    class Weather(
            @SerializedName("main")
            @Expose
            var main: String
    )

    class Wind(
            @SerializedName("speed")
            @Expose
            var speed: Double
    )

    fun toForecast(): Forecast {
        return Forecast(
                id = list[0].dt.toString() + city.name + city.country,
                cityName = city.name,
                countryName = city.country,
                dt = list[0].dt,
                temp = list[0].main.temp,
                weatherDesc = list[0].weather[0].main,
                windSpeed = list[0].wind.speed
        )
    }
}
