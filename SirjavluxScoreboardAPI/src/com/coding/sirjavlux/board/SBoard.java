package com.coding.sirjavlux.board;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SBoard {

	private Player p;
	private String title;
	private UUID uuid;
	private List<SLine> lines;
	private Scoreboard board;
	
	public SBoard(Player p, UUID uuid, String title) {
		this.p = p;
		this.uuid = uuid;
		this.title = title;
		this.lines = new ArrayList<>();
		
		//create scoreboard
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.getObjective(title);
		if (obj == null)  {
			obj = board.registerNewObjective(title, "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		
		//add to manager
		SBoardManager.addBoard(this);
	}
	
	public void updateBoard(List<String> strLines) {
		List<SLine> lines = SBoardManager.stringArrayToLines(strLines);
		if (SBoardManager.getActiveBoard(p.getUniqueId()) == null) {
			this.setActive();
		} else if (!SBoardManager.getActiveBoard(p.getUniqueId()).equals(this)) {
			this.setActive();
		}
		//get newLinesTexts
		List<String> texts = new ArrayList<>();
		for (SLine l : lines) {
			texts.add(l.getLineText());
		}
		//remove unused lines
		int count = 1;
		for (Team team : board.getTeams()) {
			if (count > lines.size()) {
				board.resetScores(team.getEntries().toArray()[0].toString());
				team.unregister();
			}
			count++;
		}
		
		//set lines
		Objective obj = board.getObjective(title);
		count = 1;
		for (SLine line : lines) {
			SLine oldLine = this.lines.size() > count - 1 ? this.lines.get(count - 1) : null;
			String prefix = line.getPrefix();
			String definer = line.getDefiner();
			String suffix = line.getSuffix();
			Team team = board.getTeam(definer);
			if (team == null) {
				if (oldLine != null) {
					if (board.getTeam(oldLine.getDefiner()) != null) {
						System.out.println("remove line");
						board.resetScores(oldLine.getDefiner());
						board.getTeam(oldLine.getDefiner()).unregister();	
					}
				}
				team = board.registerNewTeam(definer);
				team.addEntry(definer);
			}
			team.setPrefix(prefix);
			team.setSuffix(suffix);
			obj.getScore(definer).setScore(count);
			count++;
		}		
		this.lines = lines;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public void setActive() {
		if (p.isOnline()) {
			p.setScoreboard(this.board);
		}
	}
	
	public Scoreboard getScoreboard() {
		return this.board;
	}
}
