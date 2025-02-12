package me.leon.ctf

import kotlin.test.assertEquals
import me.leon.classical.*
import me.leon.classical.bifid
import me.leon.classical.bifidDecrypt
import me.leon.encode.base.base100
import me.leon.encode.base.base100Decode2String
import me.leon.ext.crypto.TABLE_A_Z_LOWER
import me.leon.ext.math.circleIndex
import me.leon.ext.stripAllSpace
import org.junit.Test

class CtfTest {
    @Test
    fun pawnshopTest() {
        val d = "flag{pawn_sh0p_ciph3r}"
        d.pawnshop().also { assertEquals(d, it.pawnshopDecode()) }
        assertEquals("CTF{RM}", "王夫 井工 夫口 由中人 井中 夫夫 由中大".pawnshopDecode())
    }

    /**
     * 偶数列 奇数列 HelloWorldab gesfcinphodtmwuqouryzejrehbxvalookT lrbaoleWdloH The quick brown fox
     * jumps over the lazy dog
     */
    @Test
    fun curveCipher() {
        val d = "HelloWorldab"
        val d2 = "gesfcinphodtmwuqouryzejrehbxvalookT"
        val d3 = "The quick brown fox jumps over the lazy dog".replace(" ", "")
        assertEquals("lrbaoleWdloH", d.curveCipher(3, 4))
        assertEquals(d, "lrbaoleWdloH".curveCipherDecode(3, 4))
        assertEquals("Thequickbrownfoxjumpsoverthelazydog", d2.curveCipherDecode(5, 7))
        assertEquals(d2, d3.curveCipher(5, 7))
    }

    @Test
    fun circleIndex() {
        assertEquals(2, 0.circleIndex(3, 5))
        assertEquals(0, 1.circleIndex(3, 5))
        assertEquals(1, 2.circleIndex(3, 5))
    }

    @Test
    fun asciiShift() {
        val emoji =
            "🙃💵🌿🎤🚪🌏🐎🥋🚫😆😍🐍✅🐎👑😡😁😁😍🕹🦓😂🕹🚪🎤💵📂✉🥋🚪😂🚪👑🐘🍎🍎🐍👑🙃😁✅😀🥋🗒"
        val emojiShift1 =
            "🐎🤣🌏🚰👁🌪🌊✉❓💵✅💧🔪🌊👉🎃😆😆✅📂🏹🥋📂👁🚰🤣🛩🚹✉👁🥋👁👉🌿🍌🍌💧👉🐎😆🔪🖐✉🍎"
        val base64 = "U2FsdGVkX19v+Vq7009NzjNds2OlkdjdqEaavqU0+gk="

        assertEquals(base64, emoji.emojiReplaceDecode())
        assertEquals(base64, emojiShift1.emojiReplaceDecode(1))
        assertEquals(emoji, base64.emojiReplace().also { println(it) })
        assertEquals(emojiShift1, base64.emojiReplace(1))
    }

    @Test
    fun tableSub() {
        val table =
            "2 22 222 3 33 333 4 44 444 5 55 555 6 66 666 7 77 777 7777 8 88 888 9 99 999 9999"
        val d = TABLE_A_Z_LOWER
        println(d.tableEncode(table, " "))
        println("333 555 2 4 444 7777 44 2 66 3 999 222 666 3 33".tableDecode(table))
    }

    @Test
    fun porta() {
        val d = "where is Porta Cipher flag"
        d.porta("hello").also { assertEquals(d.stripAllSpace().uppercase(), it.porta("hello")) }
    }

    @Test
    fun beaufort() {
        val d = "where is beaufort Cipher flag"
        d.beaufort("hello").also {
            assertEquals(d.stripAllSpace().uppercase(), it.beaufort("hello"))
        }
    }

    @Test
    fun gronsfeld() {
        val d = "where is gronsfeld Cipher flag"
        d.gronsfeld().also { assertEquals(d.stripAllSpace().uppercase(), it.gronsfeldDecrypt()) }
    }

