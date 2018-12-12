# AAD Capstone Attempt - The Movie Companion 

## Summary
My application for the Capstone Project.
The Movie Companion application will search a database for the name of a movie,
and display these results to the user in a list. 

## Activities
### Search Activity (Main Activity)
This will be the activity with a search bar and buttons. Allowing the user to input a movie title to search for.
### Results Activity
A RecyclerView that displays a list of all the available movies (maybe limited to a certain number).
It shows the title and brief description. Clicking one will take you to a details page detailing more information.
### Movie Details Activity
A detailed view displaying the title, description, poster image, date released and other information relevent to the
specific movie.
### Favoutires Activity
This has a list view that displays a list of movies the user has favvourited.

## BroadcastReciever
This app will listen for netowrk changes in the device. Displaying an appropriate toast message notifying the user when the 
app goes offline or comes back online again to block/allow user search requests to go through.

## Content Provider
A content provider will be used to maintain a local persistant storage of all 
user data. This will be an SQL database that stores favourite movies the user liked.
Maybe all search history will also be stored.

## Services
Services will be used for any (asynchronous) network requests that the app will need to make to
the api. I mean the application will listen for changes in network state, and promt the user,
with a toast message, to reconnect before making any further searches.

## Online Service
The Movie DB, a database api that stores information on movies and tv shows.: https://www.themoviedb.org/documentation/api .
I will be making calls to this api and recieving data (in Json form) to use in my app.