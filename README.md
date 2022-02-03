# Playlist App - a test app to learn Android development

Based on Udemy tutorial https://wooliesx.udemy.com/course/android-11-tdd-masterclass/

## Demo showing tests passing and app running
https://user-images.githubusercontent.com/18099038/152274387-12488e30-29e9-4e92-bd62-89e0f33e980b.mov



## Technologies and methodologies

- TDD (Unit and instrumentation tests)
- Coroutines
- Jetpack navigation
- and more!

## Requirements
 - Mock server configured in `PlaylistModule.kt`
 - I used https://mockoon.com
 - Two mocks
    - GET /playlists
   ```
    [
    {
    "id": "1",
    "name": "Hard Rock Cafe",
    "category": "rock"
    },
    {
    "id": "2",
    "name": "Chilled House",
    "category": "house"
    },
    {
    "id": "3",
    "name": "US TOP 40 HITS",
    "category": "mixed"
    },
    {
    "id": "4",
    "name": "90's Rock",
    "category": "rock"
    },
    {
    "id": "5",
    "name": "Purple Jazz",
    "category": "jazz"
    },
    {
    "id": "6",
    "name": "90's flashback",
    "category": "pop"
    },
    {
    "id": "7",
    "name": "Machine Funk",
    "category": "electro"
    },
    {
    "id": "8",
    "name": "Let's Groove",
    "category": "mixed"
    },
    {
    "id": "9",
    "name": "Feel The Beat",
    "category": "electro"
    },
    {
    "id": "10",
    "name": "Best Songs 2020",
    "category": "mixed"
    }
    ]
    ```
    - GET /playlist-details/1
    ```
    {
      "id": "1",
      "name": "Hard Rock Cafe",
      "details": "Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door"
    }
    ```
