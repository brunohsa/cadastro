import org.junit.Test

class Teste {

    @Test
    fun test() {
        val regex = "^[A-Za-z0-9\\s/?:().,+\\-]+".toRegex()


        "aaaaa".matches(regex)
    }
}