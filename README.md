# Ktfix

## Project Description
Ktfix is a fixture lib that helps you test your code by providing an easy way to create fake data.

### Usage
Considering your code has a data class such as:
```kotlin
data class User(
    val name: String,
    val lastName: String,
    val isActive: Boolean
)
```
You can create a user object with random data:
```kotlin
val user = Fixture.build<User>()
```

If you need to specify a value to some property, you can call build with a mutable map: 
```kotlin
val activeUser = mutableMapOf<String, Any>(
    ("isActive" to true))

val user = Fixture.build<User>(activeUser)
```

## Limitations:

Ktfix does not support the generation of random data every type of data yet,
checkout our next steps in TODO

### Todo:
- Generate
  - Enum
  - Collections
- Allow user to create templates to use instead of mutable maps
