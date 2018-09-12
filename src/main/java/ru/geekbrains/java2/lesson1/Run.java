package ru.geekbrains.java2.lesson1;

public abstract class Run {
  protected final static boolean run(Obscacle[] obscacles, Member member){
      for (Obscacle obscacle : obscacles){
          if(!obscacle.ablePass(member.getAge())){
              return  false;
          }
      }
      return true;
  }
  protected abstract void doIt(Team team);
}
