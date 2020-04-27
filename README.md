# SirjavluxScoreboardAPI
Used for 1.7.10 48 character scoreboards

### Creation of scoreboard
```
 SBoard board = new SBoard(Player p, UUID uuid, String name);
```

### Update board lines
```
board.updateBoard(List<String> list);
```

### Activate board
```
board.setActive();
```

### Example
```
	@EventHandler
	public void pJoin(PlayerJoinEvent e) {
		new BukkitRunnable() {
			Player p = e.getPlayer();
		    SBoard board = new SBoard(p, UUID.randomUUID(), " ");
		        
		    public void run() {
		    	List<String> list = new ArrayList<String>();
		    	for (int i = 0; i < 15; i++) {
		    		list.add(Main.this.generateRandomColorString());
		    	}
		    	this.board.updateBoard(list);
		    }
		}.runTaskTimer(this, 1L, 1L); 
	}

	  
	private String generateRandomColorString() {
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 16; i++) {
			int color = rand.nextInt(9);
			builder.append("&" + color + "#");
		} 
		return builder.toString();
	}
```
![AnimationPreview](https://media.giphy.com/media/cItjJyB1sCOVYCxXGI/giphy.gif)
