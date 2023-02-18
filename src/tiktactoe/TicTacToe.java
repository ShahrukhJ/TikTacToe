package tiktactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe
{
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    public static void main(String[] args)
    {
        char [] [] gameBoard = {{' ','|',' ','|',' '},
                                {'-','+','-','+','-'},
                                {' ','|',' ','|',' '},
                                {'-','+','-','+','-'},
                                {' ','|',' ','|',' '}
        };

        /*
        for understanding:
        char [] [] gameBoard = {  0   1   2 ...element
                                {'1','|','2','|','3'},
                                {'_','+','_','+','_'},
                                {'4','|','5','|','6'},
                                {'_','+','_','+','_'},
                                {'7','|','8','|','9'}
        };
        */
        printGameBord(gameBoard);

        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("ENTER YOUR POSITION NUMBER (1-9)");
            int playerPosition = scanner.nextInt();

            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition))
            {
                System.out.println("position taken");
                playerPosition = scanner.nextInt();
            }

            positionPlacing(gameBoard,playerPosition,"player");

            Random RandomNumber = new Random();
            int cpuPosition = RandomNumber.nextInt(9) +1 ;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition))
            {
                System.out.println("position taken");
                cpuPosition = RandomNumber.nextInt(9) +1 ;
            }

            positionPlacing(gameBoard,cpuPosition,"cpu");

            String winner = winnerDecider();
            System.out.println(winner);
        }
    }

    public static void printGameBord(char [] [] gameBoard)
    {
        for (char[] row : gameBoard) {
            for (char c : row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void positionPlacing(char[] [] gameBoard,int playerPosition,String user)
    {
        char symbol = ' ';
        if (user.equals("player"))
        {
                symbol = 'X';
                playerPositions.add(playerPosition);
        }
        else if (user.equals("cpu"))
        {
            symbol = 'O';
            playerPositions.add(playerPosition);
        }
        switch (playerPosition)
        {
            case 1:
                gameBoard [0][0] = symbol;
                break;
            case 2:
                gameBoard [0][2] = symbol;
                break;
            case 3:
                gameBoard [0][4] = symbol;
                break;
            case 4:
                gameBoard [2][0] = symbol;
                break;
            case 5:
                gameBoard [2][2] = symbol;
                break;
            case 6:
                gameBoard [2][4] = symbol;
                break;
            case 7:
                gameBoard [4][0] = symbol;
                break;
            case 8:
                gameBoard [4][2] = symbol;
                break;
            case 9:
                gameBoard [4][4] = symbol;
                break;
            default:
                System.out.println("INVALID POSITION");
                break;

        }
        printGameBord(gameBoard);
        System.out.println("---------------------------");
    }

    public static String winnerDecider()
    {
        //winning positions
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);

        List right = Arrays.asList(1,4,7);
        List middle = Arrays.asList(2,5,8);
        List left = Arrays.asList(3,6,9);

        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winningPosition = new ArrayList<>();
        winningPosition.add(topRow);
        winningPosition.add(middleRow);
        winningPosition.add(bottomRow);
        winningPosition.add(right);
        winningPosition.add(middle);
        winningPosition.add(left);
        winningPosition.add(cross1);
        winningPosition.add(cross2);

        for(List l : winningPosition)
        {
            if (playerPositions.containsAll(l))
            {
                return "Congratulations Player";
            } else if (cpuPositions.containsAll(l)) {

                return "Congratulations Cpu";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "its a Tie ";
            }
        }
        return " ";
    }
}