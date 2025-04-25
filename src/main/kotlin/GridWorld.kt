package main.kotlin

import kotlin.random.Random


enum class Terrain{
    ROCKS,
    WATER,
    FOREST,
    SWAMP;

    override fun toString(): String =
        when (this){
            ROCKS -> "ROCKS "
            WATER -> "WATER "
            FOREST-> "FOREST"
            SWAMP -> "SWAMP "
        }
}

abstract class GridWorld(val dimension: Int){
    protected val grid: Array<Array<Terrain>> =
        Array(dimension){
            Array(dimension){
                val randomNumber = Random.nextInt(0,4)
                 when (randomNumber){
                    0 -> Terrain.ROCKS
                    1 -> Terrain.SWAMP
                    2 -> Terrain.WATER
                    3 -> Terrain.FOREST
                    else -> throw Exception()
                }
            }
        }

    inner class Character(val name: String = "player1"){
        internal var coordx = Random.nextInt(0,dimension)
        internal var coordy = Random.nextInt(0, dimension)
        internal val currentTerrain
            get() = grid[coordx][coordy]
        internal var lastAction: Char = '_'
        internal fun die(){
            println("Character dead. End of game")
        }
        fun up(){
            lastAction = 'U'
            if (coordy>0){
                coordy--
            } else{
                gridEdge(this)
            }
        }
        fun down(){
            lastAction = 'D'
            if (coordy<dimension-1){
                coordy++
            } else{
                gridEdge(this)
            }
        }
        fun left(){
            lastAction = 'L'
            if (coordx>0){
                coordx--
            } else{
                gridEdge(this)
            }
        }
        fun right(){
            lastAction = 'R'
            if (coordx<dimension-1){
                coordx++
            } else{
                gridEdge(this)
            }
        }
    }
    abstract fun gridEdge(character: Character)
    override fun toString(): String = buildString {
        for (row in grid){
            for (square in row){
                append(square.toString())
                append("   ")
            }
            append("\n")
        }
    }
}

class BoundedGridWorld(dimensionInput: Int): GridWorld(dimensionInput){
    override fun gridEdge(character: Character) {
    }
}

class DeadlyGridWorld(dimensionInput: Int): GridWorld(dimensionInput){
    override fun gridEdge(character: Character) {
        character.die()
    }
}

class WrappingGridWorld(dimensionInput: Int): GridWorld(dimensionInput){
    override fun gridEdge(character: Character) {
        when (character.lastAction){
            'U' -> character.coordy = dimension-1
            'D' -> character.coordy = 0
            'L' -> character.coordx = dimension-1
            'R' -> character.coordx = 0
        }
    }
}

class RandomGridWorld(dimensionInput: Int): GridWorld(dimensionInput){
    override fun gridEdge(character: Character) {
        character.coordx = Random.nextInt(0,dimension)
        character.coordy = Random.nextInt(0, dimension)
    }
}