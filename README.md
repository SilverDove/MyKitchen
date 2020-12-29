# MyKitchen
Clara Tricot - 4th Year at ESIEA (2020/2021)

## Introduction
This project is an Android application in Kotlin helping the users to search recipes and add them into a list to keep track of the favorite recipes.
It uses the online database spoonacular as a API to access a list of recipes.
It is intuitive and optimized for the ease of everyone. Enjoy it!

## Getting Started
1. Download and install [Android Studio](https://developer.android.com/studio/)
2. Clone or download the [master branch](https://github.com/SilverDove/MyKitchen.git) using the functionalities of Android Studio. 
It's also possible to fetch the [release project](https://github.com/SilverDove/MyKitchen/releases/tag/v1.0) and then import it directly to Android Studio.


## Features

### Must Have
* Kotlin language (or Swift language for IOS)
* MVVM architecure
* Clean Architecture
* Database
* Api REST
* Display a list
* Design

### What I implemented
* 3 screens (activities)
    * Home page: search any recipe
    * FavoriteList Page: list all favorite recipes of the user
    * Details Page: display details of the recipe selected
* API Rest call from spoonacular API
* Room Database to store the recipe in the list
* Navigation between the different activities
* SearchBar with filtering
* MVVM architecture
* Clean architecture
* RecyclerView to display the list of recipe
* Kotlin language
* GitFlow up-to-date
* Copyright


 ## Overview
 
 ### Home Page
  The user can search any recipe by using the search bar. At the beginning, a message is displayed to indicate what the user should do.
  <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Home_Page.png" width="400" height="600" />
  
 * **If the recipe exists** 
 If a user wrote more than 3 characters without submited, then the result is also displayed in a list
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Home_Page_Incomplete_Search.png" width="400" height="600" />
  
 If what the user wrote and submitted exists, then a list of recipe is displayed (image + title)
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Home_Page_Search.png" width="400" height="600" />
 
 * **If the recipe doesn't exist** If the user wrote something that doesn't matched, then an error message appears
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Home_Page_Error.png" width="400" height="600" />
 
 ### Details Page
 After clicking on one of the recipe from the list (see Home Page or FavoriteList Page), this page displays the information about the recipe. 
 The user can see if the recipe is vegetarian, vegan, gluten free and/or dairy free. 
 We can see the number of people who like this recipe, how much Weight Watcher points does this recipe has and so on ...
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Details1.png" width="400" height="600" />
 
 Then, the user can see the ingredients with their amount and units. 
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Details2.png" width="400" height="600" />

At the end, we can see the instructions and the source url of the recipe.
<img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Details3.png" width="400" height="600" />
 
 The user can add or remove the recipe to/from the favoriteList. Each recipe from the favoriteList are stored into the Room Database
  * **Add into the favoriteList:** 
  To add the recipe into the favorite list , the user need to click on the icon at the top right of the screen. Then, the icon changes to indicate that the recipe is in the favorite List. Moreover, a toast appears during few seconds to confirm the operation.
  <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Details_Page_Add.png" width="400" height="600" />
 
  * **Remove from the favoriteList:** 
  To remove the recipe from the favorite list , the user need to click on the icon at the top right of the screen. Then, the icon changes to indicate that the recipe is not in the favorite List anymore. Moreover, a toast appears during few seconds to confirm the operation.
  <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Details_Page_Remove.png" width="400" height="600" />
 ![]()

 ### FavoriteList Page
 This page lists all the recipes that the user saves as "favorite". After clicking on a specific recipe from the list, it displays extra information about it. 

 * **When the list is not empty:** 
 When the list is not empty, it display, as said earlier, the list of recipe put as "favorite" by the user. A "Delete All" button is visible and allows the user to remove all the recipe from this list.
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Favorite_List.png" width="400" height="600" />
 
 * **When the list is empty:** 
 If the list is empty (it can be after clicking on the "Delete All" button, a message appears to the screen and the "Delete All" button is no longer visible.
 <img src="https://github.com/SilverDove/MyKitchen/blob/master/ScreenShots/Favorite_List_empty.png " width="400" height="600" />

 ## Authors
  * **Clara Tricot** - *MyKitchen* - [SilverDove](https://github.com/SilverDove)
 
 ## License
 This project is licensed under the MIT license.

 Copyright (c) 2020 Clara Tricot
 
 ## Acknowledgments
 Inspired by my previous project [MyMovie&CO](https://github.com/SilverDove/MyMoviesAndCO) and helped by my teacher [Vincent ETIENNE](https://github.com/vincent-etienne)
  
  
