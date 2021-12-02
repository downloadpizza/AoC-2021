# Advent of code 2021
These are my kotlin solutions for however long I manage to take part without giving up.

## How to run
Add a Config.kt file locally and put in the following:
```kt
object Config {
    val session: String = "?????"
}
```

## Reeee, be gentle on the server
I am caching all files fetched. If you open it in a browser or in a single GET request programatically does not matter.
Auto downloaders dont do things to the server, auto submitters which ignore the one minute and bad leaderboard implementations do.