    @Test
    fun fourSquare() {
        val key1 = "OPQABCDEFGHRSTUVWXYZIKLMN"
        val key2 = "GHIKOPQRSYZABTULMNVWXCDEF"
        val d = "Foursquare Cipher"
        d.fourSquare(key1, key2).also {
            assertEquals(d.stripAllSpace().uppercase(), it.fourSquareDecrypt(key1, key2))
        }
    }

    @Test
    fun bifid() {
        val key = "mabkynvhgsorpczxedfqwtuil"
        val d = "defend the east wall of the castle"
        d.bifid(key, 3).also {
            assertEquals(d.stripAllSpace().uppercase(), it.bifidDecrypt(key, 3))
        }
    }

    @Test
    fun trifid() {
        val key = "EPSDUCVWYM.ZLKXNBTFGORIJHAQ"
        val d = "D E F E N D T H E E A S T W A L L O F T H E C A S T L E .".stripAllSpace()
        d.trifid(key, 5).also { assertEquals(d, it.trifidDecrypt(key, 5)) }
    }

    @Test
    fun coreValues() {
        println("hello开发工具箱".socialistCoreValues())
        ("公正爱国公正平等公正诚信文明公正诚信文明公正诚信平等友善爱国平等诚信民主诚信文明爱国富强友善爱国平等爱国诚信平等敬业民主诚信自由平等" +
                "友善平等法治诚信富强平等友善爱国平等爱国平等诚信民主法治诚信自由法治友善自由友善爱国友善平等民主")
            .socialistCoreValuesDecrypt()
            .also { assertEquals("hello开发工具箱", it) }
    }

    @Test
    fun brainFuck() {
        val data =
            "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook! Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook! Ook! Ook! Ook! " +
                "Ook! Ook! Ook? Ook. Ook? Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook? Ook. " +
                "Ook? Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook! Ook! Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook? Ook. " +
                "Ook? Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! " +
                "Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook! Ook. " +
                "Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook? Ook. Ook? Ook! Ook. Ook? Ook! Ook! Ook! Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook? " +
                "Ook. Ook? Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook! Ook. Ook. Ook. Ook! Ook. Ook. Ook. Ook! Ook. Ook. Ook. Ook! Ook. " +
                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! " +
                "Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook! Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. " +
                "Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook."
        val ookEngine = OokEngine(8)
        assertEquals("flag{ok-ctf-1234-admin}", ookEngine.interpret(data))

        val bf = "++++++++++[>+++++++++>++++++++>+++++++<<<-]>---.>+.---.-.-.>+.-----."
        assertEquals("WQNMLGB", BrainfuckEngine(1024).interpret(bf))

        val troll =
            "Trooloolooloolooloolooloolooloolollooooolooloolooloolooloolooooolooloolooloolooloolo" +
                "oloolooloooooloolooloooooloooloolooloololllllooooloololoooooololooolooloolooloolooloololool" +
                "ooolooloololooooooloololooooloololooloolooloolooloolooloolooloolooloolooloololoooooloooloolool" +
                "olooollollollollollolllooollollollollollollollollloooooololooooolooll"
        assertEquals("Hello World!\n", TrollScriptEngine(1024).interpret(troll))
    }

    @Test
    fun b100() {
        val s = "hello开发工具箱".toByteArray()
        val encoded = s.base100()
        assertEquals("👟👜👣👣👦🏜🎳🍷🏜🎆🎈🏜🎮🎜🏜🍼🎮🏞🎥🎨", s.base100())
        assertEquals("hello开发工具箱", encoded.base100Decode2String())
    }

