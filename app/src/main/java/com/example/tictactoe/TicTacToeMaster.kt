import kotlin.random.Random

class TicTacToeMaster {

    var corrners = arrayOf(1,3,7,9)
    var edges = arrayOf(2,4,6,8)
    var middle = 5

    var empty=false

    fun chooseMove(arg:Array<Array<TicOrToe>>):Int{  //Wybiera najlepsze pole
        var field = 0
        when (numberOfMoves(arg)) {
           1 ->{ //jeżeli gracz wykonał pierwszy ruch
               field = if(emptyField(5,arg)){ //jezeli srodek wolny wybiera go
                   middle
               }else
                   chooseOne(corrners,arg) //jezeli nie wybiera pierwszy rog

        }
            3->{ //gracz 1 wykonał już 2 ruchy
                field = ifTwoInRow(arg,TicOrToe.X) //blokowanie przeciwnika
                if(field==0 && arg[1][1]==TicOrToe.X && numberOfFreeFields(corrners,arg)==2)
                    field = chooseOne(corrners,arg)
                if(field==0 && numberOfFreeFields(corrners,arg)==2)
                    field = chooseOne(edges,arg)
                if(field==0) //jezeli zadna z opcji
                    field = chooseOne(corrners,arg)
            }
            5->{ //to jest trzeci ruch możliwe pierwszy raz wygrana
                field = ifTwoInRow(arg,TicOrToe.O) //istnieją dwa znaki w lini można zakończyc
                if(field==0)
                    field=ifTwoInRow(arg,TicOrToe.X) //jeżeli trzeba zablokowac ruch
                if(field==0) //szukamy mozliwych widelek przeciwnika
                    field = possibleFork(arg, TicOrToe.X)
                if(field==0) //szukamy mozliwych widelek przeciwnika
                    field = possibleFork(arg, TicOrToe.O)
                if(field==0) //jezeli zadna z opcji
                    field = chooseOne(edges+corrners,arg)
            }
            7->{
                field = ifTwoInRow(arg,TicOrToe.O) //istnieją dwa znaki w lini można zakończyc
                if(field==0)
                    field=ifTwoInRow(arg,TicOrToe.X) //jeżeli trzeba zablokowac ruch
                if(field==0) //jeżeli nie wybrano wciaz pola wybieramy w pierszej kolejnosci brzegi nastepnie rogi
                    field = chooseOne(edges+corrners,arg)


            }

        }


        return field
    }

    private fun numberOfMoves(arg:Array<Array<TicOrToe>>):Int{
        var counter=0
        for(i in arg){
            for(j in i){
                if(j!=TicOrToe.EMPTY)
                    counter+=1
            }
        }

        return counter
    }
    //jezeli w dwoch przecinajacych sie liniach po jednym znaku a reszta pusta
    private fun possibleFork(arg:Array<Array<TicOrToe>>, tic:TicOrToe):Int{

        for(i in 0 until 3){
            if(arrayOf(arg[0][i],arg[1][i],arg[2][i]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))) { //jeżeli zawiera znak komputera i puste
                if(arrayOf(arg[0][0],arg[1][1],arg[2][2]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))){
                    if(arrayOf(arg[0][0],arg[1][1],arg[2][2])[i]==TicOrToe.EMPTY)
                        return i*3+i+1
                }
                if(arrayOf(arg[0][2],arg[1][1],arg[2][0]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))){
                    if(arrayOf(arg[0][2],arg[1][1],arg[2][0])[i]==TicOrToe.EMPTY)
                        return i*3+i+1
                }
            }
            if(arg[i].sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))) { //jeżeli zawiera znak komputera i puste
                if(arrayOf(arg[0][0],arg[1][1],arg[2][2]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))){
                    if(arrayOf(arg[0][0],arg[1][1],arg[2][2])[i]==TicOrToe.EMPTY)
                        return i*3+i+1

                }
                if(arrayOf(arg[0][2],arg[1][1],arg[2][0]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))){
                    if(arrayOf(arg[0][2],arg[1][1],arg[2][0])[i]==TicOrToe.EMPTY)
                        return i*3+i+1

                }
                for( j in 0 until 3){
                    if(arg[i][j]==TicOrToe.EMPTY && arrayOf(arg[0][j],arg[1][j],arg[2][j]).sortedArray().contentEquals(arrayOf(tic,TicOrToe.EMPTY,TicOrToe.EMPTY))){
                        return i*3+j+1
                    }
                }

            }



        }

        return 0
    }
    private fun numberOfFreeFields(arg:Array<Int>,arg2:Array<Array<TicOrToe>>):Int{
        var counter=0
        for(i in arg){
            if(arg2[(i - 1) / 3][(i - 1) % 3] ==TicOrToe.EMPTY)
                counter+=1
        }
        return counter

        return counter
    }
    private fun chooseOne(arg:Array<Int>, arg2:Array<Array<TicOrToe>>):Int{
        for(i in arg){
            if (emptyField(i,arg2))
                return i
        }
        return 0
    }

    private fun ifTwoInRow(arg:Array<Array<TicOrToe>>,sign: TicOrToe): Int{ //jeżeli 0 oznacza że nie ma dwóch w rzędzie/pionie/skosie
        for(i in 0 until 3){
            if(arg[i].sortedArray().contentEquals(arrayOf(sign,sign,TicOrToe.EMPTY))) //jeżeli zawiera dwa znaki przeciwnika i puste
                return i*3+arg[i].indexOf(TicOrToe.EMPTY)+1

            if(arrayOf(arg[0][i],arg[1][i],arg[2][i]).sortedArray().contentEquals(arrayOf(sign,sign,TicOrToe.EMPTY))){
                return arrayOf(arg[0][i],arg[1][i],arg[2][i]).indexOf(TicOrToe.EMPTY)*3+i+1
            }

        }
        if(arrayOf(arg[0][0],arg[1][1],arg[2][2]).sortedArray().contentEquals(arrayOf(sign,sign,TicOrToe.EMPTY))){
            when(arrayOf(arg[0][0],arg[1][1],arg[2][2]).indexOf(TicOrToe.EMPTY)){
                0-> return 1
                1-> return 5
                2-> return 9
            }
        }
        if(arrayOf(arg[0][2],arg[1][1],arg[2][0]).sortedArray().contentEquals(arrayOf(sign,sign,TicOrToe.EMPTY))){
            when(arrayOf(arg[0][2],arg[1][1],arg[2][0]).indexOf(TicOrToe.EMPTY)){
                0-> return 3
                1-> return 5
                2-> return 7
            }
        }
        return 0
    }


    private fun emptyField(field: Int,arg:Array<Array<TicOrToe>>): Boolean { //funkcja sprawdza czy pole jest puste
        return arg[(field - 1) / 3][(field - 1) % 3] == TicOrToe.EMPTY
    }
}