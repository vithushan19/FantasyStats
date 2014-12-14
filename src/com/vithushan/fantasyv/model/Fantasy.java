package com.vithushan.fantasyv.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * input: a string player stats for the week in JSON format output: a list of
 * java player objects with their stats
 * 
 * @author vithushan
 *
 */
public class Fantasy {

	public ArrayList<Player> initializePlayers(String jsonString)
			throws JSONException {
		String s = "";
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList<Player> players = new ArrayList<Player>();

		JSONObject obj = new JSONObject(jsonString);
		JSONObject matchups = obj.getJSONObject("fantasy_content")
				.getJSONArray("league").getJSONObject(1)
				.getJSONObject("scoreboard").getJSONObject("0")
				.getJSONObject("matchups");


		try {
			for (int i = 0; i < 7; i++) {
				JSONObject teams = matchups.getJSONObject(i + "")
						.getJSONObject("matchup").getJSONObject("0")
						.getJSONObject("teams");

				JSONArray team1 = teams.getJSONObject("0").getJSONArray("team");
				String team1Name = team1.getJSONArray(0).getJSONObject(2)
						.getString("name").toString();
				JSONArray team1Stats = team1.getJSONObject(1)
						.getJSONObject("team_stats").getJSONArray("stats");

				JSONObject team1Final = new JSONObject();
				team1Final.put("name", team1Name);
				team1Final.put("stats", team1Stats);

				Log.i("TAG", "player: " + team1Final.toString());
				Player player1;

				player1 = mapper.readValue(team1Final.toString(), Player.class);

				players.add(player1);

				JSONArray team2 = teams.getJSONObject("1").getJSONArray("team");
				String team2Name = team2.getJSONArray(0).getJSONObject(2)
						.getString("name").toString();
				JSONArray team2Stats = team2.getJSONObject(1)
						.getJSONObject("team_stats").getJSONArray("stats");

				JSONObject team2Final = new JSONObject();
				team2Final.put("name", team2Name);
				team2Final.put("stats", team2Stats);
				Player player2 = mapper.readValue(team2Final.toString(),
						Player.class);
				players.add(player2);

			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Player p : players) {
			List<Stat> stats = p.getStats();
			stats.remove(0);
			stats.remove(1);
		}

		return players;
	}

}
