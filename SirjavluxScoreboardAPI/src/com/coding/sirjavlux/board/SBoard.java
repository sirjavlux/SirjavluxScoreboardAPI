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
		for (int i = 0; i < 15; i++) board.registerNewTeam(i + "");
		
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
		//add lines to board
		for (int i = 0; i < 15; i++) {
			SLine line = lines.size() > i ? lines.get(i) : null;
			SLine oldLine = this.lines.size() > i ? this.lines.get(i) : null;
			Team team = board.getTeam(i + "");
			Objective obj = board.getObjective(title);
			if (line != null) {
				String prefix = line.getPrefix();
				String definer = line.getDefiner();
				String suffix = line.getSuffix();
				if (oldLine != null && !team.getEntries().isEmpty()) {
					if (oldLine.getLineText().equals(line.getLineText())) {
						continue;
					} else if (oldLine.getDefiner().equals(line.getDefiner())) {
						team.setPrefix(prefix);
						team.setSuffix(suffix);
						continue;
					} else {
						String oldDefiner = team.getEntries().toArray()[0].toString();
						board.resetScores(oldDefiner);
						team.removeEntry(oldDefiner);
					}
				} 
				if (team.getEntries().isEmpty()) team.addEntry(definer);
				team.setPrefix(prefix);
				team.setSuffix(suffix);
				obj.getScore(definer).setScore(i + 1);
			} else if (!team.getEntries().isEmpty()) {
				String oldDefiner = team.getEntries().toArray()[0].toString();
				board.resetScores(oldDefiner);
				team.removeEntry(oldDefiner);
			}
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
