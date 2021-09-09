# Ktfix

## Project Description
Ktfix is a fixture lib that helps you test your code by providing an easy way to create fake data.

### ☕ Usage
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

## 🚫 Limitations:

Ktfix does not support the generation of random data every type of data yet,
checkout our next steps in TODO

### 🛠️ Adjustments and improvements:

The project is still under development and future updates will focus on the following tasks:
- [x] Generate Date Types
- [x] Generate Enum
- [ ] Generate Collections
- [ ] Add support to use of templates


## 📫 Contributing to Ktfix
To contribute to Ktfix, follow these steps:

1. Fork this repository.
2. Create a branch: `git checkout -b <branch_name>`.
3. Make your changes and commit then: `git commit -m '<commit_message>'`
4. Send to origin branch: `git push origin <project_name> / <local>`
5. Create a pull request.

Alternatively, see the GitHub documentation at [how to create new pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).


[⬆ Back to top](#Ktfix)<br>