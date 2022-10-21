package oop.bomberman.entities.character.enemy;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Astar {
    public class A_Star {

        private final static int ROW = 15;
        private final static int COL = 25;
        static class cell {
            public int parentX = 0, parentY = 0;
            public double f, g, h;
        }

        private static boolean isValid(int row, int col) {
            return row >= 0 && row < ROW && col >= 0 && col < COL;
        }

        private static boolean isUnBlocked(int[][] grid, int row, int col) {
            return grid[row][col] == 0;
        }

        private static boolean isDestination(int row, int col, Pair<Integer, Integer> dest) {
            return row == dest.getKey() && col == dest.getValue();
        }

        private static double calculateHeuristicValue(int row, int col, Pair<Integer, Integer> dest) {
            return Math.sqrt(Math.pow(row - dest.getKey(), 2) + Math.pow(col - dest.getValue(), 2));
        }

        private static Pair<Integer, Integer> tracePath(cell[][] cellDetails, Pair<Integer, Integer> src, Pair<Integer, Integer> dest) {
            int row = dest.getKey();
            int col = dest.getValue();

            try {
                while (cellDetails[row][col].parentX != src.getKey() || cellDetails[row][col].parentY != src.getValue()) {
                    int tempRow = cellDetails[row][col].parentX;
                    int tempCol = cellDetails[row][col].parentY;
                    row = tempRow;
                    col = tempCol;
                }
                return new Pair<>(row, col);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Pair<>(-1, -1);
            }
        }

        private static boolean pathProcessor(
                int pi, int pj,
                int i, int j,
                Pair<Integer, Integer> dest,
                double fx, double gx, double hx,
                cell[][] cellDetails,
                boolean[][] closedList,
                int[][] grid,
                List<Pair<Double, Pair<Integer, Integer>>> openList
        ) {
            if (!isValid(i, j)) {
                return false;
            }
            if (isDestination(i, j, dest)) {
                cellDetails[i][j].parentX = pi;
                cellDetails[i][j].parentY = pj;

                return true;
            }
            if (!closedList[i][j] && isUnBlocked(grid, i, j)) {

                gx = cellDetails[pi][pj].g + 1.0;

                hx = calculateHeuristicValue(i, j, dest);
                fx = hx + gx;

                // fx < f -> update
                if (cellDetails[i][j].f == Double.MAX_VALUE || cellDetails[i][j].f > fx) {
                    openList.add(new Pair<>(fx, new Pair<>(i, j)));
                    // update cell
                    cellDetails[i][j].f = fx;
                    cellDetails[i][j].h = hx;
                    cellDetails[i][j].g = gx;
                    cellDetails[i][j].parentY = pj;
                    cellDetails[i][j].parentX = pi;
                }
            }
            return false;
        }

        public static Pair<Integer, Integer> aStarSearch(int[][] grid, Pair<Integer, Integer> src, Pair<Integer, Integer> dest) {
            if (!isValid(src.getKey(), src.getValue())) {
                System.out.println("src invalid");
                return src;
            }
            if (!isValid(dest.getKey(), dest.getValue())) {
                System.out.println("dest invalid");
                return src;
            }
            if (!isUnBlocked(grid, dest.getKey(), dest.getValue()) || !isUnBlocked(grid, src.getKey(), src.getValue())) {
                System.out.println("src || dest blocked");
                return src;
            }
            if (isDestination(src.getKey(), src.getValue(), dest)) {
                System.out.println("src = dest");
                return src;
            }
            boolean[][] closedList = new boolean[ROW][COL];
            cell[][] cellDetails = new cell[ROW][COL];

            // init cell details
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    cellDetails[i][j] = new cell();
                    cellDetails[i][j].parentX = -1;
                    cellDetails[i][j].parentY = -1;
                    cellDetails[i][j].f = Double.MAX_VALUE;
                    cellDetails[i][j].h = Double.MAX_VALUE;
                    cellDetails[i][j].g = Double.MAX_VALUE;
                }
            }

            int i = src.getKey();
            int j = src.getValue();

            cellDetails[i][j].parentX = i;
            cellDetails[i][j].parentY = j;
            cellDetails[i][j].f = 0D;
            cellDetails[i][j].h = 0D;
            cellDetails[i][j].g = 0D;

            List<Pair<Double, Pair<Integer, Integer>>> openList = new ArrayList<>();
            openList.add(new Pair<>(0D, new Pair<>(i, j)));

            boolean isDest = false;
            while (!openList.isEmpty()) {
                openList.sort((o1, o2) -> {
                    if (o1.getKey() > o2.getKey()) {
                        return 1;
                    } else if (Objects.equals(o1.getKey(), o2.getKey())) {
                        return 0;
                    }
                    return -1;
                });
                Pair<Double, Pair<Integer, Integer>> p = openList.iterator().next();
                openList.remove(0);
                i = p.getValue().getKey();
                j = p.getValue().getValue();
                closedList[i][j] = true;
                double fx = 0, gx = 0, hx = 0;
                isDest = pathProcessor(i, j, i - 1, j, dest, fx, gx, hx, cellDetails, closedList, grid, openList);
                if (isDest) {
                    break;
                }
                isDest = pathProcessor(i, j, i + 1, j, dest, fx, gx, hx, cellDetails, closedList, grid, openList);
                if (isDest) {
                    break;
                }
                isDest = pathProcessor(i, j, i, j + 1, dest, fx, gx, hx, cellDetails, closedList, grid, openList);
                if (isDest) {
                    break;
                }
                isDest = pathProcessor(i, j, i, j - 1, dest, fx, gx, hx, cellDetails, closedList, grid, openList);
                if (isDest) {
                    break;
                }
            }
            return tracePath(cellDetails, new Pair<>(src.getKey(), src.getValue()), dest);
        }


    }


}