    @Test
    fun blind() {
        val d =
            "⡥⠂⡶⡃⡔⡷⡦⡛⡨⠁⠟⡚⠉⠇⡳⡜⡉⡤⡴⡑⡓⡆⡑⡔⡆⡠⡩⡹⠂⡢⡪⡵⡢⡟⡶⡹⠃⡒⠁⡥⡞⠟⡚⡞⡣⡣⡤⡀⡡⡆⠉⡼⡻⠀⠉⡧⡙⠇⡦⡇⡧⡅⡺⡑⠺⡑⡉⡑⠂⡞⡱⡳⠁" +
                "⡊⡢⡩⡊⡚⡊⡕⡛⠀⡕⠂⡩⡱⡾⡴⠂⡶⡛⠈⡹⡇⡗⡑⠃⠁⡆⡝⡽⡺⡨⡙⠛⠅⠁⡠⡇⡩⡅⡸⡑⡧⡑⡸⠅⡆⡨⠛⡣⡨⡑⡢⡝⠁⡟⡚⡿⠺⠛⡿⡕⡴⡛⡡⠀⡔⠉" +
                "⠂⡴⡃⠃⠀⡿⡹⠄⡺⡀⡵⡊⡝⡪⡨⡛⡦⡖⡛⡧⡡⡪⠈⡲⠟⡝⡔⡕⠅⡄⡞⠟⠂⡵⡉⠅⡩⡦⡼⡈⡴⡩⡈⠟⡞⡦⡩⡆⡛⡴⡾⡈⡁⡁⡗⠺⡹⡾⡆⡢⡹⡠⡈⡃⡛⠆" +
                "⡁⡖⡻⡉⡡⡻⡓⠆⡁⡼⡷⠃⡛⠅⡵⠈⡝⡂⠉⡃⡄⡠⡠⡡⡒⡁⡃⡁⠅⡾⡨⠆⡘⠇⡄⡁⡲⠅⡖⠛⡓⡤⡃⡕⡺⡃⡝⡛⡳⠀⡢⡒⡙⠂⠺⡱⡉⡻⡒⡨⡄⡒⡒⡈⡱⡧⡽" +
                "⠆⡉⡷⡹⠛⡊⠟⡥⡜⡳⡶⠆⡺⠉⠂⡂⡛⡥⡓⡝⡴⠆⡽⡟⠅⡿⡻⡸⡺⠆⡇⠂⠈⡼⡤⡕⠂⠈⡤⠅⠛⠁⡇⡟⡧⡈⡗⡲⡊⡸⠉⡻⠺⡱⡻⡥⠍="

        val plain =
            "U2FsdGVkX1/j97ClyTDacvadvPYI2RZERoFI3b1Un/jnSSTpQv9LK09Wi7VwWuJa\r\n" +
                "aya2nAC1zRYzjzek0e2YAND2Fk8Iwga31vmMJXi+51PwYuHaWaH5vX+SXaRm1ojO\r\n" +
                "+OeDkQ0d92Ds30OI4JpEzmZXkVfkWQZ8B/mde5tn/2Ey5YVLxDYx/nVYvkDNxqqg\r\n" +
                "INvRIPxsk6qfKyQKc6qLG3k5E8mr9stPPQbqsq5NX6h7tqB5f+cTseJsmkC0Rbi2\r\n" +
                "AyKbXtbbxAWM6yGI+z/UlCF6J92rkUcmD6Mo5OKHJ6w28LTe28T5+1woWxgBzH9K\r\n" +
                "AKU="

        assertEquals(plain, d.blindDecode())
        assertEquals("⡑⡒⡔⡓⡔=", "abdcd".blindEncode())
    }

    @Test
    fun elementPeriod() {
        val d = "No Hs Bk Lr Db Uup Lr Rg Rg Fm"
        assertEquals("flagisgood", d.elementPeriodDecode())
        val plain = "periodic element table"
        val encrypted = "Cn Md Fl Db Rg Fm Db Es Ge Md Hs Md Mt Md Ds Lv Ge Lv Bk Cf Hs Md"
        assertEquals(encrypted, plain.elementPeriodEncode())
        assertEquals(plain, encrypted.elementPeriodDecode())
    }

    @Test
    fun zero1234() {
        assertEquals("108408808010204108840810842040410888", "alphabet index".zero1248())
        assertEquals("WELLDONE", "8842101220480224404014224202480122".zero1248Decode())
    }

    @Test
    fun zwc() {
        val d = "w\u200D\uFEFF\u200C\u200B\u200D\uFEFF\u200D\u200B\u200D\uFEFF\uFEFFhat"
        assertEquals(d, "abc".zwc("what"))
        assertEquals("abc", d.zwcDecode())
    }
}
