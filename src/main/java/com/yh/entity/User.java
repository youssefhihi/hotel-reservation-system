package com.yh.entity;

public class User {

    private static long SEQ = 0;
    private final Long id;
    private Integer balance;

    private User() {
        this.id = ++SEQ;
    }

    public Long getId(){ return id; }

    public Integer getBalance(){ return balance; }

    public void debit(int amount) { balance -= amount; }

   public static User createUser(Integer balance) {
       User user = new User();
       user.balance = balance;
       return user;
   }

}
