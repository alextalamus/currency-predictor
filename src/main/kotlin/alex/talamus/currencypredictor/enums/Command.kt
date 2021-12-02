package alex.talamus.currencypredictor.enums

enum class Command(val command: String, val description: String) {
    START("start", "init player"),
    PSEUDO("pseudo", "change pseudonym"),
    INFO_PLAYER("player", "player info"),
    INFO_GAME("game", "game info"),
    CREATE("create", "create game"),
    JOIN("join", "join the game"),
    BEGIN("begin", "begin the game"),
    END("end", "end the game")
}