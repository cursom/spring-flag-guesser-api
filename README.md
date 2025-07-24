
# 🌎 Flag Guesser API (Spring)

A simple Guess the Flag game backend built with Spring Framework.
It randomly fetches country data, and lets users guess which country's flag is shown.

## Stack

- Java 21+
- Spring Boot
- Caffeine Cache
- REST Countries API (https://restcountries.com)
- Lombok

## Game Logic

- `/generate` – Starts a new game by returning a flag svg and a unique code to identify the session.
- `/guess` – You send your guess, and the API tells if you're right or wrong.

Each game expires in **60 seconds**.

## 🧪 API Endpoints

### `POST /generate`

Creates a new game session.

**Response:**
```json
{
  "flagSvg": "https://...",
  "code": "...",
  "codeMeta": "00.."
}
```

### `POST /guess`

Send a guess for a flag.

**Request:**
```json
{
  "guess": "Country Name",
  "code": "..."
}
```

**Response:**
```json
{
  "country": "Correct Country Name",
  "codeMeta": "00..",
  "result": "OK/WRONG/EXPIRED",
  "seconds": 60
}
```
* `seconds` is how fast they got the flag

## Status Endpoint

`GET /` returns basic info about the api and author.

---

## Status Code Meta

These are short application-level codes:

- `0083` – game created successfully
- `0061` – correct guess
- `0097` – wrong guess
- `0078` – game expired

(used for ui hints, not http status codes)

---

*All flag and country name data comes from restcountries.com*
