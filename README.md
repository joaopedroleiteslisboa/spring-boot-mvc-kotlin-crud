# spring boot and kotlin wedding :) testing crud operation using spring web(rest layer) and Spring Data Jpa with Kotlin

![Spring boot kotlin](/images/spring_boot_kotlin.png?raw=true "Spring boot kotlin")





This is an example project trying to learn  Kotlin using Spring mvc and Spring data Jpa. When i finish the project i will
give my own opinion about this new language (for me is new because I am just reading how to write code with it) and how difficult is to learn kotlin if you are a java (it's my case)





- Use Spring data jpa H2 in memory Database
- Use mapping between layers using (https://stackoverflow.com/questions/39199426/better-way-to-map-kotlin-data-objects-to-data-objects)
- Create integration test layer using mockmvc and rest template
- Test when "pattern matching" and Enum classes with static methods using companion object
- Test kotlin amazing null properties: using Safe Calls operator (?.) and the Elvis Operator (?:)
+
[source,kotlin]
----
userService.findByName(name)?.let { User.createFromUserModel(it) }?: throw UserNotFoundException("user not found")
----
- Added A convenient and performant logging library wrapping slf4j with Kotlin extensions. https://github.com/MicroUtils/kotlin-logging
+
[source,kotlin]
----
private val LOGGER = KotlinLogging.logger {}
// using Kotlin's String templates: String literals may contain template expressions, i.e. pieces of code that are evaluated and whose results are concatenated into the string
LOGGER.info("User $savedEntity  was successfully created")
----
