package com.thoughtworks;

import com.thoughtworks.Consumer.IOwner;

class DummyOwner implements IOwner {
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
