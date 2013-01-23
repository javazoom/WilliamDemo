package com.william.demo.tdd.chess;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-1-3
 * Time: 下午12:25
 * To change this template use File | Settings | File Templates.
 */
public class PawnTest extends TestCase{
    public void testCreate()
    {
        final String pawnColor_white = "white";
        final String pawnColor_black = "black";
        Pawn onePawn = new Pawn(pawnColor_white);
        assertEquals(pawnColor_white, onePawn.getColor());

        Pawn secondPawn = new Pawn(pawnColor_black);
        assertEquals(pawnColor_black, secondPawn.getColor());

    }
}
