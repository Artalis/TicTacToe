import android.util.Log
import java.lang.NumberFormatException

enum class TicOrToe(val s: String) { //do reprezentowania wartości w komórkach
    X("x"), O("o"), EMPTY(" ")
}
class TicTacToe () {

    private val raw = 3
    private var ticArray= Array(raw) { Array(raw) { TicOrToe.EMPTY } } //tablica dwuwymiarowa inicjalizowana jako 3x3 z wartościami EMPTY
    private val master =  TicTacToeMaster()



    fun newMove(player: Int, field: Int) {  //funkcja ta zależnie od numeru gracza przypisuje polu w tablicy odpowiednią wartość
        var x = field - 1
        when (player) {
            1 -> ticArray[x / raw][x % raw] = TicOrToe.X
            2 -> ticArray[x / raw][x % raw] = TicOrToe.O
        }
    }

    fun comMove():Int{
        val x= master.chooseMove(ticArray)
        newMove(2,master.chooseMove(ticArray))
        return x
    }

    fun emptyField(field: Int): Boolean { //funkcja sprawdza czy pole jest puste
        return ticArray[(field - 1) / raw][(field - 1) % raw] == TicOrToe.EMPTY
    }
    //funkcja sprawdza czy gracz wygrał
    fun checkWhoWin(player:Int):String{
        var spr = Array(raw) { true }
        for(i in 0 until raw){
            if(!ticArray[i].contains(TicOrToe.values().dropWhile { it == TicOrToe.values()[player-1] }[0]) and !ticArray[i].contains(TicOrToe.EMPTY)){
                return ("\nPlayer $player win!")
            }
            if((!Array(raw) { j -> ticArray[j*1][i] }.contains(TicOrToe.values().dropWhile { it == TicOrToe.values()[player-1] }[0])).and(
                    !Array(raw) { j -> ticArray[j*1][i] }.contains(TicOrToe.EMPTY)
                )){
                return ("\nPlayer $player win!")
            }
            spr[0]=spr[0] && !ticArray[i].contains(TicOrToe.EMPTY)
            spr[1]=spr[1] && ticArray[i][i]==TicOrToe.values()[player-1]
            spr[2]=spr[2] && ticArray[i][raw-1-i]==TicOrToe.values()[player-1]
        }
        if(spr[0]){
            return("\nDraw!")
        }
        if(spr[1] || spr[2]){
            return ("\nPlayer $player win!")
        }
        return ""
    }



}



