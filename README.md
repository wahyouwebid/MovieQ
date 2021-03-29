# MovieQ
Android Movies build with Component Architechtures, Jetpack Pagination, Room, RxJava, Coroutines, Hilt

### App Features
* **Movie** - menampilkan daftar movie terbaru
* **TV Show** - menampilkan daftar tv show terbaru
* **Movie Favorite** - menampilkan daftar movie yang sudah ditambahkan sebagai favorite
* **TV Favorite** - menampilkan daftar tv yang sudah ditambahkan sebagai favorite
* **Search Movie & TV** - untuk melakukan pencarian movie & tv

### Screenshot
<span align="center">
 <hr>
 <p align="center"><img src="screenshot/banner.png" alt="MovieQ Screenshot" width="850" height="500"></p>
 <p align="center">Screenshot</p>
 <hr>
 </span>

### API
Api yang digunakan dalam project ini yaitu https://developers.themoviedb.org

Base URL yang digunakan adalah sebagai berikut
```
https://developers.themoviedb.org/3/
```

#### Endoint Used

|Method | Endpoint | Usage |
| ---- | ---- | --------------- |
|GET| `/search/movie` | Search Movies|
|GET| `/search/tv` | Search TV Show.| 
|GET| `/search/tv` | Search TV Show.| 
|GET| `/movie/{movie_id}` | Get Detail Movie.| 
|GET| `/movie/popular` | Get Popular .| 
|GET| `/movie/top_rated` | Get Top Rated Movie| 
|GET| `/movie/upcoming` | Get Upcoming Movie.| 
|GET| `/movie/{movie_id}/videos` | Get Videos Movie.| 
|GET| `/tv/airing_today` | Get TV Airing Today.| 
|GET| `/tv/{tv_id}` | Get TV Detail.| 
|GET| `/tv/popular` | Get TV Popular.| 
|GET| `/tv/top_rated` | Get TV Top Rated.| 
