package org.example.javaexamples.custom;

import java.util.LinkedList;
import java.util.Queue;

class Compute{
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    Compute(Queue<Integer> queue1, Queue<Integer> queue2){
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(int num){
        this.queue2.offer(num);

        while(!this.queue1.isEmpty()){
            this.queue2.offer(this.queue1.poll());
        }

        Queue<Integer> temp = this.queue1;
        this.queue1 = this.queue2;
        this.queue2 = temp;
    }

    public Integer pop(){
        return this.queue1.poll();
    }
}
public class StackUsingQueue {

    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Compute compute = new Compute(queue1,queue2);
        compute.push(1);
        compute.push(2);
        compute.push(3);
        System.out.println(compute.pop());
    }
}
