package com.thoughtworks;

public class DummySecurityGuard implements IOwner {
    int counter = 0;
    int freeSpace = 0;

    @Override
    public  void informFullSpace(){
        counter++;
    }
    @Override
    public  void  informFreeSpace(){
        freeSpace++;
    }
}
