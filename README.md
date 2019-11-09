TravelerReviewApp
=================

**Developed by:[Prashanth Ramakrishnan](prashanth_r03@yahoo.co.in)**

- To run the app, open in Android Studio, download dependencies and launch the app.
- To use the release version, please create a signing config to sign the build -> install the release apk.


**Features**
- Fetches the review details via the specified API endpoint and shows the review preview list in a recycler 
view, clicking on it launches the review details screen.
- Does'nt use the sort query parameter for the API to sort ratings, only uses the limit and offset queries for pagination
- Includes unit tests for ViewModel and instrumentation tests using Robotium and MockWebServer for the app flow.
- Uses Android architecture components - LiveData, ViewModel, Paging library -the RecyclerView list automatically hands pagination with the help of Paging library

Refer [here](https://gist.github.com/jemshit/767ab25a9670eb0083bafa65f8d786bb) for proguard rules.

**Note**
- I found that my API was not working when running via the Android app, although it worked on Postman; that is why you would see a API header
added that specifies the User-Agent explicitly as Postman.
- Styling and animations are minimum, I used Android CardView to show the list and LinearLayout with TextViews for the details screen, 
everything is pretty straight-forward
- If a photo is present it is shown in the details screen, I used Glide to load images.
- I Decided to not use dependency injection although in my first commit I did use Dagger2 and later reverted it back.
- I used the MaterialRatingBar library to show the rating stars in the app (Quite a handy one!).
- There is no database in this application, data is shown as is from the API calls!
- Networking errors are shown as-is.
- Tested on Moto G4 running Android 7.0, I don't prefer using the Emulator.
- Development tools: Android Studio-3.5.2 with Gradle-3.5.2, Gradle wrapper-5.4.1.

**Open source libaries used**
- **[RxJava2](https://github.com/ReactiveX/RxJava)**
- **[RxAndroid](https://github.com/ReactiveX/RxAndroid)**
- **[Retrofit2](https://github.com/square/retrofit)**
- **[OkHttp3](https://github.com/square/okhttp)**
- **[Glide](https://github.com/bumptech/glide)**
- **[Gson](https://github.com/google/gson)**
- **[Timber](https://github.com/JakeWharton/timber)**
- **[Project Lombok](https://projectlombok.org)**
- **[Robotium](https://github.com/RobotiumTech/robotium)**
- **[MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)**
- **[Commons-io](https://commons.apache.org/proper/commons-io/)**
- **[MaterialRatingBar](https://github.com/zhanghai/MaterialRatingBar)**

### License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.