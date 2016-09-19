package com.company;

import java.util.Scanner;

public class Main {

    static int nodesCount;

    static int edgesCount;

    static int[][] nodes;

    static int startIndex;

    static int[] distances;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesCount = in.nextInt();

        for (int i = 0; i < testCasesCount; i++) {
            nodesCount = in.nextInt();
            edgesCount = in.nextInt();

            nodes = new int[nodesCount + 1][nodesCount];
            distances = new int[nodesCount + 1];

            for(int j = 0; j < edgesCount; j++) {
                int node = in.nextInt();

                int index = emptyNeighbourIndex(node);

                int neighbour = in.nextInt();
                if(!isEdgeExist(node, neighbour)) {
                    nodes[node][index] = neighbour;
                    int neighbourIndex = emptyNeighbourIndex(neighbour);
                    nodes[neighbour][neighbourIndex] = node;
                    index++;
                }
            }

            startIndex = in.nextInt();
            calculateDistances();


            for(int j = 1; j < nodesCount + 1; j++) {
                if(j != startIndex) {
                    if(distances[j] == 0) {
                        System.out.print("-1 ");
                    } else {
                        System.out.print((distances[j] * 6) + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    static int emptyNeighbourIndex(int node) {
        for(int i = 0; i < nodesCount; i++) {
            int neighbour = nodes[node][i];

            if(neighbour == 0) {
                return i;
            }
        }

        return 0;
    }

    static void calculateDistances() {
        Queue queue = new Queue(nodesCount);

        queue.push(startIndex);

        while(queue.getSize() > 0) {
            int index = queue.pop();

            int distance = distances[index];

            for (int i = 0; i < edgesCount; i++) {
                int neighbour = nodes[index][i];

                if(neighbour == 0) {
                    break;
                }

                int newDistance = distance + 1;
                if(distances[neighbour] == 0 || newDistance < distances[neighbour]) {
                    distances[neighbour] = newDistance;

                    queue.push(neighbour);
                }
            }
        }
    }

    private static boolean isEdgeExist(int node, int neighbour) {
        for(int i = 0; i < edgesCount; i++) {
            int currentNeighbour = nodes[neighbour][i];

            if(currentNeighbour == 0) {
                break;
            } else if(currentNeighbour == node) {
                return true;
            }
        }

        for(int i = 0; i < edgesCount; i++) {
            int currentNeighbour = nodes[node][i];

            if(currentNeighbour == 0) {
                break;
            } else if(currentNeighbour == neighbour) {
                return true;
            }
        }

        return false;
    }


    private static class Queue {
        private int head;

        private int tail;

        private int[] elements;

        private int size;

        public Queue(int nodesCount) {
            elements = new int[(nodesCount + 1) * (nodesCount + 1)];
        }

        public void push(int nodeIndex) {
            elements[head] = nodeIndex;
            head++;
            size++;
        }

        public int pop() {
            int value = elements[tail];
            size--;
            tail++;
            return value;
        }

        public int getSize() {
            return size;
        }
    }
}
