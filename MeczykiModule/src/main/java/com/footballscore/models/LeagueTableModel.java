package com.footballscore.models;

/**
 * Created by Przemysław Skitał on 2016-08-11.
 */
public class LeagueTableModel {

    private String position;
    private String teamName;
    private String crestURI;
    private String playedGames;
    private String points;
    private String goals;
    private String goalsAgainst;
    private String wins;
    private String draws;
    private String losses;
    private String href;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    public String getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(String playedGames) {
        this.playedGames = playedGames;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
