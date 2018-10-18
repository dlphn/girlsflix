package server;

public class SeasonPair {
	private int tvId;
	private int seasonNumber;
	
	public SeasonPair(int tvId, int seasonNumber) {
		this.tvId = tvId;
		this.seasonNumber = seasonNumber;
	}

	public int getTvId() {
		return tvId;
	}

	public void setTvId(int tvId) {
		this.tvId = tvId;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
}
