package ru.geekbrains.javaCoreAdvanced.lesson_1;

public class Course extends Run {

    private final Obscacle[] obscacles;
    public Course(Obscacle... obscacles) {
        this.obscacles = obscacles;
    }

    @Override
    protected void doIt(Team team) {
        for(Member member: team.members){
            member.finished = run(obscacles, member);
        }
    }
}
