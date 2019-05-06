package gn.example.mvptest.bean;

import java.util.List;

public class Weather {

    private String code;
    private String msg;
    private List<WeatherList> newslist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<WeatherList> getNewsList() {
        return newslist;
    }

    public void setNewsList(List<WeatherList> newsList) {
        this.newslist = newsList;
    }

    public class WeatherList {
        private String date;
        private String highest;
        private String lowest;
        private String weather;
        private String week;
        private String wind;
        private String windspeed;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHighest() {
            return highest;
        }

        public void setHighest(String highest) {
            this.highest = highest;
        }

        public String getLowest() {
            return lowest;
        }

        public void setLowest(String lowest) {
            this.lowest = lowest;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        @Override
        public String toString() {
            return "WeatherList{" +
                    "date='" + date + '\'' +
                    ", highest='" + highest + '\'' +
                    ", lowest='" + lowest + '\'' +
                    ", weather='" + weather + '\'' +
                    ", week='" + week + '\'' +
                    ", wind='" + wind + '\'' +
                    ", windspeed='" + windspeed + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", newsList=" + newslist +
                '}';
    }
}
