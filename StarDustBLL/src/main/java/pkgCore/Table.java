package pkgCore;

import java.util.ArrayList;
import java.util.UUID;

public class Table {

	private UUID TableID;
	private String TableName;
	private ArrayList<Player> TablePlayers = new ArrayList<Player>();
	
	public Table(UUID tableID, String tableName) {
		TableID = tableID;
		TableName = tableName;
	}
	
	public Table(String tableName) {
		TableID = UUID.randomUUID();
		TableName = tableName;
	}
	
	public void AddPlayerToTable(Player p)
	{
		TablePlayers.add(p);
	}
	public void SetTablePlayers(ArrayList<Player> Players)
	{
		TablePlayers.clear();
		TablePlayers.addAll(Players);
	}
	
	public void RemovePlayerFromTable(Player p)
	{
		TablePlayers.remove(p);
	}
}
