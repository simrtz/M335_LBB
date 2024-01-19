package com.example.triviaquiz.domain.questionDataTypes

enum class Category(val apiValue: Int, val stringName: String) {
    ANY_CATEGORY(0, "Any Category"),
    GENERAL_KNOWLEDGE(9, "General Knowledge"),
    ENTERTAINMENT_BOOKS(10, "Entertainment: Books"),
    ENTERTAINMENT_FILM(11, "Entertainment: Film"),
    ENTERTAINMENT_MUSIC(12, "Entertainment: Music"),
    ENTERTAINMENT_MUSICALS(13, "Entertainment: Musicals &"),
    ENTERTAINMENT_TELEVISION(14, "Entertainment: Television"),
    ENTERTAINMENT_VIDEO_GAMES(15, "Entertainment: Video Games"),
    ENTERTAINMENT_BOARD_GAMES(16, "Entertainment: Board Games"),
    SCIENCE_NATURE(17, "Science & Nature"),
    SCIENCE_COMPUTERS(18, "Science: Computers"),
    SCIENCE_MATHEMATICS(19, "Science: Mathematics"),
    MYTHOLOGY(20, "Mythology"),
    SPORTS(21, "Sports"),
    GEOGRAPHY(22, "Geography"),
    HISTORY(23, "History"),
    POLITICS(24, "Politics"),
    ART(25, "Art"),
    CELEBRITIES(26, "Celebrities"),
    ANIMALS(27, "Animals"),
    VEHICLES(28, "Vehicles"),
    ENTERTAINMENT_COMICS(29, "Entertainment: Comics"),
    SCIENCE_GADGETS(30, "Science: Gadgets"),
    ENTERTAINMENT_JAPANESE_ANIME(31, "Entertainment: Japanese Anime & Manga"),
    ENTERTAINMENT_CARTOON_ANIMATIONS(32, "Entertainment: Cartoon & Animations");
    companion object {
        fun fromStringName(stringName: String): Category? {
            return entries.find { it.stringName == stringName }
        }

        fun fromApiValue(apiValue: Int): Category? {
            return entries.find { it.apiValue == apiValue }
        }
    }
}
