package com.coding.sirjavlux.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SBoardManager {

	private static HashMap<UUID, SBoard> boards = new HashMap<>();
	
	public static SBoard getActiveBoard(UUID uuid) {
		SBoard board = null;
		if (boards.containsKey(uuid)) board = boards.get(uuid);
		return board;
	}
	
	public static void addBoard(SBoard board) {
		boards.put(board.getUUID(), board);
	}
	
	public static void removeBoard(UUID uuid) {
		if (boards.containsKey(uuid)) {
			boards.remove(uuid);
		}
	}
	
	public static List<SLine> stringArrayToLines(List<String> textLines) {
		List<SLine> lines = new ArrayList<>();
		for (String text : textLines) {
			lines.add(new SLine(text));
		}
		Collections.reverse(lines);
		return lines;
	}
}
