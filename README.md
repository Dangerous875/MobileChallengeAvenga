#  Mobile challenge Avenga: Focus, Search and Visualization of Countries.
> [!NOTE]
>  This project uses a robust architecture based on Clean Architecture and the MVVM pattern, integrating technologies such as Retrofit, Dagger Hilt, Room, Coil, Jetpack Compose and Kotlin Serialization 2.8.0 for secure navigation arguments. The main screen, MainScreen, is the heart of the application, allowing the display and search of more than 200,000 countries. The application follows an offline-first approach, ensuring functionality even without an internet connection.
In case of connectivity problems on the first load, a screen is displayed with an informative message and a “Try Again” button to retry the connection. If the data is already stored in the local database, the application will work without interruption.
________________________________________

# Approach for Solving the Search Problem

1. Reactive Search
The search is implemented in a reactive way using a LaunchedEffect, which filters the list of countries in real time according to the prefix entered by the user, ignoring upper and lower case. When consuming the API for the first time, the data is stored in a local database to reduce load times in future executions.
2. State Management
The MainScreenState class is used to centralize the screen state:

    data class MainScreenState(

    val isLoading: Boolean = true,

    val countries: List<CountryModel> = emptyList(),

    val filterCountries: List<CountryModel> = emptyList()

    )

    •	isLoading: Indicates whether data is being loaded.

    •	countries: Contains all the countries, updating only when checking or unchecking favorites.

    •	filterCountries: Keeps the list filtered, optimizing the search without affecting performance.

3. Data Preprocessing
Special characters, such as apostrophes ('), are removed from country names. This improves search accuracy by avoiding problems related to string comparison.
4. Filter Optimization

    - A dynamic filter is applied to show only the countries marked as favorites.
    
    - The filterCountries list is continuously updated, improving the user experience by reducing redundant calculations.

6. Background Thread Operations
To maintain a smooth user interface, expensive operations such as filtering and database updates are performed in background threads (Dispatchers.IO).

________________________________________

# Important Decisions
1. Data Structure
The list of countries is represented as a List<CountryModel>, where each model includes:

    - Unique identifier (id).
    
    - Name of the city (name).
    
    - Country name (country).
    
    - Latitude (latitude).
    
    - Longitude (longitude).
    
    - Favorite indicator (isFavorite).

2. Modular Composable Components
   
    - SearchCountry: Manages user input and search logic.
    
    - MainScreen: Orchestrates the search, display and management of favorites, integrating with the ViewModel.

5. Favorites Compatibility
The isFavorite attribute in the data model allows managing favorite countries. Changes are reflected both in the database and in the user interface in a synchronized manner.
6. Scalability and Performance
 
    - State representation with StateFlow ensures efficient updates.
    
    - The architecture facilitates the integration of new functionalities, such as sorting or grouping.
________________________________________
Representation Justification
Representing countries as a List<CountryModel> offers several advantages:
1. ease of filtering: the standard filter function allows quick and easy searches.
2.	Compatibility with Compose: The list is easily integrated with components such as LazyColumn, optimizing the visualization.
3.	Scalability: It is flexible to adapt to future requirements, such as pagination or categorization.
________________________________________

# Future Improvements
1. Data loading: Implement pagination for a more optimal first data loading.

# Images of the App.

![Descripción de la imagen](https://drive.google.com/uc?id=1WQkKHFOENdGlRfWL5UW13g3CvVcLiVKW)
>
________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1Dt4lQouSy8g27jSr3uwSeBj_ji7bIvi1)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=17AzcNEzxhnVSgJ01Pv8k9uqJK8_Sc1kA)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=11bRXpY7jSgh1jwtWlfPrPrskR1iEzFZT)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1CxT3lnvJemek9IhseyArlnaWJhjB9KCx)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1N6u5RoKf8OJ4rXMFTSudWTYd4PkyUhbc)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1kx1yconv60k0LgJGWhMkiQUuAdHOdJjR)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1hZMuJRjyiTO2XWwGCItPQn7vat9bRc6v)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1JFvLghbe8bT7Jp7Icrep8OK8vxU-Askm)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1M9yY4fpS7XwhOJW4EWPPCcjpiQT8w-Ro)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1N1NKK8Uz5bnOwjQ5Ll_ZwBLZ0G6TIMgG)

________________________________________

![Descripción de la imagen](https://drive.google.com/uc?id=1JKYMajJCi1Op8BplaB9Cqnjr0N7uGR6p)
>



