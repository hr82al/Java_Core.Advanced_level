package ru.geekbrains.java2.lesson1;

public class Team implements Competition{

    private final String teamName;
    public final Member[] members;

    public Team(Member... members) {
        this("Mушкетёры", members);
    }
    public Team(String teamName, Member... members) {
        this.teamName = teamName;
        this.members = members;
    }

    @Override
    public String toString() {
        String result =  "Название комманды: \"" + teamName + "\"\n\n" +
                "Члены комманды: \n" ;
        for (Member i : members){
            result += i.toString() + "\n";
        }
        return result;
    }

    @Override
    public void showResults() {
        System.out.println("Список членов комманды \"" + teamName +
                "\", прошеших дистанцию:\n");
        for(Member member: members){
            if (member.finished) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void showTeam() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        Team team = new Team(
                new Member("Д'aртаньян", 26),
                new Member("Атос", 30),
                new Member("Партос", 52),
                new Member("Арамис", 22));
        //team.showTeam();
        Course c = new Course(Obscacle.RUNING, Obscacle.BALANCING,
                Obscacle.CLIMBING, Obscacle.JUMPING, Obscacle.SWINIMNG,
                Obscacle.CRAWLING);
        c.doIt(team);
        team.showTeam();
        team.showResults();
        //System.out.println(Obscacle.RUNING.ablePass(11));
    }


}
